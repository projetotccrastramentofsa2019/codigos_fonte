package silva.danilo.appprojetotcc.webclient.veiculos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import silva.danilo.appprojetotcc.ListaVeiculosActivity;
import silva.danilo.appprojetotcc.configuracoes.ParametrosConfig;
import silva.danilo.appprojetotcc.configuracoes.model.Usuario;
import silva.danilo.appprojetotcc.exception.ExceptionRequisicao;
import silva.danilo.appprojetotcc.model.dto.RespostaAutenticacaoDto;
import silva.danilo.appprojetotcc.model.form.UsuarioForm;
import silva.danilo.appprojetotcc.webclient.WebClient;

public class ServicoAutenticacaoWebClient extends AsyncTask<Void, Void, String>
{
    private UsuarioForm usuarioForm;
    private Activity activity;

    private ProgressDialog alertDialog;


    public ServicoAutenticacaoWebClient(Activity activity, UsuarioForm usuarioForm)
    {
        this.usuarioForm = usuarioForm;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        alertDialog = ProgressDialog.show(activity, "Aguarde", "Autenticando usuário...");
        alertDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {

        Log.d("POST", "chamou do in background");
        WebClient client = new WebClient();
        String response = null;

        try
        {
            response = client.request(WebClient.URL_SERVICO_AUTENTICACAO, usuarioForm.toJson(), "POST");

            return response;
        }
        catch(ExceptionRequisicao ex)
        {
            Log.d("ERRO", "EXCEPTION REQ");
            ex.printStackTrace();
            response = "ERRO_AUT";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        Log.d("POST", "response do in background: " + response);
        return response;
    }

    @Override
    protected void onPostExecute(String resposta) {

        Log.d("RES POST: ", "" + resposta);

        alertDialog.dismiss();

        if(resposta == null)
        {
            Toast.makeText(activity, "Ocorreu um problema ao tentar autenticar o usuário", Toast.LENGTH_LONG).show();
        }
        else if(resposta.equals("ERRO_AUT"))
        {
            Toast.makeText(activity, "Usuário e/ou senha inválidos", Toast.LENGTH_LONG).show();
        }
        else
        {
            RespostaAutenticacaoDto resp = new Gson().fromJson(resposta, RespostaAutenticacaoDto.class);

            ParametrosConfig.tokenAutenticacao = resp.getToken().retornaModelo();
            ParametrosConfig.usuario = new Usuario(resp);
            ParametrosConfig.usuario.setSenha(usuarioForm.getSenha());

            Intent i = new Intent(activity, ListaVeiculosActivity.class);

            activity.startActivity(i);
        }

    }
}
