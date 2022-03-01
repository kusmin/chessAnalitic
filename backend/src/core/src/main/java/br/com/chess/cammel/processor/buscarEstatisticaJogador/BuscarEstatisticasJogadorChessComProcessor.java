package br.com.chess.cammel.processor.buscarEstatisticaJogador;

import br.com.chess.dto.JogadorDto;
import br.com.chess.dto.estatisticas.EstatisticasDto;
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
public class BuscarEstatisticasJogadorChessComProcessor implements Processor {

    private final RestOperations restTemplate;

    private static final Logger logger = LoggerFactory.getLogger("BuscarJogadorChessComProcessor");


    @Autowired
    public BuscarEstatisticasJogadorChessComProcessor(@Qualifier("CHESSCOM")RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        try{
            String username = exchange.getIn().getBody(String.class);

            logger.info("Vai ser buscado as estatisticas do jogador de username {}", username);
            ResponseEntity<EstatisticasDto> response = restTemplate.getForEntity(String.format("/player/%s/stats", username),
                    EstatisticasDto.class);

            exchange.getMessage().setBody(response.getBody());
        }catch(Exception e){
            throw new IntegrationError("Integration", String.format("Erro ao buscar jogador %s", e));
        }

    }
}
