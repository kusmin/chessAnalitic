package br.com.chess.repositories;

import br.com.chess.domain.estaticas.EstatisticaJogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstatisticaJogadorRepository extends JpaRepository<EstatisticaJogador, Long> {
}
