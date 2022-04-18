package br.com.chess.repositories;

import br.com.chess.domain.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    Municipio findByNome(String nome);
}
