package br.com.chess.dto.estatisticas;

import br.com.chess.domain.estaticas.Estudo;

public class EstudoResponseDto extends EstudoDto {

    private String type;

    public EstudoResponseDto(Estudo estudo) {
        super(estudo);
        type = estudo.getTipo().name();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
