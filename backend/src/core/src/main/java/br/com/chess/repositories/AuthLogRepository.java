package br.com.chess.repositories;

import br.com.chess.domain.AuthLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthLogRepository extends JpaRepository<AuthLog, Long> {

}
