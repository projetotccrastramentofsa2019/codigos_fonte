package br.com.fsa.tccrastreamento.app.controller;

import java.util.Optional;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fsa.tccrastreamento.app.controller.dto.UsuarioDto;
import br.com.fsa.tccrastreamento.app.controller.dto.VerificacaoUsuarioDto;
import br.com.fsa.tccrastreamento.app.controller.form.AlteracaoUsuarioForm;
import br.com.fsa.tccrastreamento.app.controller.form.VerificacaoUsuarioForm;
import br.com.fsa.tccrastreamento.app.exception.UsuarioNaoEncontradoException;
import br.com.fsa.tccrastreamento.app.model.Token;
import br.com.fsa.tccrastreamento.app.model.Usuario;
import br.com.fsa.tccrastreamento.app.repository.TokenRepository;
import br.com.fsa.tccrastreamento.app.repository.UsuarioRepository;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usrRep;
	
	@Autowired
	private TokenRepository tokenRep;
	
	@RequestMapping(value="/alterar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public Callable<ResponseEntity<UsuarioDto>> alterarDadosDoUsuario(@RequestBody AlteracaoUsuarioForm form)
	{
		return () -> {
			
			try 
			{
				Usuario usuario = form.AlterarDadosUsuario(usrRep);
				
				return ResponseEntity.ok().body(new UsuarioDto(usuario));
			}
			catch(UsuarioNaoEncontradoException ex)
			{
				return ResponseEntity.status(401).build();
			}
			catch(Exception ex)
			{
				return ResponseEntity.status(500).build();
			}
			
		};
	}
	
	@RequestMapping(value="/verificarUsuario", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Callable<ResponseEntity<VerificacaoUsuarioDto>> verificarUsuario(@RequestBody VerificacaoUsuarioForm form, HttpServletRequest req)
	{
		return () -> {
			
			String hashToken = req.getHeader("token-aut");
			
			System.out.println("------------------------------------------------");
			System.out.println("--- FORM: ");
			System.out.println("id usuario: " + form.getIdUsuario());
			System.out.println("senha: " + form.getSenha());
			System.out.println("------------------------------------------------");
			if(tokenPertenceAoUsuario(form, hashToken))
			{
				Optional<Usuario> optUsr = usrRep.findByIdAndSenha(form.getIdUsuario(), form.getSenha());
				
				if(optUsr.isPresent())
				{
					return ResponseEntity.ok().body(new VerificacaoUsuarioDto(true, optUsr.get()));
				}
				else 
				{
					return ResponseEntity.ok().body(new VerificacaoUsuarioDto());
				}
			}
			else 
			{
				return ResponseEntity.status(401).build();
			}
			
			
		};
	}
	
	private boolean tokenPertenceAoUsuario(VerificacaoUsuarioForm form, String hash) {
		System.out.println("HASH RECEBIDO: " + hash);
		Optional<Token> optTk = tokenRep.findByHash(hash);
		
		if(optTk.isPresent())
		{
			Token tk = optTk.get();
			
			System.out.println("ID USUARIO ENCONTRADO POR HASH: " + tk.getUsuario().getId());
			
			if(tk.getUsuario().getId().equals(form.getIdUsuario())) 
			{
				System.out.println("HASH BATEU");
				return true;
			}
		}
		
		System.out.println("NÃO ENCONTROU HASH");
		return false;
	}

}