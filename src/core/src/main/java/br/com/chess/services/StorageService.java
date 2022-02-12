package br.com.chess.services;

import br.com.chess.domain.Arquivo;
import br.com.chess.domain.Usuario;

import java.io.InputStream;

public interface StorageService {

	Arquivo store(String nomeOriginal, String bucket, Usuario usuario, InputStream conteudo, boolean publico);

	Arquivo replace(String nomeOriginal, String bucket, Usuario usuario, InputStream conteudo, Arquivo arquivo);

	InputStream read(Arquivo arquivo, Usuario usuario);

	void remove(Arquivo arquivo);

	String getType();

}
