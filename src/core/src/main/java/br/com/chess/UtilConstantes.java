package br.com.chess;

import br.com.chess.exceptions.ServiceError;

public class UtilConstantes {

    private UtilConstantes() {
        throw new ServiceError("Classe utilitaria");
    }

    public static final String ADMINISTRADOR = "Administrador";
    public static final String JOGADOR_DESCONHECIDO = "Jogador não encontrado.";
    public static final String TIPO_PLATAFORMA_DESCONHECIDO = "Tipo plataforma desconhecido.";
}
