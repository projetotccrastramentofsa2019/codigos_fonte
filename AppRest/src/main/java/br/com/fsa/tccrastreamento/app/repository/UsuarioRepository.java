package br.com.fsa.tccrastreamento.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fsa.tccrastreamento.app.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);
	Optional<Usuario> findByIdAndSenha(Long id, String senha);

}
