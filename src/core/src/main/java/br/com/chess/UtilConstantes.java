package br.com.chess;

import br.com.chess.exceptions.ServiceError;

public class UtilConstantes {

    public static final String CHESS_COM = "Chess.com";
    public static final String ADMINISTRADOR = "Administrador";
    public static final String JOGADOR_DESCONHECIDO = "Jogador não encontrado.";
    public static final String DATA_INVALIDA = "Data invalida.";
    public static final String TIPO_STATUS_PLATAFORMA_DESCONHECIDO = "Status plataforma desconhecido.";
    public static final String TIPO_PLATAFORMA_DESCONHECIDO = "Tipo plataforma desconhecido.";
    public static final String ERRO_FECHAR_CAMEL = "Erro ao fechar o camel.";

    private UtilConstantes() {
        throw new ServiceError("Classe utilitaria");
    }


}
