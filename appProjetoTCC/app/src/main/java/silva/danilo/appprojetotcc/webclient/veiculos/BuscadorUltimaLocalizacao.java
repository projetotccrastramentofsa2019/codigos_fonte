package silva.danilo.appprojetotcc.webclient.veiculos;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import silva.danilo.appprojetotcc.LocalVeiculoActivity;
import silva.danilo.appprojetotcc.configuracoes.ParametrosConfig;
import silva.danilo.appprojetotcc.model.Veiculo;
import silva.danilo.appprojetotcc.model.dto.RespostaLocalizacaoVeiculoDto;
import silva.danilo.appprojetotcc.model.form.LocalizacaoVeiculo;
import silva.danilo.appprojetotcc.webclient.WebClient;

public class BuscadorUltimaLocalizacao extends AsyncTask<Void, Void, String> {

    private Activity activity;
    private Veiculo veiculo;
    private String urlUltimaLocalizacao;

    public BuscadorUltimaLocalizacao(Activity activity, Veiculo veiculo)
    {
        this.activity = activity;
        this.veiculo = veiculo;

        urlUltimaLocalizacao = WebClient.URL_LISTAGEM_LOCALIZACAO + "/" + ParametrosConfig.usuario.getId() + "/" + veiculo.getId();
    }


    @Override
    protected String doInBackground(Void... voids) {

        WebClient client = new WebClient();

        try
        {
            String req = client.request(urlUltimaLocalizacao, null, "GET");
            return req;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();

            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {

        Log.d("RESP:", "" + s);

        RespostaLocalizacaoVeiculoDto resp = new Gson().fromJson(s, RespostaLocalizacaoVeiculoDto.class);

        if (resp.getItens() != null && resp.getItens().size() > 0)
        {
            Log.d("RESP:", "RETORNOU LISTA");

            veiculo.setUltimaLocalizacao(new LocalizacaoVeiculo(resp.getItens().get(0).getLat(),
                    resp.getItens().get(0).getLng()));

            Intent i = new Intent(activity, LocalVeiculoActivity.class);
            i.putExtra("veiculoSelecionado", veiculo);

            activity.startActivity(i);
        }
        else
        {
            Log.d("RESP:", "NÃO RETORNOU LISTA");
        }

    }
}
