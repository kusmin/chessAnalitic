package br.com.chess.cammel.processor.buscar_jogador_online;

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
public class BuscarJogadorOnlineChessComProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger("BuscarJogadorChessComProcessor");
    private final RestOperations restTemplate;


    @Autowired
    public BuscarJogadorOnlineChessComProcessor(@Qualifier("CHESSCOM") RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        try {
            String username = exchange.getIn().getBody(String.class);

            logger.info("Vai ser buscado se o jogador de username {} esta online", username);
            ResponseEntity<Boolean> response = restTemplate.getForEntity(String.format("/player/%s/is-online", username),
                    Boolean.class);


            exchange.getMessage().setBody(response.getBody());
        } catch (Exception e) {
            throw new IntegrationError("Integration", String.format("Erro ao buscar jogador %s", e));
        }

    }
}
