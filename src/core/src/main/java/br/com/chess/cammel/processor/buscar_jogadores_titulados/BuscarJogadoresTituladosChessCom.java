package br.com.chess.cammel.processor.buscarJogadoresTitulados;

import br.com.chess.dto.PlayersTituladosDto;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

@Component
public class BuscarJogadoresTituladosChessCom implements Processor {

    private final RestOperations restTemplate;

    @Autowired
    public BuscarJogadoresTituladosChessCom(@Qualifier("CHESSCOM")RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public void process(Exchange exchange) throws Exception {
        String title = exchange.getIn().getBody(String.class);

        ResponseEntity<PlayersTituladosDto> response = restTemplate.getForEntity(String.format("/titled/%s", title),
                PlayersTituladosDto.class);


        exchange.getMessage().setBody(response.getBody());
    }
}
