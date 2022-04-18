package br.com.chess.dto;

import br.com.chess.domain.Municipio;

public class MunicipioDTO {
    private String uuid;
    private String nome;
    private String codigo;
    private String uf;
    private String ufExtenso;

    public MunicipioDTO() {
    }

    public MunicipioDTO(Municipio municipio) {
        if (municipio != null) {
            this.uuid = municipio.getUuid();
            this.nome = municipio.getNome();
            this.codigo = municipio.getCodigo();
            this.uf = municipio.getUf().getSigla();
            this.ufExtenso = municipio.getUf().getNomeExtenso();
        }
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getUfExtenso() {
        return ufExtenso;
    }

    public void setUfExtenso(String ufExtenso) {
        this.ufExtenso = ufExtenso;
    }
}
