package br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.controller;

import java.util.Optional;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.controller.dto.PaginaLocalizacaoVeiculoDto;
import br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.controller.form.LocalizacaoVeiculoForm;
import br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.model.LocalizacaoVeiculo;
import br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.repository.LocalizacaoVeiculoRepository;
import br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.repository.VeiculoRepository;

@RestController
@RequestMapping("/localizacao-veiculo")
public class LocalizacaoController {
	
	@Autowired
	private VeiculoRepository veiRep;
	
	@Autowired
	private LocalizacaoVeiculoRepository locRep;
	
	@RequestMapping(value="/registrar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Callable<ResponseEntity<?>> registrarLocalizacao(@RequestBody LocalizacaoVeiculoForm lvf)
	{
		return () -> {
			
			LocalizacaoVeiculo loc = lvf.retornaModelo(veiRep);		
			
			locRep.save(loc);
			
			return ResponseEntity.ok().build();
			
		};
	}
	
	
	@RequestMapping(value="/{idUsuario}/{idVeiculo}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Callable<ResponseEntity<?>> listarLocalizacoesVeiculo(@PathVariable Long idUsuario, @PathVariable Long idVeiculo, 
			@PageableDefault(sort = "dataRegistro", direction = Direction.DESC, page = 0, size = 50) Pageable pageable, 
			HttpServletRequest req)
	{
		return () -> {
			
			String token = req.getHeader("token-aut");
			
			System.out.println("TOKEN QUE CHEGOU: " + token);
			
			Page<LocalizacaoVeiculo> page = locRep.findByVeiculoIdAndVeiculoUsuarioId(idVeiculo, idUsuario, pageable);
			
			PaginaLocalizacaoVeiculoDto pagina = new PaginaLocalizacaoVeiculoDto(page);
			
			
			return ResponseEntity.ok(pagina);
			
			
		};
	}

}
