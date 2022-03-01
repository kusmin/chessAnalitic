package br.com.chess.domain.estaticas;

import br.com.chess.domain.BaseDomain;
import br.com.chess.domain.enums.TipoEstudo;
import br.com.chess.dto.estatisticas.EstudoDto;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Audited
@Table(name="tatica")
public class Estudo extends BaseDomain {

    private static final long serialVersionUID = -1264718260425764676L;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoEstudo tipo;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinColumn(name = "melhor_id")
    private EstatisticaModalidade melhor;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinColumn(name = "pior_id")
    private EstatisticaModalidade pior;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "estatistica_jogador_id")
    private EstatisticaJogador estatisticaJogador;

    public Estudo(EstatisticaJogador estatisticaJogador, TipoEstudo tipoEstudo, EstudoDto estudoDto) {
        super();
        this.tipo = tipoEstudo;
        this.estatisticaJogador = estatisticaJogador;
        if(estudoDto.getHighest() != null){
            this.melhor = new EstatisticaModalidade(estudoDto.getHighest());
        }
        if(estudoDto.getLowest() != null){
            this.pior = new EstatisticaModalidade(estudoDto.getLowest());
        }
    }

    public EstatisticaJogador getEstatisticaJogador() {
        return estatisticaJogador;
    }

    public void setEstatisticaJogador(EstatisticaJogador estatisticaJogador) {
        this.estatisticaJogador = estatisticaJogador;
    }

    public Estudo() {
        // Construtor Padrao
    }

    public EstatisticaModalidade getPior() {
        return pior;
    }

    public EstatisticaModalidade getMelhor() {
        return melhor;
    }

    public void setMelhor(EstatisticaModalidade melhor) {
        this.melhor = melhor;
    }

    public void setPior(EstatisticaModalidade pior) {
        this.pior = pior;
    }

    public TipoEstudo getTipo() {
        return tipo;
    }

    public void setTipo(TipoEstudo tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Estudo estudo = (Estudo) o;
        return getTipo() == estudo.getTipo() && Objects.equals(getEstatisticaJogador(), estudo.getEstatisticaJogador());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTipo(), getEstatisticaJogador());
    }
}
