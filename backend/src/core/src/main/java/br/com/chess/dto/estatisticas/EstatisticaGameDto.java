package br.com.chess.dto.estatisticas;

import br.com.chess.domain.estaticas.EstatisticaModalidade;

public class EstatisticaGameDto extends BaseEstatisticasDto {
    private long rd;

    private String game;

    public EstatisticaGameDto() {
        // Construtor padrao
    }

    public EstatisticaGameDto(EstatisticaModalidade estatisticaModalidade) {
        super(estatisticaModalidade != null ? estatisticaModalidade.getRating() : null, estatisticaModalidade != null ? estatisticaModalidade.getData() : null);
        if (estatisticaModalidade.getGame() != null) {
            this.game = estatisticaModalidade.getGame();
        }
        this.rd = estatisticaModalidade.getRd();
    }

    public long getRd() {
        return rd;
    }

    public void setRd(long rd) {
        this.rd = rd;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
