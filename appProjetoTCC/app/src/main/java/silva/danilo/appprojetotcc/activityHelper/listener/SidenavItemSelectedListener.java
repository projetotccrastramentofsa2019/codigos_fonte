package silva.danilo.appprojetotcc.activityHelper.listener;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import silva.danilo.appprojetotcc.ConfiguracaoActivity;
import silva.danilo.appprojetotcc.ContatoActivity;
import silva.danilo.appprojetotcc.DadosUsuarioActivity;
import silva.danilo.appprojetotcc.ListaVeiculosActivity;
import silva.danilo.appprojetotcc.R;

public class SidenavItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {

    private Activity activity;

    public SidenavItemSelectedListener(Activity activity)
    {
        this.activity = activity;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Intent i;

        switch(menuItem.getItemId())
        {
            case R.id.sidenav_item_dados_usuario:
                i = new Intent(activity, DadosUsuarioActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(i);
                break;

            case R.id.sidenav_item_veiculos:
                i = new Intent(activity, ListaVeiculosActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(i);
                break;

            case R.id.sidenav_item_contato:
                i = new Intent(activity, ContatoActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(i);
                break;

//            case R.id.sidenav_item_config:
//                i = new Intent(activity, ConfiguracaoActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                activity.startActivity(i);
//                break;

            case R.id.sidenav_item_sair:
                android.os.Process.killProcess(android.os.Process.myPid());
                break;

        }

        return true;
    }

}
