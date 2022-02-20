package br.com.chess.services.storage;

import br.com.chess.exceptions.ServiceError;

public class StorageError extends ServiceError {
	
	public StorageError(String message) {
		super(message);
	}
	
	public StorageError(String message, Throwable t) {
		super(message, t);
	}

}
