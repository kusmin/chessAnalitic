package br.com.chess.dto.estatisticas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TorneioDto {
    private long points;
    private long withdraw;
    private long count;
    @JsonProperty("highest_finish")
    private long highestFinish;

    public TorneioDto() {
        // Construtor padrao
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public long getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(long withdraw) {
        this.withdraw = withdraw;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getHighestFinish() {
        return highestFinish;
    }

    public void setHighestFinish(long highestFinish) {
        this.highestFinish = highestFinish;
    }
}
