package br.com.chess.dto;

import br.com.chess.domain.Cep;

public class EnderecoCepDTO {
    private String cep;
    private String bairro;
    private String cidade;
    private String localidade;
    private String logradouro;
    private String nomeUf;
    private String siglaUf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
    private String complemento;

    public EnderecoCepDTO() {
        // Construtor padrao
    }

    public EnderecoCepDTO(Cep cep) {
        this.cep = cep.getCodigo();
        this.logradouro = cep.getLogradouro();
        this.complemento = cep.getComplemento();
        this.bairro = cep.getBairro();
        this.localidade = cep.getNomeCidade();
        this.nomeUf = cep.getUf() != null ? cep.getUf().getNomeExtenso() : null;
        this.siglaUf = cep.getSiglaUf();
        this.cidade = cep.getMunicipio() != null ? cep.getMunicipio().getUuid() : null;
        this.ibge = cep.getIbge();
        this.gia = cep.getGia();
        this.ddd = cep.getDdd();
        this.siafi = cep.getSiafi();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
