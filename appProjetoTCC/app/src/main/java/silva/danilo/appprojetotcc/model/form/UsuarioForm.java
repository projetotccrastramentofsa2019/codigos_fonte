package silva.danilo.appprojetotcc.model.form;

import com.google.gson.Gson;

public class UsuarioForm {

    private String email;
    private String senha;

    public UsuarioForm(String email, String senha) {

        setEmail(email);
        setSenha(senha);
    }


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
        Gson gson = new Gson();

        return gson.toJson(this);
    }
}
