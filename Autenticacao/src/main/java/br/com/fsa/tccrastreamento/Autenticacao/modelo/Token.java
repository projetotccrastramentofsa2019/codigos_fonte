package br.com.fsa.tccrastreamento.Autenticacao.modelo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.fsa.tccrastreamento.Autenticacao.controller.dto.TokenDto;
import br.com.fsa.tccrastreamento.Autenticacao.repository.TokenRepository;

@Entity
public class Token {

	@Deprecated
	public Token() {}
	
	public Token(Usuario usuario) 
	{
		this.hash = gerarNovoHash();
		this.usuario = usuario;
		this.dataCriacao = LocalDateTime.now();
		this.dataExpiracao = this.dataCriacao.plusHours(1);
	}
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String hash;
	
	private LocalDateTime dataCriacao;
	
	
	private LocalDateTime dataExpiracao;
	
	@OneToOne
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(LocalDateTime dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	private static String gerarNovoHash() 
	{
		StringBuilder str = new StringBuilder();
		
		for(int i = 0; i < 100; i++)
		{
			int codCh = 65 + (int)(Math.random() * 26);
			
			str.append((char) codCh);
		}
		
		return str.toString();
	}
	
	public static TokenDto gerarNovoToken(Usuario usuario, TokenRepository tokenRep) {
		
		List<Token> listaTokenUsuario = tokenRep.findByUsuarioId(usuario.getId());
		
		for(Token t : listaTokenUsuario) 
		{
			tokenRep.deleteById(t.getId());	
		}
		
		Token token = new Token(usuario);
		tokenRep.save(token);
		
		return new TokenDto(token);
	}
	
}
