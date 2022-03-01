package br.com.chess.dto.estatisticas;


import br.com.chess.domain.estaticas.Modalidade;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ModalidadeResponseDto extends ModalidadeDto{
    private long totalGames;
    private BigDecimal percentageWins;
    private BigDecimal percentageLoss;
    private BigDecimal percentageDraw;
    private String type;

    public ModalidadeResponseDto() {
        // Construtor padrao
    }

    public ModalidadeResponseDto(Modalidade modalidade) {
        super(modalidade);
        this.type = modalidade.getTipo().name();
        this.totalGames = modalidade.getDerrotas() + modalidade.getEmpates() + modalidade.getVitorias();
        this.percentageWins = this.calcularPorcentagem(this.totalGames, modalidade.getVitorias());
        this.percentageLoss = this.calcularPorcentagem(this.totalGames, modalidade.getDerrotas());
        this.percentageDraw = this.calcularPorcentagem(this.totalGames, modalidade.getEmpates());
    }

    private BigDecimal calcularPorcentagem(long totalGames, long games) {
        return new BigDecimal(games).divide(new BigDecimal(totalGames), 2, RoundingMode.HALF_UP);
    }

    public long getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(long totalGames) {
        this.totalGames = totalGames;
    }

    public BigDecimal getPercentageWins() {
        return percentageWins;
    }

    public void setPercentageWins(BigDecimal percentageWins) {
        this.percentageWins = percentageWins;
    }

    public BigDecimal getPercentageLoss() {
        return percentageLoss;
    }

    public void setPercentageLoss(BigDecimal percentageLoss) {
        this.percentageLoss = percentageLoss;
    }

    public BigDecimal getPercentageDraw() {
        return percentageDraw;
    }

    public void setPercentageDraw(BigDecimal percentageDraw) {
        this.percentageDraw = percentageDraw;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
