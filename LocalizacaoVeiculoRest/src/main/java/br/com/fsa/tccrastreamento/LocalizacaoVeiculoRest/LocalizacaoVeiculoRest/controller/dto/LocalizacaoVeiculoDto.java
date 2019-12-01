package br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.model.LocalizacaoVeiculo;

public class LocalizacaoVeiculoDto {
	
	private BigDecimal lat;
	private BigDecimal lng;
	private LocalDateTime dataRegistro;
	
	public LocalizacaoVeiculoDto(LocalizacaoVeiculo localizacao) 
	{
		this.lat = localizacao.getLat();
		this.lng = localizacao.getLng();
		this.dataRegistro = localizacao.getDataRegistro();
	}
	
	public BigDecimal getLat() {
		return lat;
	}
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	public BigDecimal getLng() {
		return lng;
	}
	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
	

}
