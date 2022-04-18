package br.com.chess.repositories;

import br.com.chess.domain.Cep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CepRepository extends JpaRepository<Cep, Long> {
    Cep findByCodigo(String cep);
}
