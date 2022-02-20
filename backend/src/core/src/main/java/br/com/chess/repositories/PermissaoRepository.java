package br.com.chess.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.chess.domain.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	
	Permissao findByAuthority(String authority);

}
