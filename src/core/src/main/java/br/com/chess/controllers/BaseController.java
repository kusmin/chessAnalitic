package br.com.chess.controllers;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.chess.domain.Usuario;
import br.com.chess.security.JWTAuthentication;

public class BaseController {
	
	public Usuario getUsuario() {
		JWTAuthentication auth = (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
		return auth != null ? auth.getUsuario() : null;
	}
}
