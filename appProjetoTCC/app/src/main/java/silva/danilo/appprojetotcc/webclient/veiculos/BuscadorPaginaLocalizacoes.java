package silva.danilo.appprojetotcc.webclient.veiculos;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import silva.danilo.appprojetotcc.LocalVeiculoActivity;
import silva.danilo.appprojetotcc.R;
import silva.danilo.appprojetotcc.adapter.ListaLocalizacoesAdapter;
import silva.danilo.appprojetotcc.configuracoes.ParametrosConfig;
import silva.danilo.appprojetotcc.model.Veiculo;
import silva.danilo.appprojetotcc.model.dto.RespostaLocalizacaoVeiculoDto;
import silva.danilo.appprojetotcc.model.form.LocalizacaoVeiculo;
import silva.danilo.appprojetotcc.webclient.WebClient;

public class BuscadorPaginaLocalizacoes extends AsyncTask<Void, Void, String> {

    private Activity activity;
    private String url;
    private Veiculo veiculo;

    public BuscadorPaginaLocalizacoes(Activity activity, Veiculo veiculo)
    {
        this.activity = activity;
        this.veiculo = veiculo;
        url = WebClient.URL_LISTAGEM_LOCALIZACAO + "/" + ParametrosConfig.usuario.getId() + "/" + this.veiculo.getId();
    }

    @Override
    protected String doInBackground(Void... voids) {

        String resp = null;

        try
        {
            resp = new WebClient().request(url, null, "GET");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            resp = "ERRO";
        }

        return resp;
    }

    @Override
    protected void onPostExecute(String s) {

        Log.d("RESP", "" + s);

        RespostaLocalizacaoVeiculoDto resp = new Gson().fromJson(s, RespostaLocalizacaoVeiculoDto.class);

        if (resp.getItens() != null && resp.getItens().size() > 0)
        {
            ListaLocalizacoesAdapter listaLocalizacoesAdapter = new ListaLocalizacoesAdapter(activity, resp.getItens());

            ListView listViewLocalizacao = activity.findViewById(R.id.veiculo_listview_localizacao);

            listViewLocalizacao.setAdapter(listaLocalizacoesAdapter);
        }
        else
        {
            Log.d("RESP:", "NÃO RETORNOU LISTA");
        }


    }
}
