package br.com.chess.dto.estatisticas;

import br.com.chess.domain.estaticas.QuebraCabecas;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PuzzleRushStatsDto {
    @JsonProperty("total_attempts")
    private long totalAttempts;
    private long score;

    public PuzzleRushStatsDto() {
        // Construtor padrao
    }

    public PuzzleRushStatsDto(QuebraCabecas quebraCabecas) {
        this.totalAttempts = quebraCabecas.getTotalTentativas();
        this.score = quebraCabecas.getScore();
    }

    public long getTotalAttempts() {
        return totalAttempts;
    }

    public void setTotalAttempts(long totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public PuzzleRushStatsDto(long totalAttempts) {
        this.totalAttempts = totalAttempts;
    }
}
