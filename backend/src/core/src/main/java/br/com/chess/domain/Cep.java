package br.com.chess.domain;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
@Entity
@Table(name = "cep", indexes = {@Index(columnList = "codigo"), @Index(columnList = "municipio_id")})
public class Cep extends BaseDomain {
    private static final long serialVersionUID = 5531431115488982162L;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "bairro", nullable = true)
    private String bairro;

    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = true)
    private Municipio municipio;

    @Column(name = "nome_cidade", nullable = true)
    private String nomeCidade;

    @Column(name = "logrouro", nullable = true)
    private String logradouro;

    @ManyToOne
    @JoinColumn(name = "uf_id", nullable = true)
    private Uf uf;

    @Column(name = "sigla_uf", nullable = true)
    private String siglaUf;

    @Column(name = "complemento", nullable = true)
    private String complemento;

    @Column(name = "origem", nullable = false)
    private String origem;

    @Column(name = "ibge", nullable = true)
    private String ibge;

    @Column(name = "gia", nullable = true)
    private String gia;

    @Column(name = "ddd", nullable = true)
    private String ddd;

    @Column(name = "siafi", nullable = true)
    private String siafi;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String siglaUf) {
        this.siglaUf = siglaUf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getSiafi() {
        return siafi;
    }

    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }
}
