package br.com.chess.cammel;

import br.com.chess.cammel.processor.buscarJogadores.BuscarJogadorChessComProcessor;
import br.com.chess.cammel.processor.buscarJogadores.BuscarJogadoresProcessorLichess;
import br.com.chess.cammel.processor.ErroProcessor;
import br.com.chess.domain.enums.TipoPlataforma;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscarJogadoresRoute extends RouteBuilder {

    private final BuscarJogadoresProcessorLichess buscarJogadoresProcessorLichess;
    private final ErroProcessor erroProcessor;
    private final BuscarJogadorChessComProcessor buscarJogadorChessComProcessor;

    @Autowired
    public BuscarJogadoresRoute(BuscarJogadoresProcessorLichess buscarJogadoresProcessorLichess, ErroProcessor erroProcessor, BuscarJogadorChessComProcessor buscarJogadorChessComProcessor) {
        this.buscarJogadoresProcessorLichess = buscarJogadoresProcessorLichess;
        this.erroProcessor = erroProcessor;
        this.buscarJogadorChessComProcessor = buscarJogadorChessComProcessor;
    }


    @Override
    public void configure() throws Exception {
        from("direct://buscar-jogador").id("jogadoresBusca")
                .choice()
                .when(header("plataforma").isEqualTo(TipoPlataforma.CHESS_COM)).to("direct:buscar-jogador-chess-com")
//                .when(header("plataforma").isEqualTo(TipoPlataforma.LICHESS)).to("direct:buscar-jogador-liches")
                .otherwise()
                .to("direct:erro");

            from("direct:buscar-jogador-chess-com").id("jogadorChessCom").process(buscarJogadorChessComProcessor);
            from("direct:buscar-jogador-lichess").id("jogadorLichess").process(buscarJogadoresProcessorLichess);
            from("direct:erro").id("erro").process(erroProcessor);

    }
}
