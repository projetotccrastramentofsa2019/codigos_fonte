package silva.danilo.appprojetotcc.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DadosAutenticacaoUsuario {

    private UsuarioJson usuario;
    private String hashSessao;

    public UsuarioJson getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioJson usuario) {
        this.usuario = usuario;
    }

    public String getHashSessao() {
        return hashSessao;
    }

    public void setHashSessao(String hashSessao) {
        this.hashSessao = hashSessao;
    }

    public static DadosAutenticacaoUsuario fromJson(String strJson)
    {
        try {
            DadosAutenticacaoUsuario resp = new Gson().fromJson(strJson, DadosAutenticacaoUsuario.class);


            Log.e("TAG", resp.toString());

            return resp;
        }
        catch(Exception ex)
        {
            Log.e("TAG", "Exception: ", ex);
            return null;
        }
    }
}
