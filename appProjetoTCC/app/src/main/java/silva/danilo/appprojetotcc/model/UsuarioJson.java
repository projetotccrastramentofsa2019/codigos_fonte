package silva.danilo.appprojetotcc.model;

import java.util.List;

public class UsuarioJson {

    private Integer codigoUsuario;
    private String nome;
    private String email;
    private String senha;
    private List<VeiculoJson> listaVeiculos;

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<VeiculoJson> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<VeiculoJson> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
