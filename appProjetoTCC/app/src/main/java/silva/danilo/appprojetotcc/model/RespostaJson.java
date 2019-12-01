package silva.danilo.appprojetotcc.model;

import com.google.gson.Gson;

public class RespostaJson {

    private String statusResposta;
    private Object retorno;

    public String getStatusResposta() {
        return statusResposta;
    }

    public void setStatusResposta(String statusResposta) {
        this.statusResposta = statusResposta;
    }

    public Object getRetorno() {
        return retorno;
    }

    public void setRetorno(Object retorno) {
        this.retorno = retorno;
    }

    public static RespostaJson fromJson(String strJson)
    {
        RespostaJson resp = new Gson().fromJson(strJson, RespostaJson.class);

        return resp;
    }
}
