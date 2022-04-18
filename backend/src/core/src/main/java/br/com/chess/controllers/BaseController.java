package br.com.chess.controllers;

import br.com.chess.domain.Usuario;
import br.com.chess.security.JWTAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

    public Usuario getUsuario() {
        JWTAuthentication auth = (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? auth.getUsuario() : null;
    }
}
