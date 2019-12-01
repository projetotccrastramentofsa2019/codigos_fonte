package br.com.fsa.tccrastreamento.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fsa.tccrastreamento.app.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long>{
	
	Optional<Token> findByHash(String hash);

}
