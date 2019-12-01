package br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.model.LocalizacaoVeiculo;

public interface LocalizacaoVeiculoRepository extends JpaRepository<LocalizacaoVeiculo, Long> {
	
	Page<LocalizacaoVeiculo> findByVeiculoUsuarioId(Long id, Pageable pageable);
	Page<LocalizacaoVeiculo> findByVeiculoIdAndVeiculoUsuarioId(Long veiculoId, Long veiculoUsuarioId, Pageable pageable);

}
