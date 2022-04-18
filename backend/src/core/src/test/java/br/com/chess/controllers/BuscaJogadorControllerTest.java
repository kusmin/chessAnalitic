package br.com.chess.controllers;

import br.com.chess.BaseTest;
import br.com.chess.domain.Jogador;
import br.com.chess.domain.enums.TipoPlataforma;
import br.com.chess.dto.Autorizacao;
import br.com.chess.dto.Erro;
import br.com.chess.dto.JogadorResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
    void aoBuscarJogadorCasoUsuarioNaoTenhaAutorizacaoRetornar403() {

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
     * Busca Jogador, com sucesso criando e editando no chess.com
     */

    @Test
    void aoBuscarJogadorComSuceso() {
        Autorizacao auth = criarAdministrador();

        // Sem autorização
        for (String username : JOGADORES) {
            JogadorResponseDto jogadorResponseDto = this.testClient.get().uri(String.format("/api/v1/player/%s/platform/%s", username, TipoPlataforma.CHESS_COM))
                    .header("Authorization", auth.getToken())
                    .exchange().expectStatus().isEqualTo(HttpStatus.OK)
                    .expectBody(JogadorResponseDto.class).returnResult().getResponseBody();

            assertNotNull(jogadorResponseDto);
            assertNotNull(jogadorResponseDto.getJogador());
            assertNotNull(jogadorResponseDto.getEstatisticas());
            assertNotNull(jogadorResponseDto.getJogador().getUsername());
            assertEquals(username, jogadorResponseDto.getJogador().getUsername());

            Jogador jogador = this.jogadorRepository.findByUsernameAndTipo(username, TipoPlataforma.CHESS_COM);
            assertNotNull(jogador);
            assertEquals(username, jogador.getUsername());
            jogador.setDataUltimaAtualizacao(this.alterarEdicao(jogador.getDataUltimaAtualizacao()));
            jogadorRepository.save(jogador);
        }
        // Edicao
        for (String username : JOGADORES) {
            JogadorResponseDto jogadorResponseDto = this.testClient.get().uri(String.format("/api/v1/player/%s/platform/%s", username, TipoPlataforma.CHESS_COM))
                    .header("Authorization", auth.getToken())
                    .exchange().expectStatus().isEqualTo(HttpStatus.OK)
                    .expectBody(JogadorResponseDto.class).returnResult().getResponseBody();
            assertNotNull(jogadorResponseDto);
            assertNotNull(jogadorResponseDto.getJogador());
            assertNotNull(jogadorResponseDto.getEstatisticas());
            assertNotNull(jogadorResponseDto.getJogador().getUsername());
            assertEquals(username, jogadorResponseDto.getJogador().getUsername());
        }
    }

    /**
     * GET - /api/v1/player/{user}/platform/{type}
     * Busca Jogador, com sucesso todos os gms
     */

    @Test
    void aoBuscarJogadorComSucesoGMS() {
        Autorizacao auth = criarAdministrador();

        for (String username : JOGADORES_GM) {
            JogadorResponseDto jogadorResponseDto = this.testClient.get().uri(String.format("/api/v1/player/%s/platform/%s", username, TipoPlataforma.CHESS_COM))
                    .header("Authorization", auth.getToken())
                    .exchange().expectStatus().isEqualTo(HttpStatus.OK)
                    .expectBody(JogadorResponseDto.class).returnResult().getResponseBody();

            assertNotNull(jogadorResponseDto);
            assertNotNull(jogadorResponseDto.getEstatisticas());
            assertNotNull(jogadorResponseDto.getJogador().getUsername());
            assertEquals(username, jogadorResponseDto.getJogador().getUsername());

            Jogador jogador = this.jogadorRepository.findByUsernameAndTipo(username, TipoPlataforma.CHESS_COM);
            assertNotNull(jogador);
            assertEquals(username, jogador.getUsername());
        }
    }

    /**
     * GET - /api/v1/player/{user}/platform/{type}
     * Busca Jogador, desconhecido retornar 404
     */

    @Test
    void aoBuscarJogadorDesconhecidoRetornar404() {
        Autorizacao auth = criarAdministrador();

        Erro erro = this.testClient.get().uri(String.format("/api/v1/player/%s/platform/%s", uuid(), TipoPlataforma.CHESS_COM))
                .header("Authorization", auth.getToken())
                .exchange().expectStatus().isEqualTo(HttpStatus.NOT_FOUND)
                .expectBody(Erro.class).returnResult().getResponseBody();

        assertNotNull(erro);
        assertEquals("NOT_FOUND", erro.getCode());
        assertEquals("Jogador não encontrado.", erro.getMessage());
    }

    /**
     * GET - /api/v1/player/{user}/platform/{type}/is-online
     * Busca Jogador online sem permissão
     */
    @Test
    void aoBuscarJogadorOnlineCasoUsuarioNaoTenhaAutorizacaoRetornar403() {

        // Sem autorização
        this.testClient.get().uri(String.format("/api/v1/player/%s/platform/%s/is-online", uuid(), uuid()))
                .header("Authorization", uuid())
                .exchange().expectStatus().isEqualTo(HttpStatus.FORBIDDEN);

        // sem token

        this.testClient.get().uri(String.format("/api/v1/player/%s/platform/%s/is-online", uuid(), uuid()))
                .exchange().expectStatus().isEqualTo(HttpStatus.FORBIDDEN);
    }

    /**
     * GET - /api/v1/player/{user}/platform/{type}/is-online
     * Busca Jogador online sem permissão
     */
    @Test
    void aoBuscarJogadorOnlineCasoDeSucesso() {
        Autorizacao auth = criarAdministrador();

        for (String username : JOGADORES_GM) {
            Boolean result = this.testClient.get().uri(String.format("/api/v1/player/%s/platform/%s/is-online", username, TipoPlataforma.CHESS_COM))
                    .header("Authorization", auth.getToken())
                    .exchange().expectStatus().isEqualTo(HttpStatus.OK)
                    .expectBody(Boolean.class).returnResult().getResponseBody();

            assertNotNull(result);
            System.out.println(result);
        }
    }
}
