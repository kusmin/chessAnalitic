package br.com.chess.repositories;

import br.com.chess.domain.Uf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UfRepository extends JpaRepository<Uf, Long> {
    List<Uf> findAllByOrderBySiglaAsc();

    Uf findBySigla(String uf);
}
