package br.com.chess.repositories;

import br.com.chess.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Perfil findByNome(String nome);

    Perfil findByUuid(String uuid);

}
