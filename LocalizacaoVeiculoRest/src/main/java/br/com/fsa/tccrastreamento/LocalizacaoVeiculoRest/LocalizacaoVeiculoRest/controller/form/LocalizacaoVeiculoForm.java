package br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.controller.form;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.model.LocalizacaoVeiculo;
import br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.model.Veiculo;
import br.com.fsa.tccrastreamento.LocalizacaoVeiculoRest.LocalizacaoVeiculoRest.repository.VeiculoRepository;

public class LocalizacaoVeiculoForm {
	
	private Long idUsuario;
	private Long idVeiculo;
	private BigDecimal lat;
	private BigDecimal lng;
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdVeiculo() {
		return idVeiculo;
	}
	public void setIdVeiculo(Long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}
	public BigDecimal getLat() {
		return lat;
	}
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	public BigDecimal getLng() {
		return lng;
	}
	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
	
	public LocalizacaoVeiculo retornaModelo(VeiculoRepository veiRep) 
	{

		Optional<Veiculo> opt = veiRep.findByIdAndUsuarioId(this.getIdVeiculo(), this.getIdUsuario());
		
		if(!opt.isPresent())
		{
			return null;
		}	
		
		Veiculo v = opt.get();
		
		LocalizacaoVeiculo l = new LocalizacaoVeiculo();
		
		l.setLat(this.lat);
		l.setLng(this.lng);
		l.setVeiculo(v);
		
		return l;
	}

}
