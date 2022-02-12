package br.com.chess.services.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.UUID;

import br.com.chess.domain.Arquivo;
import br.com.chess.domain.ArquivoDownload;
import br.com.chess.domain.Usuario;
import br.com.chess.repositories.ArquivoDownloadRepository;
import br.com.chess.repositories.ArquivoRepository;
import br.com.chess.repositories.UsuarioRepository;
import br.com.chess.services.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class StorageS3 implements StorageService, HealthIndicator {

	private final ArquivoRepository arquivoRepository;
	
	private final ArquivoDownloadRepository arquivoDownloadRepository;
	
	private final UsuarioRepository usuarioRepository;
	
	private final S3Client s3Client;
	
	private static final Logger logger = LoggerFactory.getLogger("StorageS3");

	private static final Random rand = new Random();
	
	@Value("${s3.bucket.name}")
	private String bucket;
	
	@Autowired
	public StorageS3(ArquivoRepository arquivoRepository, ArquivoDownloadRepository arquivoDownloadRepository, S3Client s3Client, UsuarioRepository usuarioRepository) {
		this.arquivoRepository = arquivoRepository;
		this.arquivoDownloadRepository = arquivoDownloadRepository;
		this.s3Client = s3Client;
		this.usuarioRepository = usuarioRepository;
	}
	
	private File createTempFile(InputStream input) throws IOException {
		File arquivo = new File(System.getProperty("java.io.tmpdir") + "/tempUpload_" + UUID.randomUUID().toString());
		try (FileOutputStream fos = new FileOutputStream(arquivo)) {
			int c = -1;
			byte[] buffer = new byte[65536];
			while ((c = input.read(buffer)) != -1) {
				fos.write(buffer, 0, c);
			}
		}
		return arquivo;
	}
	
	@Override
	public Arquivo store(String nomeOriginal, String bucket, Usuario usuario, InputStream conteudo) {
		if (nomeOriginal == null) {
			throw new StorageError("Nome original indefinido");
		}
		
		if (usuario == null) {
			throw new StorageError("Usuário indefinido");
		}
		
		if (conteudo == null) {
			throw new StorageError("Conteúdo indefinido");
		}
		
		Arquivo arquivo = new Arquivo();
		arquivo.setBucket(bucket);
		arquivo.setUuid(UUID.randomUUID().toString());
		
		arquivo.setOriginalName(nomeOriginal);
		
		arquivo.setUsuario(usuario);
		arquivo.setExcluido(false);
		arquivo.setCreatedAt(new java.util.Date());
		
		File arquivoTemp = null;
		try {
			arquivoTemp = this.createTempFile(conteudo);

			this.s3Client.putObject(PutObjectRequest.builder()
					.bucket(bucket)
					.contentLength(arquivoTemp.length())
					.key(arquivo.getUuid())
					.build(), 
					RequestBody.fromFile(arquivoTemp));
		} catch (IOException ex) {
			throw new StorageError("Erro no sistema de arquivos", ex);
		} finally {
			if (arquivoTemp != null && arquivoTemp.exists()) {
				this.cleanUp(arquivoTemp.toPath());
			}
		}
		
		return this.arquivoRepository.save(arquivo);
	}
	public void cleanUp(Path path)  {
		try {
			Files.delete(path);
		} catch (IOException e) {
			logger.error(String.format("Não foi possível apagar o arquivo %s", path));
		}
	}

	@Override
	public InputStream read(Arquivo arquivo, Usuario usuario) {
		if (arquivo == null) {
			throw new StorageError("Arquivo indefinido");
		}
		if (usuario == null) {
			throw new StorageError("Usuário indefinido");
		}
		
		ArquivoDownload download = new ArquivoDownload();
		
		download.setArquivo(arquivo);
		download.setUsuario(usuario);
		arquivoDownloadRepository.save(download);
		
		return this.s3Client.getObject(GetObjectRequest.builder()
													   .bucket(arquivo.getBucket())
													   .key(arquivo.getUuid())
													   .build());
	}

	@Override
	public void remove(Arquivo arquivo) {
		DeleteObjectRequest request = DeleteObjectRequest.builder().bucket(arquivo.getBucket()).key(arquivo.getUuid()).build();
		this.s3Client.deleteObject(request);
		arquivo.setExcluido(true);
		arquivoRepository.save(arquivo);
	}

	@Override
	public String getType() {
		return "AWS S3";
	}

	@Override
	public Health health() {
		
		String nomeArquivoTeste = "ArquivoTeste-" + UUID.randomUUID().toString();
		
		File arquivoTeste = new File(System.getProperty("java.io.tmpdir") + "/tempFile-" + UUID.randomUUID().toString());
		
		StringBuilder builder = new StringBuilder();
		int tamanho = 1 + (rand.nextInt() * 100);
		for (int i = 0; i < tamanho; i++) {
			builder.append(UUID.randomUUID().toString());
		}
		
		try (FileOutputStream fos = new FileOutputStream(arquivoTeste)){
			fos.write(builder.toString().getBytes());
		} catch (IOException ex) {
			return Health.down().withDetail("s3.error.test", ex.getMessage()).build();
		} 
		
		Arquivo registro = null;
		Usuario usuario = usuarioRepository.findByEmail("admin@itexto.com.br");
		
		try (FileInputStream inputStream = new FileInputStream(arquivoTeste)) {
			registro = this.store(nomeArquivoTeste, this.bucket, usuario, inputStream);
		} catch (Throwable ex) {
			return Health.down().withDetail("s3.error.write.io", ex.getMessage()).build();
		} 
		
		try (InputStream leituraArquivo = this.read(registro, usuario)){
			byte[] buffer = new byte[16384];
			StringBuilder builderReader = new StringBuilder();
			int c = -1;
			while ((c = leituraArquivo.read(buffer)) != -1) {
				builderReader.append(new String(buffer, 0, c));
			}
			
			if (! builder.toString().equals(builderReader.toString())) {
				return Health.down().withDetail("s3.error.inconsistent", "O valor escrito é diferente do lido no bucket").build();
			}
			
		} catch (Throwable error) {
			return Health.down().withDetail("s3.error.read", error.getMessage()).build();
		}
		
		
		
		return Health.up().build();
	}

}
