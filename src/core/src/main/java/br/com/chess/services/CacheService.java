package br.com.chess.services;

public interface CacheService {
	
	String get(String key);
	
	void set(String key, String value);
	
	void set(String key, String value, long ttl);
	
	void remove(String key);
	
	void clean();
	
	String getType();

}
