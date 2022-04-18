package br.com.chess.services;

import br.com.chess.BaseTest;
import br.com.chess.domain.PuzzleDiario;
import br.com.chess.exceptions.IntegrationError;
import br.com.chess.repositories.PuzzleDiarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PuzzleServiceTest extends BaseTest {

    @Autowired
    private PuzzleService puzzleService;

    @Autowired
    private PuzzleDiarioRepository puzzleDiarioRepository;

    /**
     * Testando o job de atualização de puzzles
     **/
    @Test
    void testandoJobPuzzles() throws IntegrationError {
        this.puzzleService.atualizarPuzzles();
        List<PuzzleDiario> puzzleDiario = this.puzzleDiarioRepository.findAll();
        assertNotNull(puzzleDiario);
        assertFalse(puzzleDiario.isEmpty());
    }
}
