package silva.danilo.appprojetotcc.configuracoes;

import silva.danilo.appprojetotcc.model.UsuarioJson;

public class ManipuladorSession
{
    public static boolean usuario_autenticado;

    private static UsuarioJson usuarioLogado;


    public static void setUsuarioLogado(UsuarioJson usr)
    {
        usuarioLogado = usr;
    }

    public static UsuarioJson getUsuarioLogado()
    {
        return usuarioLogado;

    }
}
