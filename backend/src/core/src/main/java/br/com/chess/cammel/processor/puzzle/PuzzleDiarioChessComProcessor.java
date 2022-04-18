package br.com.chess.cammel.processor.puzzle;

import br.com.chess.dto.PuzzleDiarioDto;
import br.com.chess.exceptions.IntegrationError;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

@Component
public class PuzzleDiarioChessComProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger("PuzzleDiarioChessComProcessor");
    private final RestOperations restTemplate;


    @Autowired
    public PuzzleDiarioChessComProcessor(@Qualifier("CHESSCOM") RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        try {
            logger.info("Vai ser buscado puzzle diario");
            ResponseEntity<PuzzleDiarioDto> response = restTemplate.getForEntity("/puzzle",
                    PuzzleDiarioDto.class);


            exchange.getMessage().setBody(response.getBody());
        } catch (Exception e) {
            throw new IntegrationError("Integration", String.format("Erro ao buscar jogador %s", e));
        }

    }
}
