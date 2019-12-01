package br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.model.LocalizacaoVeiculo;

public class PaginaLocalizacaoVeiculoDto {
	
	private Long numeroTotalDeElementos;
	private Long numeroDePaginas;
	private Long indicePagina;
	
	private List<LocalizacaoVeiculoDto> itens;

	public PaginaLocalizacaoVeiculoDto(Page<LocalizacaoVeiculo> page) {
		
		this.numeroTotalDeElementos = page.getTotalElements();
		this.numeroDePaginas = (long) page.getTotalPages();
		this.indicePagina = (long) page.getNumber();
		
		itens = new ArrayList<>();
		
		List<LocalizacaoVeiculo> lista = page.getContent();
		
		for(LocalizacaoVeiculo l : lista)
		{
			itens.add(new LocalizacaoVeiculoDto(l));
		}
	}

	public Long getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(Long numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

	public Long getIndicePagina() {
		return indicePagina;
	}

	public void setIndicePagina(Long indicePagina) {
		this.indicePagina = indicePagina;
	}

	public List<LocalizacaoVeiculoDto> getItens() {
		return itens;
	}

	public void setItens(List<LocalizacaoVeiculoDto> itens) {
		this.itens = itens;
	}

	public Long getNumeroTotalDeElementos() {
		return numeroTotalDeElementos;
	}

	public void setNumeroTotalDeElementos(Long numeroTotalDeElementos) {
		this.numeroTotalDeElementos = numeroTotalDeElementos;
	}
	
	

}
