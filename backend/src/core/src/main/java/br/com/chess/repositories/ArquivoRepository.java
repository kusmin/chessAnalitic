package br.com.chess.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.chess.domain.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

}
