package br.com.chess.controllers;

import br.com.chess.BaseTest;
import br.com.chess.domain.TipoPlataforma;
import br.com.chess.dto.Autorizacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.time.Duration;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class BuscaJogadorControllerTest extends BaseTest {
    @BeforeEach
    public void beforeEach() {
        this.testClient = testClient.mutate().responseTimeout(Duration.ofHours(4)).build();
    }

    /**
     * GET - /api/v1/player/{user}/platform/{type}
     * Busca Jogador, caso nao tenha autorização retorna 403
     */

    @Test
    void aoBuscarJogadorCasoUsuarioNaoTenhaAutorizacaoRetornar403(){

        // Sem autorização
        this.testClient.get().uri(String.format("/api/v1/player/%s/platform/%s", uuid(), uuid()))
                .header("Authorization", uuid())
                .exchange().expectStatus().isEqualTo(HttpStatus.FORBIDDEN);

        // sem token

        this.testClient.get().uri(String.format("/api/v1/player/%s/platform/%s", uuid(), uuid()))
                .exchange().expectStatus().isEqualTo(HttpStatus.FORBIDDEN);
    }

    /**
     * GET - /api/v1/player/{user}/platform/{type}
     * Busca Jogador, com sucesso
     */

    @Test
    void aoBuscarJogadorComSuceso(){
        Autorizacao auth = criarAdministrador();

        // Sem autorização
        this.testClient.get().uri(String.format("/api/v1/player/%s/platform/%s","kusmin", TipoPlataforma.CHESS_COM))
                .header("Authorization", uuid())
                .exchange().expectStatus().isEqualTo(HttpStatus.OK);

    }
}
