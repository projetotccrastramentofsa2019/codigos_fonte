package silva.danilo.appprojetotcc;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import silva.danilo.appprojetotcc.activityHelper.ActivityHelper;
import silva.danilo.appprojetotcc.activityHelper.RotinasSidenav;
import silva.danilo.appprojetotcc.activityHelper.listener.SidenavItemSelectedListener;
import silva.danilo.appprojetotcc.configuracoes.ParametrosConfig;
import silva.danilo.appprojetotcc.model.Veiculo;
import silva.danilo.appprojetotcc.model.VeiculoJson;
import silva.danilo.appprojetotcc.webclient.veiculos.CarregadorListaVeiculos;

public class ListaVeiculosActivity extends AppCompatActivity {

    private ListView listaVeiculos;
    private TextView labelNomeUsuario;
    private ImageView imagemUsuario;
    private CarregadorListaVeiculos clienteListaVeiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_veiculos);

        listaVeiculos = (ListView) findViewById(R.id.lista_veiculos);
        labelNomeUsuario = findViewById(R.id.veiculo_txt_descricao_veiculo);
        imagemUsuario = findViewById(R.id.lista_veiculos_imagem_usuario);
        listaVeiculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                abrirDetalheVeiculo(position);

            }
        });
    }

    private void abrirDetalheVeiculo(int position)
    {
        VeiculoJson veiculoJson = (VeiculoJson) listaVeiculos.getItemAtPosition(position);

        Veiculo veiculo = new Veiculo(veiculoJson);

        Intent i = new Intent(ListaVeiculosActivity.this, VeiculoActivity.class);
        i.putExtra("veiculoSelecionado", veiculo);

        startActivity(i);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu_principal, menu);
//
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//
//        switch(item.getItemId())
//        {
//            case R.id.item_menu_sidenav:
//                Toast.makeText(this, "TESTE", Toast.LENGTH_LONG).show();
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onResume()
    {
        super.onResume();

        atualizarView();
    }

    private void atualizarView() {
        labelNomeUsuario.setText(ParametrosConfig.usuario.getNome());
        RotinasSidenav.carregarSidenav(this);
        ActivityHelper.atualizarFotoUsuario(this, imagemUsuario);

        carregarListaVeiculos();
    }

    private void carregarListaVeiculos()
    {
        clienteListaVeiculos = new CarregadorListaVeiculos(this);

        clienteListaVeiculos.execute();
    }

}
