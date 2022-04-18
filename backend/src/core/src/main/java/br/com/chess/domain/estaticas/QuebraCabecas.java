package br.com.chess.domain.estaticas;

import br.com.chess.domain.BaseDomain;
import br.com.chess.dto.estatisticas.PuzzleRushDto;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Audited
@Table(name = "quebra_cabecas")
public class QuebraCabecas extends BaseDomain {


    private static final long serialVersionUID = 7870666971131492935L;
    @Column(name = "totalAtentivas", nullable = true)
    private long totalTentativas;

    @Column(name = "score", nullable = true)
    private long score;

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "quebra_cabecas_estatistica_jogador",
            joinColumns = @JoinColumn(name = "quebra_cabecas_id"),
            inverseJoinColumns = @JoinColumn(name = "estatistica_jogador_id"))
    private EstatisticaJogador estatisticaJogador;

    public QuebraCabecas() {
        // Construtor padrao
    }

    public QuebraCabecas(EstatisticaJogador estatisticaJogador, PuzzleRushDto puzzle) {
        super();
        this.estatisticaJogador = estatisticaJogador;
        if (puzzle != null && puzzle.getBest() != null) {
            this.totalTentativas = puzzle.getBest().getTotalAttempts();
            this.score = puzzle.getBest().getScore();
        }
    }

    public EstatisticaJogador getEstatisticaJogador() {
        return estatisticaJogador;
    }

    public void setEstatisticaJogador(EstatisticaJogador estatisticaJogador) {
        this.estatisticaJogador = estatisticaJogador;
    }

    public long getTotalTentativas() {
        return totalTentativas;
    }

    public void setTotalTentativas(long totalTentativas) {
        this.totalTentativas = totalTentativas;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        QuebraCabecas that = (QuebraCabecas) o;
        return getTotalTentativas() == that.getTotalTentativas() && getScore() == that.getScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTotalTentativas(), getScore());
    }
}
