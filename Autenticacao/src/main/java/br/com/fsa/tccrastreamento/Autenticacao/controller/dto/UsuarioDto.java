package br.com.fsa.tccrastreamento.Autenticacao.controller.dto;

import br.com.fsa.tccrastreamento.Autenticacao.modelo.Usuario;

public class UsuarioDto {
	
	private Long id;
	private String nome;
	private String email;
	private TokenDto token;
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}
	
	public String getNome() { 
		return this.nome;
	}
	
	public String getEmail() {
		return this.email;
	}

	public TokenDto getToken() {
		return token;
	}

	public void setToken(TokenDto token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
