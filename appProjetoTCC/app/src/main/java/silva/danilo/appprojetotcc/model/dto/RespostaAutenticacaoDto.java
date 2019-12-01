package silva.danilo.appprojetotcc.model.dto;

import silva.danilo.appprojetotcc.configuracoes.model.TokenAutenticacao;

public class RespostaAutenticacaoDto {

    private Long id;
    private String nome;
    private String email;
    private TokenAutenticacaoDto token;

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

    public TokenAutenticacaoDto getToken() {
        return token;
    }

    public void setToken(TokenAutenticacaoDto token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
