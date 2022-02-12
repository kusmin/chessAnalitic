package br.com.chess.repositories;

import br.com.chess.domain.Arquivo;
import br.com.chess.domain.ArquivoDownload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArquivoDownloadRepository extends JpaRepository<ArquivoDownload, Long> {

    List<ArquivoDownload> findAllByArquivo(Arquivo arquivo);
}
