package br.com.fsa.tccrastreamento.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fsa.tccrastreamento.app.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

	List<Veiculo> findByUsuarioId(Long id);
	
}
