package br.com.chess.repositories;

import br.com.chess.domain.ListaJogadoresTitulados;
import br.com.chess.domain.enums.TipoMaestria;
import br.com.chess.domain.enums.TipoPlataforma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LIstaJogadoresTituladosRepository extends JpaRepository<ListaJogadoresTitulados, Long> {
    ListaJogadoresTitulados findByTituloAndTipo(TipoMaestria titulo, TipoPlataforma plataforma);
}
