package br.com.chess.cammel;

import br.com.chess.cammel.processor.ErroProcessor;
import br.com.chess.cammel.processor.buscar_jogador_online.BuscarJogadorOnlineChessComProcessor;
import br.com.chess.domain.enums.TipoPlataforma;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscarJogadorOnlineRoute extends RouteBuilder {
    private final ErroProcessor erroProcessor;

    private final BuscarJogadorOnlineChessComProcessor buscarJogadorOnlineChessComProcessor;

    @Autowired
    public BuscarJogadorOnlineRoute(ErroProcessor erroProcessor, BuscarJogadorOnlineChessComProcessor buscarJogadorOnlineChessComProcessor) {
        this.erroProcessor = erroProcessor;
        this.buscarJogadorOnlineChessComProcessor = buscarJogadorOnlineChessComProcessor;
    }

    @Override
    public void configure() throws Exception {
        from("direct://buscar-jogador-online").id("onlineJogadores")
                .choice()
                .when(header("plataforma").isEqualTo(TipoPlataforma.CHESS_COM)).to("direct:online-jogador-chesscom")
                .otherwise()
                .to("direct:erro");

        from("direct:online-jogador-chesscom").id("jogadorChessComOnline").process(buscarJogadorOnlineChessComProcessor);
        from("direct:erro").id("erro").process(erroProcessor);
    }
}
