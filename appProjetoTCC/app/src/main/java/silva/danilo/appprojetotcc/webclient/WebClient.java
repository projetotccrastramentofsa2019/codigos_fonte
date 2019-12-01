package silva.danilo.appprojetotcc.webclient;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import silva.danilo.appprojetotcc.configuracoes.ManipuladorSession;
import silva.danilo.appprojetotcc.configuracoes.ParametrosConfig;
import silva.danilo.appprojetotcc.exception.ExceptionRequisicao;

public class WebClient {

    public static final String enderecoServidor = "http://191.252.92.38";
 //   public static final String enderecoServidor = "http://192.168.1.41";

    public static final String URL_SERVICO_AUTENTICACAO = enderecoServidor + "/aut";
    public static final String URL_LISTAGEM_VEICULOS = enderecoServidor + "/app/veiculo/listarVeiculosDoUsuario";
    public static final String URL_VERIFICACAO_ALTERACAO_USUARIO = enderecoServidor + "/app/usuario/verificarUsuario";
    public static final String URL_ALTERACAO_USUARIO = enderecoServidor + "/app/usuario/alterar";
    public static final String URL_LISTAGEM_LOCALIZACAO = enderecoServidor + "/localizacao-veiculo";

    static java.net.CookieManager cm = new java.net.CookieManager();

    public String request(String strUrl, String json, Map<String, String> headers, String method) throws ExceptionRequisicao, Exception
    {
        try
        {

            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(method);
            connection.setRequestProperty("Key", "Value");
           // connection.setRequestProperty("Accept","*/*");

            if(ParametrosConfig.tokenAutenticacao != null)
            {
                connection.setRequestProperty("token-aut", ParametrosConfig.tokenAutenticacao.getHash());
            }

            String strc = "";

            if (cm.getCookieStore().getCookies().size() > 0) {

                strc = TextUtils.join(";",  cm.getCookieStore().getCookies());

                connection.setRequestProperty("Cookie", strc);

                Log.d("SESSION", strc);
            }


            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");

            if(headers != null) {
                for (String key : headers.keySet()) {
                    connection.setRequestProperty(key, headers.get(key));
                }
            }

            connection.setDoInput(true);

            if(method.equalsIgnoreCase("POST"))
            {
                connection.setDoOutput(true);

                OutputStream os = connection.getOutputStream();

                os.write(json.getBytes());
            }

            connection.connect();

            int status = connection.getResponseCode();
            int digito = status / 100;
            if(digito != 4 && digito != 5)
                return retornaRespostaRequisicao(connection);

            throw new ExceptionRequisicao("STATUS DA REQUISICAO: " + status);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    @NonNull
    private String retornaRespostaRequisicao(HttpURLConnection connection) throws IOException {
        InputStream input = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(input));

        StringBuilder resposta = new StringBuilder();

        String linha = "";

        while((linha = br.readLine()) != null)
        {
            resposta.append(linha);
        }

        br.close();


        Map<String, List<String>> headerFields = connection.getHeaderFields();
        List<String> cookiesHeader = headerFields.get("Set-Cookie");

        if(cookiesHeader != null)
        {
            for(String c : cookiesHeader)
            {
                cm.getCookieStore().add(null, HttpCookie.parse(c).get(0));
            }
        }


        return resposta.toString();
    }


    public String request(String strUrl, String json, String method) throws ExceptionRequisicao, Exception
    {
        return request(strUrl, json, null, method);
    }
}
