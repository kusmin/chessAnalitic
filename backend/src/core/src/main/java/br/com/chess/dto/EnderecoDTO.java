package br.com.chess.dto;

import br.com.chess.domain.Usuario;

import javax.validation.constraints.NotEmpty;

public class EnderecoDTO {
    @NotEmpty(message = "É obrigatório informar o logradouro")
    private String logradouro;

    @NotEmpty(message = "É obrigatório informar o número")
    private String numero;

    @NotEmpty(message = "É obrigatório informar o bairro")
    private String bairro;

    private String complemento;

    @NotEmpty(message = "É obrigatório informar o cep")
    private String cep;

    @NotEmpty(message = "É obrigatório informar a cidade")
    private String cidade;

    private String estado;

    private String referencia;

    private String nomeUf;

    private String siglaUf;

    private String nomeCidade;

    public EnderecoDTO() {
        // Construtor padrao
    }

    public EnderecoDTO(Usuario assinante) {
        this.bairro = assinante.getBairro();
        this.cep = assinante.getCep();
        this.complemento = assinante.getComplemento();
        this.estado = assinante.getCidade().getUf().getSigla();
        this.logradouro = assinante.getLogradouro();
        this.numero = assinante.getNumero();
        this.referencia = assinante.getReferencia() != null ? assinante.getReferencia() : null;
        this.cidade = assinante.getCidade().getNome();
        this.nomeUf = assinante.getCidade().getUf() != null ? assinante.getCidade().getUf().getNomeExtenso() : null;
        this.nomeCidade = assinante.getCidade().getUuid();
        this.siglaUf = assinante.getCidade().getUf().getNomeExtenso();
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNomeUf() {
        return nomeUf;
    }

    public void setNomeUf(String nomeUf) {
        this.nomeUf = nomeUf;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String siglaUf) {
        this.siglaUf = siglaUf;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }
}
