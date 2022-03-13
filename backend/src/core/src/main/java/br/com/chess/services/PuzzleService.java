package br.com.chess.services;

import br.com.chess.UtilConstantes;
import br.com.chess.domain.PuzzleDiario;
import br.com.chess.domain.enums.TipoPlataforma;
import br.com.chess.dto.PuzzleDiarioDto;
import br.com.chess.exceptions.IntegrationError;
import br.com.chess.repositories.PuzzleDiarioRepository;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static br.com.chess.UtilConstantes.TIME_ZONE;

@Component
public class PuzzleService {

    private final ProducerTemplate producerTemplate;
    private static final Logger logger = LoggerFactory.getLogger("PuzzleService");
    private final PuzzleDiarioRepository puzzleDiarioRepository;

    @Autowired
    public PuzzleService(ProducerTemplate producerTemplate, PuzzleDiarioRepository puzzleDiarioRepository) {
        this.producerTemplate = producerTemplate;
        this.puzzleDiarioRepository = puzzleDiarioRepository;
    }

    private PuzzleDiarioDto buscarPuzzleDiario(TipoPlataforma type) throws IntegrationError {
        PuzzleDiarioDto puzzleDiarioDto = null;
        try{
            puzzleDiarioDto = (PuzzleDiarioDto)producerTemplate.requestBodyAndHeader("direct://puzzles-diarios","plataforma", type );
        }catch(Exception e){
            throw new IntegrationError(UtilConstantes.CHESS_COM, e.getMessage());
        }
        return puzzleDiarioDto;
    }

    @Scheduled(cron = "0 0 3 * * ?", zone = TIME_ZONE)
    public void atualizarPuzzles() throws IntegrationError {
        logger.info("Atualizando puzzles da plataforma");
        for(TipoPlataforma plataforma:TipoPlataforma.values()){
            this.puzzleDiarioRepository.save(new PuzzleDiario(this.buscarPuzzleDiario(plataforma), plataforma));
        }
    }
}
