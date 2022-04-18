package br.com.chess.dto;

import br.com.chess.domain.Jogador;
import br.com.chess.dto.estatisticas.EstatisticaResponseDto;

public class JogadorResponseDto {
    private JogadorDto jogador;

    private EstatisticaResponseDto estatisticas;

    public JogadorResponseDto() {
        // Construtor padrão
    }

    public JogadorResponseDto(Jogador jogador) {
        this.jogador = new JogadorDto(jogador);
        if (jogador.getEstatisticaJogador() != null) {
            this.estatisticas = new EstatisticaResponseDto(jogador.getEstatisticaJogador());
        }
    }

    public JogadorDto getJogador() {
        return jogador;
    }

    public void setJogador(JogadorDto jogador) {
        this.jogador = jogador;
    }

    public EstatisticaResponseDto getEstatisticas() {
        return estatisticas;
    }

    public void setEstatisticas(EstatisticaResponseDto estatisticas) {
        this.estatisticas = estatisticas;
    }
}
