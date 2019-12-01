package silva.danilo.appprojetotcc.webclient;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class TesteWebClient extends AsyncTask<Void, Void, String> {

    private Context context;

    public TesteWebClient(Context context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {

        Log.d("POST: ", s);

        Toast.makeText(context, "RETORNOU!: " + s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected String doInBackground(Void... voids) {

        try
        {
            WebClient client = new WebClient();

            String strRetorno = client.request("http://10.0.2.2:8080/projetoTCC/" +
                    "", "{ \"teste\": \"TESTE\" }", "POST");

            return strRetorno;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }


    }

}
