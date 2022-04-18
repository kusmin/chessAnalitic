package br.com.chess.domain.enums;

public enum SituacaoEmail {
    PENDENTE("PENDENTE"),
    ENVIADO("ENVIADO"), VALIDADO("VALIDADO");

    private final String value;

    SituacaoEmail(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String getKey() {
        return this.name();
    }

}
