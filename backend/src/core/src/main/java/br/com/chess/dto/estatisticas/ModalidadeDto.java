package br.com.chess.dto.estatisticas;

import br.com.chess.domain.estaticas.Modalidade;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ModalidadeDto {

    private EstatisticaGameDto last;
    private EstatisticaGameDto best;
    @JsonProperty("record")
    private RecordeModalidadeDto recorde;
    private TorneioDto tournament;

    public ModalidadeDto() {
        // Construtor padrao
    }

    public ModalidadeDto(Modalidade modalidade) {
        this.last = new EstatisticaGameDto(modalidade.getUltimaPartida());
        this.best = new EstatisticaGameDto(modalidade.getMelhorPartida());
        this.recorde = new RecordeModalidadeDto(modalidade);
    }

    public TorneioDto getTournament() {
        return tournament;
    }

    public void setTournament(TorneioDto tournament) {
        this.tournament = tournament;
    }

    public EstatisticaGameDto getLast() {
        return last;
    }

    public void setLast(EstatisticaGameDto last) {
        this.last = last;
    }

    public EstatisticaGameDto getBest() {
        return best;
    }

    public void setBest(EstatisticaGameDto best) {
        this.best = best;
    }

    public RecordeModalidadeDto getRecorde() {
        return recorde;
    }

    public void setRecorde(RecordeModalidadeDto recorde) {
        this.recorde = recorde;
    }
}
