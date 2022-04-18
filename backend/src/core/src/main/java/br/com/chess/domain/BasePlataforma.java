package br.com.chess.domain;

import br.com.chess.domain.enums.TipoMaestria;
import br.com.chess.domain.enums.TipoPlataforma;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class BasePlataforma extends BaseDomain {

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoPlataforma tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "titulo", nullable = true)
    private TipoMaestria titulo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_ultima_atualizacao", nullable = false)
    private Date dataUltimaAtualizacao;

    protected BasePlataforma(TipoPlataforma tipo, TipoMaestria titulo) {
        this.tipo = tipo;
        this.titulo = titulo;
    }

    protected BasePlataforma() {
        // Construtor padrao
    }

    public TipoPlataforma getTipo() {
        return tipo;
    }

    public void setTipo(TipoPlataforma tipo) {
        this.tipo = tipo;
    }

    public TipoMaestria getTitulo() {
        return titulo;
    }

    public void setTitulo(TipoMaestria titulo) {
        this.titulo = titulo;
    }

    public Date getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
        if (this.dataUltimaAtualizacao == null) {
            this.dataUltimaAtualizacao = new Date();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BasePlataforma that = (BasePlataforma) o;
        return getTipo() == that.getTipo() && getTitulo() == that.getTitulo() && Objects.equals(getDataUltimaAtualizacao(), that.getDataUltimaAtualizacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTipo(), getTitulo(), getDataUltimaAtualizacao());
    }
}
