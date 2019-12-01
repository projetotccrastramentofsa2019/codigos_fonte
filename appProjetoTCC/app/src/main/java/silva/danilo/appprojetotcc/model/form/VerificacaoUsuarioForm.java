package silva.danilo.appprojetotcc.model.form;

import com.google.gson.Gson;

public class VerificacaoUsuarioForm {

    private Long idUsuario;
    private String senha;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }
}
