package br.com.chess.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.chess.domain.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	
	Perfil findByNome(String nome);
	
	Perfil findByUuid(String uuid);

}
