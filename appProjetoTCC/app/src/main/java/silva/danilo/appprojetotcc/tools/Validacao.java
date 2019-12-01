package silva.danilo.appprojetotcc.tools;

import java.util.regex.Matcher;

public class Validacao {

    private static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean emailValido(String email){

        return email.matches(REGEX_EMAIL);
    }
}
