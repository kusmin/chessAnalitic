package br.com.chess.domain;

public enum StatusContaPlataforma {

    BASICO("basic"),
    FECHADA("closed"),
    PREMIUM("premium"),
    FUNCIONARIO("staff");

    private String value;

    private StatusContaPlataforma(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return this.value;
    }

    public String getKey() {
        return this.name();
    }
}
