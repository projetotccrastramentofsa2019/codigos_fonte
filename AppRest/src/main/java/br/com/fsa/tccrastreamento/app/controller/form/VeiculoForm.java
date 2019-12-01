package br.com.fsa.tccrastreamento.app.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.fsa.tccrastreamento.app.exception.EmailDeUsuarioInexistenteException;
import br.com.fsa.tccrastreamento.app.model.Usuario;
import br.com.fsa.tccrastreamento.app.model.Veiculo;
import br.com.fsa.tccrastreamento.app.repository.UsuarioRepository;
import br.com.fsa.tccrastreamento.app.repository.VeiculoRepository;

public class VeiculoForm {
	
	@NotNull @NotEmpty
	private String placa;
	@NotNull @NotEmpty
	private String descricao;
	private String marca;
	private String modelo;
	private String cor;
	private Integer ano;
	private String urlFoto;
	private String emailUsuario;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public Veiculo converter(UsuarioRepository usrRep) {
		
		Optional<Usuario> optUsr = usrRep.findByEmail(this.emailUsuario);
		
		if(!optUsr.isPresent())
			throw new EmailDeUsuarioInexistenteException("O email " + this.getEmailUsuario() + " não foi encontrado");
		
		Usuario proprietario = optUsr.get();
	
		Veiculo veiculo = new Veiculo();
		veiculo.setDescricao(this.descricao);
		veiculo.setPlaca(this.placa);
		veiculo.setUrlFoto(this.urlFoto);
		veiculo.setModelo(this.modelo);
		veiculo.setMarca(this.marca);
		veiculo.setAno(this.ano);
		veiculo.setCor(this.cor);
		veiculo.setUsuario(proprietario);
		
		return veiculo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	

}
