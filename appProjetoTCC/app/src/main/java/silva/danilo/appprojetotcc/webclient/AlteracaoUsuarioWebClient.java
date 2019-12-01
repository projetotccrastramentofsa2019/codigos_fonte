package silva.danilo.appprojetotcc.webclient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;

import silva.danilo.appprojetotcc.DadosUsuarioActivity;
import silva.danilo.appprojetotcc.configuracoes.ParametrosConfig;
import silva.danilo.appprojetotcc.exception.ExceptionRequisicao;
import silva.danilo.appprojetotcc.model.dto.UsuarioDto;
import silva.danilo.appprojetotcc.model.form.AlteracaoUsuarioForm;

public class AlteracaoUsuarioWebClient extends AsyncTask<Void, Void, String> {

    public AlteracaoUsuarioForm form;
    public Activity activity;
    private ProgressDialog alertDialog;

    public AlteracaoUsuarioWebClient(DadosUsuarioActivity activity)
    {
        this.activity = activity;
        this.form = activity.retornaForm();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        alertDialog = ProgressDialog.show(activity, "Aguarde", "Enviando dados...");
        alertDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {

        String strResp = null;

        try
        {
            strResp = new WebClient().request(WebClient.URL_ALTERACAO_USUARIO, form.toJson(), "POST");


            return strResp;
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

        if(s.isEmpty())
        {
            Toast.makeText(activity, "Ocorreu um problema ao tentar alterar os dados", Toast.LENGTH_LONG).show();
        }
        else if(s.equals("FALHA_AUT"))
        {
            Toast.makeText(activity, "TOKEN EXPIROU", Toast.LENGTH_LONG).show();
        }
        else
        {
            UsuarioDto usuarioDto = new Gson().fromJson(s, UsuarioDto.class);

            ParametrosConfig.usuario.setSenha(form.getSenhaNova());
            ParametrosConfig.usuario.setNome(usuarioDto.getNome());
            ParametrosConfig.usuario.setId(usuarioDto.getId());
            ParametrosConfig.usuario.setEmail(usuarioDto.getEmail());

            Toast.makeText(activity.getApplicationContext(), "Dados salvos com sucesso!", Toast.LENGTH_LONG).show();


            activity.finish();
        }

    }
}
