package silva.danilo.appprojetotcc.webclient.veiculos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import silva.danilo.appprojetotcc.ListaVeiculosActivity;
import silva.danilo.appprojetotcc.R;
import silva.danilo.appprojetotcc.adapter.ListaVeiculosAdapter;
import silva.danilo.appprojetotcc.configuracoes.ManipuladorSession;
import silva.danilo.appprojetotcc.model.RespostaJson;
import silva.danilo.appprojetotcc.model.RespostaListarVeiculos;
import silva.danilo.appprojetotcc.model.VeiculoJson;
import silva.danilo.appprojetotcc.webclient.WebClient;

public class CarregadorListaVeiculos extends AsyncTask<Void, Void, String>
{
    private Context context;
    private ProgressDialog alertDialog;

    public CarregadorListaVeiculos(Context context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        alertDialog = ProgressDialog.show(context, "Aguarde", "Carregando Veículos...");
        alertDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {

        try
        {
            String strResp = new WebClient().request(WebClient.URL_LISTAGEM_VEICULOS + "/1", null, "GET");

            Log.d("RETORNO POST: ", "" + strResp);

            return strResp;

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onPostExecute(String s) {

        alertDialog.dismiss();

        Log.d("LISTA_VEICULO", "" + s);

        Type listType = new TypeToken<List<VeiculoJson>>() {}.getType();
        List<VeiculoJson> listaVeiculos = new Gson().fromJson(s, listType);

        ListaVeiculosActivity act = (ListaVeiculosActivity) context;

        ListView listViewVeiculos = (ListView) act.findViewById(R.id.lista_veiculos);

        ListaVeiculosAdapter adapter = new ListaVeiculosAdapter(context, listaVeiculos);

        listViewVeiculos.setAdapter(adapter);


    }
}
