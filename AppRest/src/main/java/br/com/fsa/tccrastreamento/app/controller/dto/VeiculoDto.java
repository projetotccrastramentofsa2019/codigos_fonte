package br.com.fsa.tccrastreamento.app.controller.dto;

import java.time.LocalDateTime;

import br.com.fsa.tccrastreamento.app.model.Veiculo;

public class VeiculoDto {
	
	private Long id;
	private String placa;
	private String descricao;
	private LocalDateTime dataCadastro;
	
	public VeiculoDto(Veiculo veiculo) 
	{
		this.id = veiculo.getId(); 
		this.placa = veiculo.getPlaca();
		this.descricao = veiculo.getDescricao();
		this.dataCadastro = veiculo.getDataCadastro();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	

}
