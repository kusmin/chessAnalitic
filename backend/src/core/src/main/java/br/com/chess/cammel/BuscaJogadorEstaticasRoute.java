package br.com.chess.cammel;

import br.com.chess.cammel.processor.ErroProcessor;
import br.com.chess.cammel.processor.buscarEstatisticaJogador.BuscarEstatisticasJogadorChessComProcessor;
import br.com.chess.domain.enums.TipoPlataforma;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BuscaJogadorEstaticasRoute extends RouteBuilder {
    private final ErroProcessor erroProcessor;

    private final BuscarEstatisticasJogadorChessComProcessor buscarEstatisticasJogadorChessComProcessor;

    public BuscaJogadorEstaticasRoute(ErroProcessor erroProcessor, BuscarEstatisticasJogadorChessComProcessor buscarEstatisticasJogadorChessComProcessor) {
        this.erroProcessor = erroProcessor;
        this.buscarEstatisticasJogadorChessComProcessor = buscarEstatisticasJogadorChessComProcessor;
    }



    @Override
    public void configure() throws Exception {
        from("direct://buscar-jogador-estatisticas").id("estatisticasJogadores")
                .choice()
                .when(header("plataforma").isEqualTo(TipoPlataforma.CHESS_COM)).to("direct:estaticas-jogador-chesscom")
                .otherwise()
                .to("direct:erro");

        from("direct:estaticas-jogador-chesscom").id("jogadorChessComEstatisticas").process(buscarEstatisticasJogadorChessComProcessor);
        from("direct:erro").id("erro").process(erroProcessor);
    }
}
