package br.com.chess.repositories;

import br.com.chess.domain.Jogador;
import br.com.chess.domain.TipoPlataforma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {


    Jogador findByUsernameAndTipo(String user, TipoPlataforma tipoPlataforma);
}
