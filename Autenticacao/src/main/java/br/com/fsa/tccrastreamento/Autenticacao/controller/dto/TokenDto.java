package br.com.fsa.tccrastreamento.Autenticacao.controller.dto;

import java.time.LocalDateTime;

import br.com.fsa.tccrastreamento.Autenticacao.modelo.Token;

public class TokenDto {
	
	private String hash;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataExpiracao;
	
	public TokenDto (Token token)
	{
		this.hash = token.getHash();
		this.dataCriacao = token.getDataCriacao();
		this.dataExpiracao = token.getDataExpiracao();
	}

	public String getHash() {
		return hash;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}
	
	

}
