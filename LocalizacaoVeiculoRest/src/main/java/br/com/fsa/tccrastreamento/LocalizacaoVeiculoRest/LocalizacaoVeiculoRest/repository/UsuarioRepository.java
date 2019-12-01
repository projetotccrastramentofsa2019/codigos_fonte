package br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	

}
