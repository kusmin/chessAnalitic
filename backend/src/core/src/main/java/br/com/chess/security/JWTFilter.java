package br.com.chess.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import br.com.chess.services.SecurityService;

@Component
public class JWTFilter extends GenericFilterBean {

	private static final String TOKEN = "Authorization";
	
	@Autowired
	private SecurityService securityService;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String token = httpRequest.getHeader(TOKEN);
		if (token != null) {
			JWTAuthentication auth = securityService.parseToken(token);
			if (auth != null && auth.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		
		chain.doFilter(request, response);
	}
	
}
