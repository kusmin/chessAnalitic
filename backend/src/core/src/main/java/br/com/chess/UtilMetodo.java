package br.com.chess;

import br.com.chess.domain.StatusContaPlataforma;
import br.com.chess.domain.TipoMaestria;
import br.com.chess.domain.TipoPlataforma;
import br.com.chess.exceptions.ServiceError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import static br.com.chess.UtilConstantes.*;

public class UtilMetodo {

    private static final Logger logger = LoggerFactory.getLogger("UtilMetodo");

    private UtilMetodo() {
        throw new ServiceError("Classe utilitaria");
    }

    public static void cleanUp(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            logger.error(String.format("Não foi possível apagar o arquivo %s", path));
        }
    }
    public static Random rand(){
        try {
            return SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            logger.error(String.format("Erro ao gerar numero %s", e));
        }
        return null;
    }

    public static StatusContaPlataforma retornarStatusContaPlataforma(String status) {
        try {
            return StatusContaPlataforma.findByCodigo(status);
        }catch (Exception e) {
            throw new ServiceError(TIPO_STATUS_PLATAFORMA_DESCONHECIDO);
        }
    }

    public static TipoMaestria retornarTipoMaestria(String tipo) {
        try {
            return TipoMaestria.valueOf(tipo);
        }catch (Exception e) {
            throw new ServiceError(TIPO_STATUS_MAESTRIA_DESCONHECIDO);
        }
    }

    public static TipoPlataforma confirmarTipoPlataforma(String type) {
        try{
            return TipoPlataforma.valueOf(type);
        }catch (Exception e) {
            throw new ServiceError(UtilConstantes.TIPO_PLATAFORMA_DESCONHECIDO);
        }
    }
}
