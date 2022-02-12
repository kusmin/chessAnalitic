package br.com.chess;

import br.com.chess.domain.Perfil;
import br.com.chess.domain.Usuario;
import br.com.chess.dto.Autorizacao;
import br.com.chess.dto.Credenciais;
import br.com.chess.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.chess.repositories.PerfilRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class BaseTest {

    @Autowired
    protected WebTestClient testClient;
    
    @Autowired
    private PerfilRepository perfilRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UsuarioRepository usuarioRepository;

    public WebTestClient getTestClient() {
        return testClient;
    }

    public void setTestClient(WebTestClient testClient) {
        this.testClient = testClient;
    }

    /**
     * Gera um código UUID randômico
     * @return
     */
    protected String uuid() {
        return UUID.randomUUID().toString();
    }

	public Perfil getPerfil(Personas persona) {
		return perfilRepository.findByNome(persona.getNome());
	}

	protected Date addDias(Date data, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DATE, dias);
		return calendar.getTime();
	}
	/**
	 * Cria um usuário administrador com dados randômicos
	 * @return
	 */
	public Autorizacao criarAdministrador() {
		return criarAdministrador(email(), cpf(), senha());
	}

	/**
	 * Cria um usuário administrador
	 * @param email
	 * @param cpf
	 * @param senha
	 * @return
	 */
	public Autorizacao criarAdministrador(String email, String cpf, String senha) {
		return criarUsuarioAuth(email, senha, Personas.Administrador, cpf);
	}
	public Autorizacao criarUsuarioAuth(String email, String senha, Personas persona, String cpf) {
		return this.criarUsuarioAuth(email, senha, persona, cpf, BigDecimal.ZERO);
	}
	/**
	 * Cria um usuário que já se encontra autenticado na plataforma.
	 *
	 * É o método preferencial na escrita das especificações.
	 * @param email
	 * @param senha
	 * @param persona
	 * @param cpf
	 * @return
	 */
	public Autorizacao criarUsuarioAuth(String email, String senha, Personas persona, String cpf, BigDecimal saldo) {
		Usuario usuario = criarUsuario(email, senha, persona, cpf, saldo);
		return this.autenticar(usuario.getCpf(), senha);
	}
	public Autorizacao autenticar(String usuario, String senha) {
		Credenciais credenciais = new Credenciais(usuario, senha);
		return this.testClient.post().uri("/api/v1/auth")
				.bodyValue(credenciais)
				.exchange()
				.expectBody(Autorizacao.class).returnResult().getResponseBody();
	}

	protected Usuario criarUsuario(String email, String senha, Personas persona, String cpf, BigDecimal saldo) {
		return criarUsuario(email,senha,persona,cpf,Boolean.TRUE, saldo);
	}

	protected Usuario criarUsuario(String email, String senha, Personas persona, String cpf, Boolean ativo, BigDecimal saldo) {
		Perfil perfil = this.getPerfil(persona);
		Usuario usuario = new Usuario();
		usuario.setNome(email);
		usuario.setSobrenome(uuid());
		usuario.setEmail(email);
		usuario.setAtivo(ativo);
		usuario.setPerfil(perfil);
		usuario.setHashSenha(this.encoder.encode(senha));
		usuario.setCpf(cpf);


		usuario = usuarioRepository.save(usuario);

		return usuario;
	}

	/**
	 * Cria um e-mail randômico para os testes
	 * @return
	 */
	protected String email() {
		return uuid() + "@gmail.com";
	}

	/**
	 * Cria um CPF randomico
	 * @return
	 */
	protected String cpf() {
		return new GeraCpfCnpj().cpf(false);
	}

	protected String senha() {
		return "12*$" + uuid();
	}
}
