package br.com.chess.cammel;

import br.com.chess.cammel.processor.ErroProcessor;
import br.com.chess.cammel.processor.puzzle.PuzzleDiarioChessComProcessor;
import br.com.chess.domain.enums.TipoPlataforma;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscarPuzzleRoute extends RouteBuilder {

    private final ErroProcessor erroProcessor;
    private final PuzzleDiarioChessComProcessor puzzleDiarioChessComProcessor;

    @Autowired
    public BuscarPuzzleRoute(ErroProcessor erroProcessor, PuzzleDiarioChessComProcessor puzzleDiarioChessComProcessor) {
        this.erroProcessor = erroProcessor;
        this.puzzleDiarioChessComProcessor = puzzleDiarioChessComProcessor;
    }

    @Override
    public void configure() throws Exception {
        from("direct://puzzles-diarios").id("puzzlesDiarios")
                .choice()
                .when(header("plataforma").isEqualTo(TipoPlataforma.CHESS_COM)).to("direct://puzzlesDiarios-chesscom")
                .otherwise()
                .to("direct:erro");

        from("direct://puzzlesDiarios-chesscom").id("puzzlesDiariosChessCom").process(puzzleDiarioChessComProcessor);
        from("direct:erro").id("erro").process(erroProcessor);
    }
}
