package silva.danilo.appprojetotcc.webclient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import silva.danilo.appprojetotcc.DadosUsuarioActivity;
import silva.danilo.appprojetotcc.configuracoes.ParametrosConfig;
import silva.danilo.appprojetotcc.exception.ExceptionRequisicao;
import silva.danilo.appprojetotcc.model.dto.RespostaVerificacaoUsuario;
import silva.danilo.appprojetotcc.model.form.VerificacaoUsuarioForm;

public class VerificacaoAlteracaoUsuarioWebClient extends AsyncTask<Void, Void, String> {

    private ProgressDialog alertDialog;
    private VerificacaoUsuarioForm verificacaoUsuarioForm;
    private DadosUsuarioActivity activity;

    public VerificacaoAlteracaoUsuarioWebClient(DadosUsuarioActivity activity, VerificacaoUsuarioForm verificacaoUsuarioForm)
    {
        this.verificacaoUsuarioForm = verificacaoUsuarioForm;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

        alertDialog = ProgressDialog.show(activity, "Aguarde", "Realizando a autenticação...");
        alertDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {

        String strResp = null;

        try
        {
            strResp = new WebClient().request(WebClient.URL_VERIFICACAO_ALTERACAO_USUARIO, verificacaoUsuarioForm.toJson(), "POST");
        }
        catch(ExceptionRequisicao ex)
        {
            ex.printStackTrace();
            strResp = "FALHA_AUT";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            strResp = "";
        }

        return strResp;
    }

    @Override
    protected void onPostExecute(String s) {

        alertDialog.dismiss();
        Log.d("RESP", "" + s);

        if(s.equals("FALHA_AUT"))
        {
            Toast.makeText(activity, "TOKEN EXPIROU", Toast.LENGTH_LONG).show();
        }
        else if(s.isEmpty())
        {
            Toast.makeText(activity, "Ocorreu um problema ao tentar autenticar", Toast.LENGTH_LONG).show();
        }
        else
        {
            RespostaVerificacaoUsuario respostaVerificacaoUsuario = new Gson().fromJson(s, RespostaVerificacaoUsuario.class);

            if(respostaVerificacaoUsuario.isVerificacaoOk())
            {
                new AlteracaoUsuarioWebClient(activity).execute();
            }
            else
            {
                Toast.makeText(activity, "Senha inválida", Toast.LENGTH_LONG).show();
            }
        }

    }
}
