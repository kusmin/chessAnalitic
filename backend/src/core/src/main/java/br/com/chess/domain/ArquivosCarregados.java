package br.com.chess.domain;

import br.com.chess.domain.enums.StatusArquivo;
import br.com.chess.domain.enums.TipoArquivo;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Audited
@Table(name = "arquivo", indexes = {@Index(columnList = "arquivo_id")})
public class ArquivosCarregados extends BaseDomain {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arquivo_id", nullable = false)
    private Arquivo arquivo;

    @Column(name = "data_envio", nullable = false)
    private Date dataEnvio;

    @Column(name = "nome_arquivo", nullable = false, length = 128)
    private String nomeArquivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusArquivo status;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoArquivo tipo;

    public ArquivosCarregados() {
        // Construtor padrao
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public StatusArquivo getStatus() {
        return status;
    }

    public void setStatus(StatusArquivo status) {
        this.status = status;
    }

    public TipoArquivo getTipo() {
        return tipo;
    }

    public void setTipo(TipoArquivo tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ArquivosCarregados that = (ArquivosCarregados) o;
        return Objects.equals(getArquivo(), that.getArquivo()) && Objects.equals(getDataEnvio(), that.getDataEnvio()) && Objects.equals(getNomeArquivo(), that.getNomeArquivo()) && getStatus() == that.getStatus() && getTipo() == that.getTipo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getArquivo(), getDataEnvio(), getNomeArquivo(), getStatus(), getTipo());
    }
}
