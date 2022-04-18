package br.com.chess.domain;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Audited
@Table(name = "arquivo")
public class Arquivo extends BaseDomain {

    @Column(name = "bucket", nullable = false, length = 64)
    private String bucket;

    @Column(name = "original_name", nullable = false, length = 128)
    private String originalName;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "excluido", nullable = false)
    private boolean excluido = false;

    @Column(name = "url", nullable = true, length = 255)
    private String url;

    @Column(name = "content_type", nullable = true)
    private String contentType;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Arquivo)) return false;
        if (!super.equals(o)) return false;
        Arquivo arquivo = (Arquivo) o;
        return isExcluido() == arquivo.isExcluido() && Objects.equals(getBucket(), arquivo.getBucket()) && Objects.equals(getOriginalName(), arquivo.getOriginalName()) && Objects.equals(getUsuario(), arquivo.getUsuario()) && Objects.equals(getUrl(), arquivo.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBucket(), getOriginalName(), getUsuario(), isExcluido(), getUrl());
    }
}
