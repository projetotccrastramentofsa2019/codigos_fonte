package silva.danilo.appprojetotcc.model;

import java.util.List;

public class RespostaListarVeiculos {

    private String statusResposta;
    private List<VeiculoJson> retorno;



    public String getStatusResposta() {
        return statusResposta;
    }

    public void setStatusResposta(String statusResposta) {
        this.statusResposta = statusResposta;
    }

    public List<VeiculoJson> getRetorno() {
        return retorno;
    }

    public void setListaVeiculos(List<VeiculoJson> retorno) {
        this.retorno = retorno;
    }
}
