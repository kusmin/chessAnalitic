package br.com.chess.exceptions;

public class IntegrationError extends Exception {
	
	private final String code;
	
	private final String message;
	
	public IntegrationError(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}

}
