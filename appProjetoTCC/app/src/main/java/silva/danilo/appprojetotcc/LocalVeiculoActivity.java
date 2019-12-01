package silva.danilo.appprojetotcc;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.SupportMapFragment;

import silva.danilo.appprojetotcc.fragment.MapaFragment;
import silva.danilo.appprojetotcc.model.Veiculo;
import silva.danilo.appprojetotcc.model.VeiculoJson;

public class LocalVeiculoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_veiculo);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();

        Veiculo veiculoSelecionado = (Veiculo) getIntent().getSerializableExtra("veiculoSelecionado");

        tx.replace(R.id.frameMapa, MapaFragment.newInstance(veiculoSelecionado));
        tx.commit();

    }
}
