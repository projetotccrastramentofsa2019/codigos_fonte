package br.com.fsa.tccrastreamento.app.controller.dto;

import br.com.fsa.tccrastreamento.app.model.Usuario;

public class UsuarioDto {
	
	private Long id;
	private String nome;
	private String email;
	
	public UsuarioDto (Usuario usr)
	{
		setId(usr.getId());
		setNome(usr.getNome());
		setEmail(usr.getEmail());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}