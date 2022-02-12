package br.com.chess.cammel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

@Component
public class BuscarJogadoresProcessorLichess implements Processor {
    private static final Logger logger = LoggerFactory.getLogger(BuscarJogadoresProcessorLichess.class);

    private final RestOperations restTemplate;

    public BuscarJogadoresProcessorLichess(@Qualifier("LICHESS")RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Começando a busca de jogadores");

        RequestEntity request = RequestEntity.get("/player")
                .accept(MediaType.APPLICATION_JSON).build();

        ResponseEntity<String> response = restTemplate.exchange("/player", HttpMethod.GET, request, new ParameterizedTypeReference<String>() {
        });
        System.out.println(response.getBody());
    }
}
