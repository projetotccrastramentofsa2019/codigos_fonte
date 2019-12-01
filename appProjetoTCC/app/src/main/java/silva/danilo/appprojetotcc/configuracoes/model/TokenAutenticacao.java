package silva.danilo.appprojetotcc.configuracoes.model;

import java.time.LocalDateTime;
import java.util.Date;

public class TokenAutenticacao {

    private String hash;
    private Date dataCriacao;
    private Date dataExpiracao;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }
}
