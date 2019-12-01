package webClient;

import java.math.BigDecimal;

import com.google.gson.Gson;

import model.MensagemLocalizacaoVeiculo;
import webClient.WebClient.Metodo;

public class ClienteInterface {

	public final String enderecoServidor = "http://localhost:8080/projetoTCC";
	
	public final String enderecoServidorGravarLocalizacaoVeiculo = enderecoServidor + "/localizacao-veiculo/registrar";
	
	
	//PARAMETROS: código do usuário, código do veículo, latitude, longitude
	public final int numeroDeParametros = 4;
	
	public void enviarLocalizacaoDoVeiculo(String mensagem) 
	{
		WebClient wc = new WebClient();
		MensagemLocalizacaoVeiculo objASerEnviado = new MensagemLocalizacaoVeiculo();
		
		if(!verificarMensagem(mensagem, objASerEnviado))
		{
			System.out.println("ENVIAR LOCALIZACAO DO VEICULO: MENSAGEM INVALIDA");
			return;
		}
		
		Gson g = new Gson();
		
		String jsonEnvio = g.toJson(objASerEnviado, MensagemLocalizacaoVeiculo.class); 
		
		try 
		{
			wc.request(enderecoServidorGravarLocalizacaoVeiculo, jsonEnvio, Metodo.POST, null);
		}
		catch(Exception ex)
		{
			System.out.println("OCORREU UM PROBLEMA AO ENVIAR POST");
			ex.printStackTrace();
		}
	}
	
	public boolean verificarMensagem(String mensagem, MensagemLocalizacaoVeiculo objASerEnviado) 
	{
		try 
		{
			String[] aux = mensagem.split("#");
			
			if(aux.length != numeroDeParametros)
			{
				return false;
			}
			
			objASerEnviado.setIdUsuario(Integer.parseInt(aux[0]));
			objASerEnviado.setIdVeiculo(Integer.parseInt(aux[1]));
			objASerEnviado.setLat(new BigDecimal(aux[2]));
			objASerEnviado.setLng(new BigDecimal(aux[3]));
			
			aux = null;
			
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
		finally 
		{
			System.gc();
		}
	}
	
	
}
