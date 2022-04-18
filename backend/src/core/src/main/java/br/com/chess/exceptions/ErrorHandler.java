package br.com.chess.exceptions;

import br.com.chess.dto.Erro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({ServiceError.class})
    public ResponseEntity<Erro> handleException(ServiceError error, WebRequest request) {
        Erro erro = new Erro(error);
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IntegrationError.class})
    public ResponseEntity<Erro> handleException(IntegrationError error, WebRequest request) {
        Erro erro = new Erro();
        erro.setCode(String.format("Erro de integração com a plataforma %s", error.getCode()));
        erro.setMessage(error.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundError.class})
    public ResponseEntity<Erro> handleException(NotFoundError error, WebRequest request) {
        Erro erro = new Erro();
        erro.setCode("NOT_FOUND");
        erro.setMessage(error.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AccessError.class})
    public ResponseEntity<Erro> handleException(AccessError error, WebRequest request) {
        Erro erro = new Erro();
        erro.setCode("ACCESS_ERROR");
        erro.setMessage(error.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.FORBIDDEN);
    }

}
