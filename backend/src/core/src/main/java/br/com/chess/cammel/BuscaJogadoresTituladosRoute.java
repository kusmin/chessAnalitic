package br.com.chess.cammel;

import br.com.chess.cammel.processor.ErroProcessor;
import br.com.chess.cammel.processor.buscar_jogadores_titulados.BuscarJogadoresTituladosChessCom;
import br.com.chess.domain.enums.TipoPlataforma;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscaJogadoresTituladosRoute extends RouteBuilder {
    private final ErroProcessor erroProcessor;

    private final BuscarJogadoresTituladosChessCom buscarJogadorChessComProcessor;

    @Autowired
    public BuscaJogadoresTituladosRoute(ErroProcessor erroProcessor, BuscarJogadoresTituladosChessCom buscarJogadorChessComProcessor) {
        this.erroProcessor = erroProcessor;
        this.buscarJogadorChessComProcessor = buscarJogadorChessComProcessor;
    }

    @Override
    public void configure() throws Exception {
        from("direct://jogador-titulados").id("jogadoresTitulados")
                .choice()
                .when(header("plataforma").isEqualTo(TipoPlataforma.CHESS_COM)).to("direct:titulados-chesscom")
                .otherwise()
                .to("direct:erro");

        from("direct:titulados-chesscom").id("jogadorChessComTitulados").process(buscarJogadorChessComProcessor);
        from("direct:erro").id("erro").process(erroProcessor);
    }
}
