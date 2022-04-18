package br.com.chess.domain.enums;

public enum StatusContaPlataforma {

    BASICO("basic"),
    FECHADA("closed"),
    PREMIUM("premium"),
    FUNCIONARIO("staff");

    private final String value;

    StatusContaPlataforma(String value) {
        this.value = value;
    }

    public static StatusContaPlataforma findByCodigo(String group) {
        if (group == null) {
            return null;
        }
        StatusContaPlataforma result = null;
        for (StatusContaPlataforma valor : values()) {
            if (valor.value.equalsIgnoreCase(group.trim())) {
                result = valor;
                break;
            }
        }
        return result;
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
