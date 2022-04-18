package br.com.chess.repositories;

import br.com.chess.domain.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

}
