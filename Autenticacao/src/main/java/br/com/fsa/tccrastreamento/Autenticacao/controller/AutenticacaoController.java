package br.com.fsa.tccrastreamento.Autenticacao.controller;

import java.util.Optional;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fsa.tccrastreamento.Autenticacao.controller.dto.UsuarioDto;
import br.com.fsa.tccrastreamento.Autenticacao.controller.form.UsuarioAutenticacaoForm;
import br.com.fsa.tccrastreamento.Autenticacao.dao.UsuarioDao;
import br.com.fsa.tccrastreamento.Autenticacao.modelo.Token;
import br.com.fsa.tccrastreamento.Autenticacao.modelo.Usuario;
import br.com.fsa.tccrastreamento.Autenticacao.repository.TokenRepository;
import br.com.fsa.tccrastreamento.Autenticacao.repository.UsuarioRepository;

@RestController
@RequestMapping("/aut")
public class AutenticacaoController {

	@Autowired
	private UsuarioRepository usrRep;
	
	@Autowired
	private TokenRepository tokenRep;

	@GetMapping("/teste")
	public Callable<ResponseEntity<String>> teste() {
		return () -> {
			return ResponseEntity.ok().body("OK!");
		};
	}
	
	@PostMapping
	@Transactional
	public Callable<ResponseEntity<UsuarioDto>> autentica(@RequestBody UsuarioAutenticacaoForm usrForm)
	{
		return () -> {
			
			try 
			{
				UsuarioDao usrDao = new UsuarioDao(usrRep);
	
				Usuario usr = usrDao.retornaUsuarioAutenticacao(usrForm.getEmail(), usrForm.getSenha());
				
				if (usr != null) 
				{
					UsuarioDto dto = new UsuarioDto(usr);
					dto.setToken(Token.gerarNovoToken(usr, tokenRep));
					
					return ResponseEntity.ok().body(dto);
				}
	
				return ResponseEntity.notFound().build();
			}
			catch(Exception ex) {
				
				ex.printStackTrace();
				
				return ResponseEntity.status(500).build();
				
			}
		};
	}

	@GetMapping("/retornaUsuarioTeste")
	public ResponseEntity<UsuarioDto> testeRetornoUsuario() {
		Pageable paginacao = PageRequest.of(0, 1, Direction.ASC, "id");

		try {
			Page<Usuario> usuario = usrRep.findByEmailAndSenha("teste@teste.com", "123456", paginacao);

			if (!usuario.isEmpty()) {
				UsuarioDto dto = new UsuarioDto(usuario.getContent().get(usuario.getContent().size() - 1));

				return ResponseEntity.ok().body(dto);
			}

			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	

}
