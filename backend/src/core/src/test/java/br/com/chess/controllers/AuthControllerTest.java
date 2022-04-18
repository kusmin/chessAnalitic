package br.com.chess.controllers;

import br.com.chess.BaseTest;
import br.com.chess.domain.Permissao;
import br.com.chess.domain.Usuario;
import br.com.chess.dto.Autorizacao;
import br.com.chess.dto.Credenciais;
import br.com.chess.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AuthControllerTest extends BaseTest {

    @Autowired
    private AuthController controller;

    @Autowired
    private WebTestClient testClient;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Test
    void testAutenticacaoBemSucedida() {

        Credenciais credenciais = new Credenciais("renan.lagee@gmail.com", "admin1234");
        ResponseEntity<Autorizacao> result = controller.autenticar(credenciais);

        assertEquals(200, result.getStatusCodeValue(), "Deveria ter retornado o código 200");
        Autorizacao autorizacao = result.getBody();
        assertNotNull(autorizacao, "Não retornou as autorizações");
        assertEquals(credenciais.getUsername(), autorizacao.getUsername(), "Retornou o username incorreto");
        assertNotNull(autorizacao.getToken(), "Não nos retornou um token");
        assertNotNull(autorizacao.getName(), "Não retornou o nome do usuário");

        Usuario usuario = usuarioRepository.findByEmail(autorizacao.getUsername());
        assertNotNull(usuario, "O registro do usuário não existe no banco de dados");
        for (Permissao permissao : usuario.getPerfil().getPermissoes()) {
            Optional<String> role = autorizacao.getRoles().stream().filter(p -> p.equals(permissao.getAuthority())).findFirst();
            assertTrue(role.isPresent());
            assertNotNull(role.get(), "A permissão " + permissao.getAuthority() + " não foi encontrada");
        }


        /*
         * Se a autenticação está correta, consigo acessar um endpoint de teste
         */
        this.testClient.get().uri("/api/v1/test")
                .header("Authorization", autorizacao.getToken())
                .exchange().expectStatus().isOk();
        /*
         * Se fornecemos um token inválido, não devo conseguir acessar este conteúdo
         */
        this.testClient.get().uri("/api/v1/test")
                .header("Authorization", "invalido")
                .exchange().expectStatus().isForbidden();
    }

    @Test
    void testAutenticacaoMalSucedida() {
        Credenciais credenciais = new Credenciais(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        ResponseEntity<Autorizacao> result = controller.autenticar(credenciais);
        assertEquals(401, result.getStatusCodeValue(), "Deveria me retornar o código 401");
        assertNull(result.getBody(), "A resposta deveria vir vazia");
    }
}
