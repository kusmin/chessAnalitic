package br.com.chess;

public enum Personas {

    Administrador("Administrador"), // administrador da plataforma
    Assinante("Assinante");   // quem cria a assinatura (dono da assinatura)

    private final String nome;

    Personas(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

}
