package br.com.chess.services;

import br.com.chess.BaseTest;
import br.com.chess.domain.ListaJogadoresTitulados;
import br.com.chess.exceptions.IntegrationError;
import br.com.chess.exceptions.NotFoundError;
import br.com.chess.repositories.JogadorRepository;
import br.com.chess.repositories.ListaJogadoresTituladosRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JogadorServiceTest extends BaseTest {

    @Autowired
    private JogadorService service;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private ListaJogadoresTituladosRepository lIstaJogadoresTituladosRepository;

    /**
     * Buscando retorno ao buscar jogadores no titulados
     **/
    @Test
    void buscarJogadoresTitulos() throws IntegrationError, NotFoundError {
        this.service.atualizarListaJogadoresTitulados();

        List<ListaJogadoresTitulados> jogadores = this.lIstaJogadoresTituladosRepository.findAll();
        assertNotNull(jogadores);
        assertFalse(jogadores.isEmpty());
    }
}
