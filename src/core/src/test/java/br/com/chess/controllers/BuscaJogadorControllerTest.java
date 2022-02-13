package br.com.chess.controllers;

import br.com.chess.BaseTest;
import br.com.chess.domain.Jogador;
import br.com.chess.domain.TipoPlataforma;
import br.com.chess.dto.Autorizacao;
import br.com.chess.dto.Erro;
import br.com.chess.dto.JogadorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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
     * Busca Jogador, com sucesso criando e editando no chess.com
     */

    @Test
    void aoBuscarJogadorComSuceso(){
        Autorizacao auth = criarAdministrador();

        // Sem autorização
        for(String username:JOGADORES){
            JogadorDto jogadorDto = this.testClient.get().uri(String.format("/api/v1/player/%s/platform/%s",username, TipoPlataforma.CHESS_COM))
                    .header("Authorization", auth.getToken())
                    .exchange().expectStatus().isEqualTo(HttpStatus.OK)
                    .expectBody(JogadorDto.class).returnResult().getResponseBody();

            assertNotNull(jogadorDto);
            assertNotNull(jogadorDto.getUsername());
            assertEquals(username, jogadorDto.getUsername());

            Jogador jogador = this.jogadorRepository.findByUsernameAndTipo(username, TipoPlataforma.CHESS_COM);
            assertNotNull(jogador);
            assertEquals(username, jogador.getUsername());
            jogador.setDataUltimaAtualizacao(this.alterarEdicao(jogador.getDataUltimaAtualizacao()));
            jogadorRepository.save(jogador);
        }
        // Edicao
        for(String username:JOGADORES){
            JogadorDto jogadorDto = this.testClient.get().uri(String.format("/api/v1/player/%s/platform/%s",username, TipoPlataforma.CHESS_COM))
                    .header("Authorization", auth.getToken())
                    .exchange().expectStatus().isEqualTo(HttpStatus.OK)
                    .expectBody(JogadorDto.class).returnResult().getResponseBody();

            assertNotNull(jogadorDto);
            assertNotNull(jogadorDto.getUsername());
            assertEquals(username, jogadorDto.getUsername());
        }
    }

    /**
     * GET - /api/v1/player/{user}/platform/{type}
     * Busca Jogador, com sucesso todos os gms
     */

    @Test
    void aoBuscarJogadorComSucesoGMS(){
        Autorizacao auth = criarAdministrador();

        // Sem autorização
        for(String username:JOGADORES_GM){
            JogadorDto jogadorDto = this.testClient.get().uri(String.format("/api/v1/player/%s/platform/%s",username, TipoPlataforma.CHESS_COM))
                    .header("Authorization", auth.getToken())
                    .exchange().expectStatus().isEqualTo(HttpStatus.OK)
                    .expectBody(JogadorDto.class).returnResult().getResponseBody();

            assertNotNull(jogadorDto);
            assertNotNull(jogadorDto.getUsername());
            assertEquals(username, jogadorDto.getUsername());

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
    void aoBuscarJogadorDesconhecidoRetornar404(){
        Autorizacao auth = criarAdministrador();

        // Sem autorização
        Erro erro = this.testClient.get().uri(String.format("/api/v1/player/%s/platform/%s",uuid(), TipoPlataforma.CHESS_COM))
                .header("Authorization", auth.getToken())
                .exchange().expectStatus().isEqualTo(HttpStatus.NOT_FOUND)
                .expectBody(Erro.class).returnResult().getResponseBody();

        assertNotNull(erro);
        assertEquals("NOT_FOUND", erro.getCode());
        assertEquals("Jogador não encontrado.", erro.getMessage());
    }
}
