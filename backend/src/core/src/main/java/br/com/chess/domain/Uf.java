package br.com.chess.domain;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Audited
@Entity
@Table(name = "uf")
public class Uf extends BaseDomain {

    private static final long serialVersionUID = 1670862054569770390L;

    @Column(name = "nome_extenso", nullable = false, unique = true)
    private String nomeExtenso;

    @Column(name = "sigla", nullable = false, unique = true)
    private String sigla;

    @Column(name = "codigo", nullable = false, unique = true)
    private Integer codigo;

    public String getNomeExtenso() {
        return nomeExtenso;
    }

    public void setNomeExtenso(String nomeExtenso) {
        this.nomeExtenso = nomeExtenso;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Uf uf = (Uf) o;
        return Objects.equals(getNomeExtenso(), uf.getNomeExtenso()) && Objects.equals(getSigla(), uf.getSigla()) && Objects.equals(getCodigo(), uf.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNomeExtenso(), getSigla(), getCodigo());
    }
}
