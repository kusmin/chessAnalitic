package br.com.chess.dto.estatisticas;

import br.com.chess.domain.estaticas.EstatisticaJogador;

import java.util.List;
import java.util.stream.Collectors;

public class EstatisticaResponseDto {
    private List<ModalidadeResponseDto> modalidades;
    private long ratingFide;
    private List<EstudoResponseDto> estudos;
    private PuzzleRushDto puzzle;

    public EstatisticaResponseDto(EstatisticaJogador estatisticaJogador) {
        this.ratingFide = estatisticaJogador.getFide();
        if (estatisticaJogador.getModalidades() != null && !estatisticaJogador.getModalidades().isEmpty()) {
            this.modalidades = estatisticaJogador.getModalidades().stream().map(ModalidadeResponseDto::new).collect(Collectors.toList());
        }
        if (estatisticaJogador.getQuebraCabecas() != null) {
            this.puzzle = new PuzzleRushDto(estatisticaJogador.getQuebraCabecas());
        }
        if (estatisticaJogador.getEstudos() != null && !estatisticaJogador.getEstudos().isEmpty()) {
            this.estudos = estatisticaJogador.getEstudos().stream().map(EstudoResponseDto::new).collect(Collectors.toList());
        }
    }

    public EstatisticaResponseDto() {
        // Construtor padrão
    }

    public List<ModalidadeResponseDto> getModalidades() {
        return modalidades;
    }

    public void setModalidades(List<ModalidadeResponseDto> modalidades) {
        this.modalidades = modalidades;
    }

    public long getRatingFide() {
        return ratingFide;
    }

    public void setRatingFide(long ratingFide) {
        this.ratingFide = ratingFide;
    }

    public List<EstudoResponseDto> getEstudos() {
        return estudos;
    }

    public void setEstudos(List<EstudoResponseDto> estudos) {
        this.estudos = estudos;
    }

    public PuzzleRushDto getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(PuzzleRushDto puzzle) {
        this.puzzle = puzzle;
    }
}
