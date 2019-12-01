package silva.danilo.appprojetotcc.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class
UsuarioAutenticacaoJson {

    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String toJson()
    {
        Gson gson = new GsonBuilder().create();

        String strJson = gson.toJson(this);

        return strJson;
    }
}
