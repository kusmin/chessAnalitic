package br.com.chess.domain;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serial;
import java.util.Objects;

@Audited
@Entity
@Table(name = "municipio", indexes = {@Index(columnList = "uf_id")})
public class Municipio extends BaseDomain {
    @Serial
    private static final long serialVersionUID = 8750684685173956582L;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "uf_id", nullable = false)
    private Uf uf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Municipio municipio = (Municipio) o;
        return Objects.equals(getNome(), municipio.getNome()) && Objects.equals(getCodigo(), municipio.getCodigo()) && Objects.equals(getUf(), municipio.getUf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNome(), getCodigo(), getUf());
    }
}
