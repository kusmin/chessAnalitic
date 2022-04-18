package br.com.chess.services;

import org.apache.camel.CamelContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LichessServiceTest {

    @Autowired
    private CamelContext camelContext;

    /**
     * Buscando retorno ao buscar jogadores no lichesss
     **/
    @Test
    void buscarJogadoresLichess() {
        String result = (String) this.camelContext.createFluentProducerTemplate().to("direct:jogadores").request();
        assertNotNull(result);
    }
}
