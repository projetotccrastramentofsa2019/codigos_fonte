package silva.danilo.appprojetotcc.model.form;

import com.google.gson.Gson;

public class AlteracaoUsuarioForm {

    private Long id;
    private String email;
    private String nome;
    private String senhaAntiga;
    private String senhaNova;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public void setSenhaAntiga(String senhaAntiga) {
        this.senhaAntiga = senhaAntiga;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }
}
