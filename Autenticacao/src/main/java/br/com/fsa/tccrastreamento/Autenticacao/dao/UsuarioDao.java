package br.com.fsa.tccrastreamento.Autenticacao.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import br.com.fsa.tccrastreamento.Autenticacao.modelo.Usuario;
import br.com.fsa.tccrastreamento.Autenticacao.repository.UsuarioRepository;

public class UsuarioDao {
	
	private UsuarioRepository usrRep;
	
	public UsuarioDao(UsuarioRepository usrRep)
	{
		this.usrRep = usrRep;
		
	}
	
	public Usuario retornaUsuarioAutenticacao(String email, String senha) {
		
		Pageable paginacao = PageRequest.of(0, 1, Direction.ASC, "id");
		Page<Usuario> usuario = usrRep.findByEmailAndSenha(email, senha, paginacao);
		
		if(usuario.isEmpty())
		{
			return null;
		}
		else 
		{
			return usuario.getContent().get(usuario.getContent().size() - 1);
		}
		
	}

}
