package br.com.chess.dto;

import br.com.chess.domain.Uf;

public class UfDTO {
    private String nome;

    private String nomeExtenso;

    private Integer codigo;

    public UfDTO() {
        // Construtor padrao
    }

    public UfDTO(Uf uf) {
        if (uf != null) {
            this.nome = uf.getSigla();
            this.nomeExtenso = uf.getNomeExtenso();
            this.codigo = uf.getCodigo();
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeExtenso() {
        return nomeExtenso;
    }

    public void setNomeExtenso(String nomeExtenso) {
        this.nomeExtenso = nomeExtenso;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
