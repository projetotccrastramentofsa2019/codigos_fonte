package br.com.fsa.tccrastreamento.Autenticacao.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fsa.tccrastreamento.Autenticacao.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Page<Usuario> findByEmailAndSenha(String email, String senha, Pageable paginacao);
	
}
