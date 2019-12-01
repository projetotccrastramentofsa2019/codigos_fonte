package br.com.fsa.tccrastreamento.app.controller.dto;

import br.com.fsa.tccrastreamento.app.model.Usuario;

public class VerificacaoUsuarioDto {
	
	private boolean verificacaoOk;
	private UsuarioDto usuario;

	public VerificacaoUsuarioDto(boolean verificacaoOk, Usuario usuario) {
		this.verificacaoOk = verificacaoOk;
		this.usuario = new UsuarioDto(usuario);
	}

	public VerificacaoUsuarioDto() {
		
	}

	public boolean isVerificacaoOk() {
		return verificacaoOk;
	}
	public void setVerificacaoOk(boolean verificacaoOk) {
		this.verificacaoOk = verificacaoOk;
	}
	public UsuarioDto getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}
}