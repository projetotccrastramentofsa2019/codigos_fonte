package silva.danilo.appprojetotcc.model.dto;

public class RespostaVerificacaoUsuario {

    private boolean verificacaoOk;

    private UsuarioDto usuario;

    public boolean isVerificacaoOk() {
        return verificacaoOk;
    }

    public void setVerificacaoOk(boolean verificacaoOk) {
        this.verificacaoOk = verificacaoOk;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }
}
