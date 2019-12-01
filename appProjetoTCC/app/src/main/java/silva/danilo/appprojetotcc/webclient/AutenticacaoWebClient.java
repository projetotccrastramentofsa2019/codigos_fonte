package silva.danilo.appprojetotcc.webclient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import silva.danilo.appprojetotcc.ListaVeiculosActivity;
import silva.danilo.appprojetotcc.LoginActivity;
import silva.danilo.appprojetotcc.R;
import silva.danilo.appprojetotcc.configuracoes.ManipuladorSession;
import silva.danilo.appprojetotcc.model.DadosAutenticacaoUsuario;
import silva.danilo.appprojetotcc.model.RespostaJson;
import silva.danilo.appprojetotcc.model.UsuarioAutenticacaoJson;

public class AutenticacaoWebClient extends AsyncTask<Void, Void, String>
{
    private LoginActivity context;
    private ProgressDialog alertDialog;
    private RespostaJson respostaJson;

    private String cookie;

    public AutenticacaoWebClient(LoginActivity context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

        alertDialog = ProgressDialog.show(context, "Aguarde", "Realizando a autenticação...");
        alertDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {

        EditText txtEmail = (EditText) context.findViewById(R.id.login_txtLogin);
        EditText txtSenha = (EditText) context.findViewById(R.id.login_txtSenha);

        UsuarioAutenticacaoJson usr = new UsuarioAutenticacaoJson();

        usr.setEmail(txtEmail.getText().toString());
        usr.setSenha(txtSenha.getText().toString());

        try
        {
            WebClient client = new WebClient();

//            String strJson = client.request(WebClient.URL_AUTENTICACAO , usr.toJson(),"POST");
//
//            return strJson;

            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String resp) {

        try {
            RespostaJson respJson = RespostaJson.fromJson(resp);

            DadosAutenticacaoUsuario dados = new Gson().fromJson(respJson.getRetorno().toString(), DadosAutenticacaoUsuario.class);

            if (respJson.getStatusResposta().equals("OK")) {

                ManipuladorSession.setUsuarioLogado(dados.getUsuario());

                Intent intent = new Intent(context, ListaVeiculosActivity.class);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "ACONTECEU ALGO: " + resp, Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception ex)
        {
            Toast.makeText(context, "ERRO: " + ex.getMessage(), Toast.LENGTH_LONG);
        }
        finally {
            alertDialog.dismiss();
        }
    }
}
