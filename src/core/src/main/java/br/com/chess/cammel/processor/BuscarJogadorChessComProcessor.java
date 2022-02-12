package br.com.chess.cammel.processor;

import br.com.chess.dto.JogadorDto;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

public class BuscarJogadorChessComProcessor implements Processor {

    private final RestOperations restTemplate;

    public BuscarJogadorChessComProcessor(@Qualifier("CHESS_COM")RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String username = exchange.getIn().getBody(String.class);

        ResponseEntity<JogadorDto> response = restTemplate.getForEntity(String.format("/player/%s", username),
                JogadorDto.class);

        exchange.getOut().setBody(response.getBody());
    }
}
