package br.com.chess.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serial;
import java.util.Objects;

@Entity
@Table(name = "arquivo_download")
public class ArquivoDownload extends BaseDomain {

    @Serial
    private static final long serialVersionUID = 848L;

    @ManyToOne
    @JoinColumn(name = "arquivo_id", nullable = false)
    private Arquivo arquivo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ArquivoDownload that = (ArquivoDownload) o;
        return Objects.equals(getArquivo(), that.getArquivo()) && Objects.equals(getUsuario(), that.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getArquivo(), getUsuario());
    }
}
