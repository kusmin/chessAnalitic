package br.com.chess.services;

import br.com.chess.BaseTest;
import br.com.chess.domain.PuzzleDiario;
import br.com.chess.exceptions.IntegrationError;
import br.com.chess.repositories.PuzzleDiarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PuzzleServiceTest extends BaseTest {

    @Autowired
    private PuzzleService puzzleService;

    @Autowired
    private PuzzleDiarioRepository puzzleDiarioRepository;

    /**
     * Testando o job de atualização de puzzles
     **/
    @Test
    public void testandoJobPuzzles() throws IntegrationError {
        this.puzzleService.atualizarPuzzles();
        List<PuzzleDiario> puzzleDiario = this.puzzleDiarioRepository.findAll();
        assertNotNull(puzzleDiario);
        assertFalse(puzzleDiario.isEmpty());
    }
}
