package br.com.fsa.tccrastreamento.Autenticacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fsa.tccrastreamento.Autenticacao.modelo.Token;

public interface TokenUsuarioRepository extends JpaRepository<Token, Long> {

}
