package br.com.chess.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.chess.domain.AuthLog;

public interface AuthLogRepository extends JpaRepository<AuthLog, Long> {

}
