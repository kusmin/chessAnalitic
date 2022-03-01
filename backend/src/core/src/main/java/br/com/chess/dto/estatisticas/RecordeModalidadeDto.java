package br.com.chess.dto.estatisticas;

import br.com.chess.domain.estaticas.Modalidade;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RecordeModalidadeDto {
    private long win;
    private long loss;
    private long draw;
    @JsonProperty("time_per_move")
    private long timePerMove;
    @JsonProperty("timeout_percent")
    private long timeoutPercent;

    public RecordeModalidadeDto() {
        // Construtor padrao
    }

    public RecordeModalidadeDto(Modalidade modalidade) {
    }

    public long getWin() {
        return win;
    }

    public void setWin(long win) {
        this.win = win;
    }

    public long getLoss() {
        return loss;
    }

    public void setLoss(long loss) {
        this.loss = loss;
    }

    public long getDraw() {
        return draw;
    }

    public void setDraw(long draw) {
        this.draw = draw;
    }

    public long getTimePerMove() {
        return timePerMove;
    }

    public void setTimePerMove(long timePerMove) {
        this.timePerMove = timePerMove;
    }

    public long getTimeoutPercent() {
        return timeoutPercent;
    }

    public void setTimeoutPercent(long timeoutPercent) {
        this.timeoutPercent = timeoutPercent;
    }
}
