package silva.danilo.appprojetotcc.model.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import silva.danilo.appprojetotcc.configuracoes.model.TokenAutenticacao;

public class TokenAutenticacaoDto {

    private String hash;
    private String dataCriacao;
    private String dataExpiracao;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(String dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public TokenAutenticacao retornaModelo(){

        TokenAutenticacao t = new TokenAutenticacao();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

        t.setHash(getHash());

        try
        {
            t.setDataCriacao(format.parse(getDataCriacao()));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        try
        {
            t.setDataExpiracao(format.parse(getDataExpiracao()));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return t;
    }
}
