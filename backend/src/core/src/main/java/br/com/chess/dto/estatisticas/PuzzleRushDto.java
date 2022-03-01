package br.com.chess.dto.estatisticas;

import br.com.chess.domain.estaticas.QuebraCabecas;

public class PuzzleRushDto {

    private PuzzleRushStatsDto best;

    public PuzzleRushDto() {
        // Construtor padrao
    }

    public PuzzleRushDto(QuebraCabecas quebraCabecas) {
        this.best = new PuzzleRushStatsDto(quebraCabecas);
    }

    public PuzzleRushStatsDto getBest() {
        return best;
    }

    public void setBest(PuzzleRushStatsDto best) {
        this.best = best;
    }
}
