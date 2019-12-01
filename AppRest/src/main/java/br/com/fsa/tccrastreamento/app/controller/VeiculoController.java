package br.com.fsa.tccrastreamento.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fsa.tccrastreamento.app.controller.dto.VeiculoDto;
import br.com.fsa.tccrastreamento.app.controller.form.VeiculoForm;
import br.com.fsa.tccrastreamento.app.model.Veiculo;
import br.com.fsa.tccrastreamento.app.repository.UsuarioRepository;
import br.com.fsa.tccrastreamento.app.repository.VeiculoRepository;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
	
	@Autowired
	private VeiculoRepository veiRep;
	
	@Autowired
	private UsuarioRepository usrRep;
	
	@RequestMapping(value = "/teste")
	public String teste() 
	{
		return "teste ok!!";
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public Callable<ResponseEntity<VeiculoDto>> cadastrarVeiculo(@RequestBody @Valid VeiculoForm form)
	{
		return () -> {
			
			Veiculo veiculo = form.converter(usrRep);
			
			veiRep.save(veiculo);
			
			return ResponseEntity.ok().body(new VeiculoDto(veiculo));
			
		};
	}
	
	@RequestMapping(value = "/listarVeiculosDoUsuario/{idUsuario}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Callable<ResponseEntity<List<VeiculoDto>>> listarVeiculos(@PathVariable Long idUsuario)
	{
		return () ->{
			
			List<Veiculo> listaVeiculos= veiRep.findByUsuarioId(idUsuario);
			List<VeiculoDto> listaRetorno = new ArrayList<>();
			
			for(Veiculo v : listaVeiculos) 
			{
				listaRetorno.add(new VeiculoDto(v));
			}
			
			return ResponseEntity.ok().body(listaRetorno);
			
		};
	}
	

}












