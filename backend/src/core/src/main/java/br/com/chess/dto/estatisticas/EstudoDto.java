package br.com.chess.dto.estatisticas;

import br.com.chess.domain.estaticas.Estudo;

public class EstudoDto {
    private BaseEstatisticasDto highest;

    private BaseEstatisticasDto lowest;

    public EstudoDto() {
        // Construtor padrao
    }

    public EstudoDto(Estudo estudo) {
        if(estudo.getMelhor() != null){
            this.highest = new BaseEstatisticasDto(estudo.getMelhor().getRating(), estudo.getMelhor().getData());
        }
        if(estudo.getPior() != null){
            this.lowest = new BaseEstatisticasDto(estudo.getPior().getRating(), estudo.getPior().getData());
        }
    }

    public BaseEstatisticasDto getHighest() {
        return highest;
    }

    public void setHighest(BaseEstatisticasDto highest) {
        this.highest = highest;
    }

    public BaseEstatisticasDto getLowest() {
        return lowest;
    }

    public void setLowest(BaseEstatisticasDto lowest) {
        this.lowest = lowest;
    }
}
