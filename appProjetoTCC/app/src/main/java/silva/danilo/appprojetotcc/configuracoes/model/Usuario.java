package silva.danilo.appprojetotcc.configuracoes.model;

import silva.danilo.appprojetotcc.model.dto.RespostaAutenticacaoDto;

public class Usuario {

    private Long id;
    private String email;
    private String nome;
    private String senha;
    private String caminhoFoto;

    public Usuario(RespostaAutenticacaoDto resp) {
        this.id = resp.getId();
        this.email = resp.getEmail();
        this.nome = resp.getNome();
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
}
