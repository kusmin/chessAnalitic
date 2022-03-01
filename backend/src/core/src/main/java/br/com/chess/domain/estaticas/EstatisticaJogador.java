package br.com.chess.domain.estaticas;

import br.com.chess.domain.BaseDomain;
import br.com.chess.domain.Jogador;
import br.com.chess.domain.enums.TipoEstudo;
import br.com.chess.domain.enums.TipoModalidade;
import br.com.chess.dto.estatisticas.EstatisticasDto;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Audited
@Table(name="estatistica_jogador")
public class EstatisticaJogador extends BaseDomain {
    private static final long serialVersionUID = 2661461038034844760L;

    @OneToMany(mappedBy = "estatisticaJogador",cascade = CascadeType.ALL, orphanRemoval=true, fetch= FetchType.EAGER)
    private Set<Modalidade> modalidades;

    @OneToMany(mappedBy = "estatisticaJogador",cascade = CascadeType.ALL, orphanRemoval=true, fetch= FetchType.EAGER)
    private Set<Estudo> estudos;

    @Column(name="fide", nullable = true)
    private long fide;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch= FetchType.EAGER, mappedBy = "estatisticaJogador")
    private QuebraCabecas quebraCabecas;

    @OneToOne
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

    public Jogador getJogador() {
        return jogador;
    }

    public EstatisticaJogador() {
        // Construtor padrao
    }

    public EstatisticaJogador(Jogador jogador, EstatisticasDto estatisticas) {
        super();
        this.jogador = jogador;
        this.fide = estatisticas.getFide();
        this.quebraCabecas = new QuebraCabecas(this,estatisticas.getPuzzle());
        Set<Modalidade> modalidadeSet = new HashSet<>();
        if(estatisticas.getChessDaily() != null){
            modalidadeSet.add(new Modalidade(this,TipoModalidade.DIARIO, estatisticas.getChessDaily()));
        }
        if(estatisticas.getChess960Daily() != null){
            modalidadeSet.add(new Modalidade(this, TipoModalidade.DIARIO_960, estatisticas.getChess960Daily()));
        }
        if(estatisticas.getChessRapid() != null){
            modalidadeSet.add(new Modalidade(this, TipoModalidade.RAPIDO, estatisticas.getChessRapid()));
        }
        if(estatisticas.getChessBlitz() != null){
            modalidadeSet.add(new Modalidade(this, TipoModalidade.BLITZ, estatisticas.getChessBlitz()));
        }
        if(estatisticas.getChessBullet() != null){
            modalidadeSet.add(new Modalidade(this, TipoModalidade.BULLET, estatisticas.getChessBullet()));
        }
        this.modalidades = modalidadeSet;

        Set<Estudo> estudoSet = new HashSet<>();
        if(estatisticas.getLessons() != null){
            estudoSet.add(new Estudo(this,TipoEstudo.LICOES, estatisticas.getLessons()));
        }
        if(estatisticas.getTactics() != null){
            estudoSet.add(new Estudo(this,TipoEstudo.TATICA, estatisticas.getTactics()));
        }
        this.estudos = estudoSet;
    }

    public Set<Modalidade> getModalidades() {
        return modalidades;
    }

    public void setModalidades(Set<Modalidade> modalidades) {
        this.modalidades = modalidades;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Set<Estudo> getEstudos() {
        return estudos;
    }

    public void setEstudos(Set<Estudo> estudos) {
        this.estudos = estudos;
    }

    public long getFide() {
        return fide;
    }

    public void setFide(long fide) {
        this.fide = fide;
    }

    public void setQuebraCabecas(QuebraCabecas quebraCabecas) {
        this.quebraCabecas = quebraCabecas;
    }

    public QuebraCabecas getQuebraCabecas() {
        return quebraCabecas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EstatisticaJogador that = (EstatisticaJogador) o;
        return Objects.equals(getJogador(), that.getJogador());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getJogador());
    }
}
