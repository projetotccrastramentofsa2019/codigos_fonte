package silva.danilo.appprojetotcc;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import silva.danilo.appprojetotcc.model.form.UsuarioForm;
import silva.danilo.appprojetotcc.webclient.AutenticacaoWebClient;
import silva.danilo.appprojetotcc.webclient.veiculos.ServicoAutenticacaoWebClient;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText txtEmail;
    private EditText txtSenha;
    private ServicoAutenticacaoWebClient clienteAutenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,

                }, 999);

        btnLogin = (Button) findViewById(R.id.login_btnLogin);

        txtEmail = findViewById(R.id.login_txtLogin);
        txtSenha = findViewById(R.id.login_txtSenha);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validarFormularioLogin())
                {
                    enviarRequisicaoPostLogin();
                }
            }
        });
    }

    private UsuarioForm retornaObjetoFormulario()
    {
        return new UsuarioForm(txtEmail.getText().toString(), txtSenha.getText().toString());
    }

    private void enviarRequisicaoPostLogin()
    {
        clienteAutenticacao = new ServicoAutenticacaoWebClient(
                LoginActivity.this,
                retornaObjetoFormulario()
        );

        clienteAutenticacao.execute();
    }

    private boolean validarFormularioLogin()
    {
        String textoEmail = txtEmail.getText().toString();
        String textoSenha = txtSenha.getText().toString();

        if(textoEmail == null || textoEmail.isEmpty())
        {
            Toast.makeText(this, "Digite o seu e-mail", Toast.LENGTH_LONG).show();
            return false;
        }
        if(textoSenha == null || textoEmail.isEmpty())
        {
            Toast.makeText(this, "Digite a sua senha", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }



}
