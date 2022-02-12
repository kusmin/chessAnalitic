package br.com.chess.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Credenciais {
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;

	
	public Credenciais() {
		// construtor padrão
	}
	
	public Credenciais(String username, String password) {
		this.username = username;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
