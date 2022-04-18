package br.com.chess.domain;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serial;
import java.util.Objects;

@Audited
@Entity
@Table(name = "assinatura", indexes = {@Index(columnList = "usuario_assinante_id")})
public class Assinatura extends BaseDomain {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 2857230876812508927L;

    @ManyToOne
    @JoinColumn(name = "usuario_assinante_id", nullable = false)
    private Usuario assinante;

    @Column(name = "ativa")
    private Boolean isAtiva;

    public Assinatura() {
        // Construtor padrao
    }

    public Usuario getAssinante() {
        return assinante;
    }

    public void setAssinante(Usuario assinante) {
        this.assinante = assinante;
    }

    public Boolean getAtiva() {
        return isAtiva;
    }

    public void setAtiva(Boolean ativa) {
        isAtiva = ativa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Assinatura that = (Assinatura) o;
        return Objects.equals(getAssinante(), that.getAssinante()) && Objects.equals(isAtiva, that.isAtiva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAssinante(), isAtiva);
    }
}
