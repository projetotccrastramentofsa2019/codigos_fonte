package br.com.fsa.tccrastreamento.Autenticacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fsa.tccrastreamento.Autenticacao.modelo.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
	
	void deleteById(Long id);
	List<Token> findByUsuarioId(Long id);

}
