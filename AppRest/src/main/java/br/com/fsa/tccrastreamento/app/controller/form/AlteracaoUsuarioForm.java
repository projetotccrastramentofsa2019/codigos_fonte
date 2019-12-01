package br.com.fsa.tccrastreamento.app.controller.form;

import java.util.Optional;

import br.com.fsa.tccrastreamento.app.exception.UsuarioNaoEncontradoException;
import br.com.fsa.tccrastreamento.app.model.Usuario;
import br.com.fsa.tccrastreamento.app.repository.UsuarioRepository;


public class AlteracaoUsuarioForm {
	
	private Long id;
	private String email;
	private String nome;
	private String senhaAntiga;
	private String senhaNova;
	
	public Usuario AlterarDadosUsuario(UsuarioRepository usrRep) throws UsuarioNaoEncontradoException, Exception 
	{
		Optional<Usuario> optUsuario = usrRep.findByIdAndSenha(this.getId(), this.getSenhaAntiga());
		
		if(optUsuario.isPresent())
		{
			Usuario usr = usrRep.getOne(getId());
			
			usr.setEmail(getEmail());
			usr.setNome(getNome());
			usr.setSenha(getSenhaNova());
			
			usrRep.save(usr);
			
			return usr;
		}
		else 
		{
			throw new UsuarioNaoEncontradoException();
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenhaAntiga() {
		return senhaAntiga;
	}
	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}
	public String getSenhaNova() {
		return senhaNova;
	}
	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	
}