package silva.danilo.appprojetotcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import silva.danilo.appprojetotcc.activityHelper.RotinasSidenav;
import silva.danilo.appprojetotcc.model.Veiculo;
import silva.danilo.appprojetotcc.model.VeiculoJson;
import silva.danilo.appprojetotcc.webclient.veiculos.BuscadorPaginaLocalizacoes;
import silva.danilo.appprojetotcc.webclient.veiculos.BuscadorUltimaLocalizacao;

public class VeiculoActivity extends AppCompatActivity {

    private Button btnUltimaLocalizacao;
    private TextView txtMarca;
    private TextView txtModelo;
    private TextView txtDescricaoVeiculo;
    private TextView txtPlaca;
    private TextView txtAno;
    private TextView txtCor;
    private TextView txtCodigoVeiculo;
    private Veiculo veiculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiculo);

        RotinasSidenav.carregarSidenav(this);

        btnUltimaLocalizacao = findViewById(R.id.veiculo_botao_ultima_localizacao);
        txtDescricaoVeiculo = findViewById(R.id.veiculo_txt_descricao_veiculo);
        txtMarca = findViewById(R.id.veiculo_txt_marca);
        txtPlaca = findViewById(R.id.veiculo_txt_placa);
        txtModelo = findViewById(R.id.veiculo_txt_modelo);
        txtCor = findViewById(R.id.veiculo_txt_cor);
        txtCodigoVeiculo = findViewById(R.id.veiculo_txt_codigo_veiculo);

        try
        {
            veiculo = (Veiculo) getIntent().getSerializableExtra("veiculoSelecionado");

            txtCodigoVeiculo.setText("Código do Veículo: " + veiculo.getId());
            txtDescricaoVeiculo.setText(veiculo.getDescricao());
            txtModelo.setText(veiculo.getModelo());
            txtPlaca.setText(veiculo.getPlaca());
            txtAno.setText(String.valueOf(veiculo.getAno()));
            txtCor.setText(veiculo.getCor());
            txtMarca.setText(veiculo.getMarca());

        }
        catch(Exception e)
        {
            Log.d("VEICULO_ACT", "PROBLEMA AO OBTER VEICULO SELECIONADO");
            e.printStackTrace();
        }

        btnUltimaLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(veiculo != null)
                {
                    new BuscadorUltimaLocalizacao(VeiculoActivity.this, veiculo).execute();
                }
                else
                {
                    Toast.makeText(VeiculoActivity.this, "Ocorreu um problema ao obter os dados do veiculo selecionado", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(veiculo != null)
        {
            new BuscadorPaginaLocalizacoes(this, veiculo).execute();
        }
    }
}
