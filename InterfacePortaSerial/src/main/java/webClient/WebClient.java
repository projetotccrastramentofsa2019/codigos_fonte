package webClient;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class WebClient {
	
	public enum Metodo {
		GET, POST
	} 
	
	public void configHeaderRequest(HttpURLConnection urlConn, Metodo metodo, Map<String, String> headers) throws Exception
	{
		switch(metodo)
		{
			case GET:
				urlConn.setRequestMethod("GET");
				break;
				
			case POST:
				urlConn.setRequestMethod("POST");
				break;
		}
		
		urlConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		urlConn.setRequestProperty("Accept", "application/json");
		urlConn.setRequestProperty("token-aut", "CYGROZRGGHCOKQNPNJJBBRLPIMRIPXAGVEEFBJTSMKVILMBTROHTUXHZLCWWJQQOAZWWQVHMIVYGPZDYLPMSMFDMESRXZPBSXEBS");
	}
	
	public String request(String strUrl, String reqBody, Metodo metodo, Map<String, String> headers) throws Exception 
	{
		String retorno = "";
		URL url = new URL(strUrl);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		
		configHeaderRequest(urlConn, metodo, headers);
		
		urlConn.setDoInput(true);
		
		if(!metodo.equals(Metodo.GET))
		{
			urlConn.setDoOutput(true);
		
			OutputStream os = urlConn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			
			writer.write(reqBody);
			
			writer.flush();
			writer.close();
			os.close();
		}
		
		InputStream input = urlConn.getInputStream();
		InputStreamReader reader = new InputStreamReader(input);
		
		int data = reader.read();
		
		while(data != -1)
		{
			char current = (char) data;
			
			retorno += current;
			data = reader.read();
		}
		
		return retorno;
	}
	

}