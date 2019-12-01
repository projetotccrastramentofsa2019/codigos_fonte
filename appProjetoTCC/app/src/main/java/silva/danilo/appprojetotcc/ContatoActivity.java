package silva.danilo.appprojetotcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import silva.danilo.appprojetotcc.activityHelper.RotinasSidenav;

public class ContatoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        RotinasSidenav.carregarSidenav(this);
    }
}
