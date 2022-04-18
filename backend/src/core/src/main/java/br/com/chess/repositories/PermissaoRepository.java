package br.com.chess.repositories;

import br.com.chess.domain.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    Permissao findByAuthority(String authority);

}
