package br.com.chess.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.chess.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByEmail(String email);

	Usuario findByUuid(String uuid);

}
