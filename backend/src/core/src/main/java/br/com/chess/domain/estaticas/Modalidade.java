package br.com.chess.domain.estaticas;

import br.com.chess.domain.BaseDomain;
import br.com.chess.domain.enums.TipoModalidade;
import br.com.chess.dto.estatisticas.ModalidadeDto;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Audited
@Table(name="modalidade")
public class Modalidade extends BaseDomain {
    private static final long serialVersionUID = -3646380255194961867L;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoModalidade tipo;

    @Column(name ="vitorias", nullable = true)
    private long vitorias;

    @Column(name ="derrotas", nullable = true)
    private long derrotas;

    @Column(name ="empates", nullable = true)
    private long empates;

    @Column(name ="total_partidas", nullable = true)
    private long totalPartidas;

    @Column(name ="tempo_movimento", nullable = true)
    private long tempoPorMovimento;

    @Column(name ="derrotas_tempo", nullable = true)
    private long derrotasTempo;

    @ManyToOne
    @JoinColumn(name = "estatisticaJogador_id")
    private EstatisticaJogador estatisticaJogador;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinColumn(name = "melhor_partida_id")
    private EstatisticaModalidade melhorPartida;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinColumn(name = "ultima_partida_id")
    private EstatisticaModalidade ultimaPartida;

    public Modalidade() {
        // Construtor padrao
    }

    public Modalidade(EstatisticaJogador estatisticaJogador, TipoModalidade tipoModalidade, ModalidadeDto modalidadeDto) {
        super();
        this.estatisticaJogador = estatisticaJogador;
        this.tipo = tipoModalidade;
        if(modalidadeDto.getRecorde() != null){
            this.vitorias = modalidadeDto.getRecorde().getWin();
            this.derrotas = modalidadeDto.getRecorde().getLoss();
            this.empates = modalidadeDto.getRecorde().getDraw();
            this.tempoPorMovimento = modalidadeDto.getRecorde().getTimePerMove();
            this.derrotasTempo = modalidadeDto.getRecorde().getTimeoutPercent();
        }
        if(modalidadeDto.getBest() != null){
            this.melhorPartida = new EstatisticaModalidade(modalidadeDto.getBest());
        }
        if(modalidadeDto.getLast() != null){
            this.ultimaPartida = new EstatisticaModalidade(modalidadeDto.getLast());
        }

    }

    public EstatisticaModalidade getUltimaPartida() {
        return ultimaPartida;
    }

    public EstatisticaModalidade getMelhorPartida() {
        return melhorPartida;
    }

    public TipoModalidade getTipo() {
        return tipo;
    }

    public void setTipo(TipoModalidade tipo) {
        this.tipo = tipo;
    }

    public long getVitorias() {
        return vitorias;
    }

    public void setVitorias(long vitorias) {
        this.vitorias = vitorias;
    }

    public long getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(long derrotas) {
        this.derrotas = derrotas;
    }

    public long getEmpates() {
        return empates;
    }

    public void setEmpates(long empates) {
        this.empates = empates;
    }

    public long getTempoPorMovimento() {
        return tempoPorMovimento;
    }

    public void setTempoPorMovimento(long tempoPorMovimento) {
        this.tempoPorMovimento = tempoPorMovimento;
    }

    public long getDerrotasTempo() {
        return derrotasTempo;
    }

    public void setDerrotasTempo(long derrotasTempo) {
        this.derrotasTempo = derrotasTempo;
    }

    public void setMelhorPartida(EstatisticaModalidade melhorPartida) {
        this.melhorPartida = melhorPartida;
    }

    public void setUltimaPartida(EstatisticaModalidade ultimaPartida) {
        this.ultimaPartida = ultimaPartida;
    }

    public EstatisticaJogador getEstatisticaJogador() {
        return estatisticaJogador;
    }

    public void setEstatisticaJogador(EstatisticaJogador estatisticaJogador) {
        this.estatisticaJogador = estatisticaJogador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Modalidade that = (Modalidade) o;
        return getVitorias() == that.getVitorias() && getDerrotas() == that.getDerrotas() && getEmpates() == that.getEmpates() && getTempoPorMovimento() == that.getTempoPorMovimento() && getDerrotasTempo() == that.getDerrotasTempo() && getTipo() == that.getTipo() && Objects.equals(getEstatisticaJogador(), that.getEstatisticaJogador()) && Objects.equals(getMelhorPartida(), that.getMelhorPartida()) && Objects.equals(getUltimaPartida(), that.getUltimaPartida());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTipo());
    }
}
