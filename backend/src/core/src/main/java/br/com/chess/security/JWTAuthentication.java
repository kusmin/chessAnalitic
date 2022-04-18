package br.com.chess.security;

import br.com.chess.domain.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthentication implements Authentication {

    private static final long serialVersionUID = 7193684893408711752L;

    private final Usuario usuario;

    private boolean autenticado;

    public JWTAuthentication(Usuario usuario) {
        this.usuario = usuario;
        this.autenticado = usuario.isAtivo();
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    @Override
    public String getName() {
        return usuario != null ? usuario.getNome() : null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getPerfil().getPermissoes();
    }

    @Override
    public Object getCredentials() {
        return usuario != null ? usuario.getEmail() : null;
    }

    @Override
    public Object getDetails() {
        return usuario;
    }

    @Override
    public Object getPrincipal() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAuthenticated() {
        return this.autenticado;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.autenticado = isAuthenticated;

    }
}
