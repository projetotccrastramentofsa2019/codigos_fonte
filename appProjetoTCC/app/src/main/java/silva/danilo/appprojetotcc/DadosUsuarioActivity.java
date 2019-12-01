package silva.danilo.appprojetotcc;

import android.app.Activity;
import android.app.Dialog;
import android.app.Instrumentation;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

import java.io.File;

import silva.danilo.appprojetotcc.activityHelper.ActivityHelper;
import silva.danilo.appprojetotcc.activityHelper.RotinasSidenav;
import silva.danilo.appprojetotcc.configuracoes.ParametrosConfig;
import silva.danilo.appprojetotcc.dao.UsuarioDao;
import silva.danilo.appprojetotcc.model.form.AlteracaoUsuarioForm;
import silva.danilo.appprojetotcc.model.form.VerificacaoUsuarioForm;
import silva.danilo.appprojetotcc.tools.Validacao;
import silva.danilo.appprojetotcc.webclient.VerificacaoAlteracaoUsuarioWebClient;

public class DadosUsuarioActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_CAMERA = 567;
    public static final int REQUEST_CODE_PIC_CROP = 123;


    interface CallbackDialog {
        void retorno(VerificacaoUsuarioForm form);
    }

    Dialog dialogVerificacao;

    public EditText txtNome;
    public EditText txtEmail;
    public EditText txtSenha;
    public EditText txtConfirmaSenha;
    CircleImageView foto;

    private ImageView btnGravar;
    private ImageView btnFoto;

    private String caminhoFoto;

    private VerificacaoAlteracaoUsuarioWebClient clienteVerificacaoAlteracao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_usuario);

        RotinasSidenav.carregarSidenav(this);

        txtNome = findViewById(R.id.dados_usuario_txt_nome);
        txtEmail = findViewById(R.id.dados_usuario_txt_email);
        txtSenha = findViewById(R.id.dados_usuario_txt_senha);
        txtConfirmaSenha = findViewById(R.id.dados_usuario_txt_confirmacao_senha);
        btnGravar = findViewById(R.id.dados_usuario_btn_gravar);
        btnFoto = findViewById(R.id.dados_usuario_btn_foto);
        foto = (CircleImageView) findViewById(R.id.dados_usuario_foto);

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(formValido())
                {
                    exibirDialogConfirmacao(new CallbackDialog() {
                        @Override
                        public void retorno(VerificacaoUsuarioForm form) {

                            clienteVerificacaoAlteracao = new VerificacaoAlteracaoUsuarioWebClient(DadosUsuarioActivity.this, form);
                            clienteVerificacaoAlteracao.execute();

                        }
                    });
                }

            }
        });

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                Log.d("NOME_FOTO", "" + caminhoFoto);
                File arquivoFoto = new File(caminhoFoto);

                Log.d("ARQ_FOTO", "" + arquivoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(DadosUsuarioActivity.this, BuildConfig.APPLICATION_ID + ".provider", arquivoFoto));
                startActivityForResult(intentCamera, REQUEST_CODE_CAMERA);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        txtNome.setText(ParametrosConfig.usuario.getNome());
        txtEmail.setText(ParametrosConfig.usuario.getEmail());
        txtSenha.setText(ParametrosConfig.usuario.getSenha());
        txtConfirmaSenha.setText(ParametrosConfig.usuario.getSenha());

        ActivityHelper.atualizarFotoUsuario(this, foto);

    }

    private void exibirDialogConfirmacao(final CallbackDialog callback)
    {
        dialogVerificacao = new Dialog(this);

        dialogVerificacao.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogVerificacao.setContentView(R.layout.layout_dialog_verificacao_usuario);
        dialogVerificacao.setCanceledOnTouchOutside(false);
        dialogVerificacao.setCancelable(false);

        Button btnOk = dialogVerificacao.findViewById(R.id.dialog_verificacao_usuario_btn_ok);
        Button btnCancel = dialogVerificacao.findViewById(R.id.dialog_verificacao_usuario_btn_cancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("ID_USUARIO", "" + ParametrosConfig.usuario.getId());
                EditText txtSenha = dialogVerificacao.findViewById(R.id.dialog_verificacao_usuario_txt_senha);

                VerificacaoUsuarioForm form = new VerificacaoUsuarioForm();
                form.setIdUsuario(ParametrosConfig.usuario.getId());
                form.setSenha(txtSenha.getText().toString());

                callback.retorno(form);
                dialogVerificacao.dismiss();
                dialogVerificacao = null;
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogVerificacao.dismiss();
                dialogVerificacao = null;
            }
        });

        dialogVerificacao.show();

    }

    public AlteracaoUsuarioForm retornaForm(){

        AlteracaoUsuarioForm form = new AlteracaoUsuarioForm();

        String nome = txtNome.getText().toString();
        String email = txtEmail.getText().toString();
        String senha = txtSenha.getText().toString();

        form.setId(ParametrosConfig.usuario.getId());
        form.setSenhaAntiga(ParametrosConfig.usuario.getSenha());
        form.setNome(nome);
        form.setEmail(email);

        if(senha.isEmpty())
            form.setSenhaNova(form.getSenhaAntiga());
        else
            form.setSenhaNova(senha);

        return form;
    }

    private boolean formValido(){

        String nome = txtNome.getText().toString();
        String email = txtEmail.getText().toString();
        String senha = txtSenha.getText().toString();
        String confirmacaoSenha = txtSenha.getText().toString();

        boolean retorno = true;

        Log.d("NOME", "" + nome);
        if(nome.isEmpty())
        {
            txtNome.setError("Digite o seu nome");
            retorno = false;
        }

        if(!Validacao.emailValido(email))
        {
            txtEmail.setError("E-mail inválido");
            retorno = false;
        }

        if(!senha.equals(confirmacaoSenha))
        {
            txtSenha.setError("A senha está divergente na confirmação");
            retorno = false;
        }

        if(senha.length() < 4)
        {
            txtSenha.setError("A senha deve ter mais de 3 caracteres.");
            retorno = false;
        }

        return retorno;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK)
        {
            if (requestCode == REQUEST_CODE_CAMERA)
            {
                Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
                Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
                foto.setImageBitmap(bitmapReduzido);
                foto.setTag(caminhoFoto);
                 ParametrosConfig.usuario.setCaminhoFoto(caminhoFoto);
                new UsuarioDao(this).atribuirCaminhoFoto(caminhoFoto, ParametrosConfig.usuario.getId());
            }
        }

    }

}
