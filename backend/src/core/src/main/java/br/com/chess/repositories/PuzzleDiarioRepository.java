package br.com.chess.repositories;

import br.com.chess.domain.PuzzleDiario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuzzleDiarioRepository extends JpaRepository<PuzzleDiario, Long> {
}
