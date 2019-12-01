package br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	Optional<Veiculo> findByIdAndUsuarioId(Long id, Long usuarioId);
	
}
