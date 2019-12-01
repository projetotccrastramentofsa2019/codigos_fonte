package silva.danilo.appprojetotcc.activityHelper;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import silva.danilo.appprojetotcc.ConfiguracaoActivity;
import silva.danilo.appprojetotcc.ContatoActivity;
import silva.danilo.appprojetotcc.DadosUsuarioActivity;
import silva.danilo.appprojetotcc.ListaVeiculosActivity;
import silva.danilo.appprojetotcc.R;
import silva.danilo.appprojetotcc.activityHelper.listener.SidenavItemSelectedListener;
import silva.danilo.appprojetotcc.configuracoes.ParametrosConfig;

public class RotinasSidenav {

    public static void carregarSidenav(AppCompatActivity activity)
    {
        SidenavItemSelectedListener listener = new SidenavItemSelectedListener(activity);

        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);

        activity.setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = activity.findViewById(R.id.drawer_layout);

        NavigationView navigationView = activity.findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(listener);

        checarItem(activity, navigationView);

        atribuirNomeUsuarioHeader(navigationView);
        atualizarFoto(activity, navigationView);
    }

    private static void checarItem(AppCompatActivity activity, NavigationView navigationView)
    {
        if(activity instanceof ListaVeiculosActivity)
        {
            MenuItem menuItem = navigationView.getMenu().findItem(R.id.sidenav_item_veiculos);
            navigationView.setCheckedItem(menuItem);
        }
        else if(activity instanceof DadosUsuarioActivity)
        {
            MenuItem menuItem = navigationView.getMenu().findItem(R.id.sidenav_item_dados_usuario);
            navigationView.setCheckedItem(menuItem);
        }
        else if(activity instanceof ContatoActivity)
        {
            MenuItem menuItem = navigationView.getMenu().findItem(R.id.sidenav_item_contato);
            navigationView.setCheckedItem(menuItem);
        }
//        else if(activity instanceof ConfiguracaoActivity)
//        {
//            MenuItem menuItem = navigationView.getMenu().findItem(R.id.sidenav_item_config);
//            navigationView.setCheckedItem(menuItem);
//        }

    }

    private static void atribuirNomeUsuarioHeader(NavigationView navigationView)
    {
        View header = navigationView.getHeaderView(0);

        TextView textView = header.findViewById(R.id.sidenav_lbl_nome_usuario);

        textView.setText(ParametrosConfig.usuario.getNome());
    }

    private static void atualizarFoto(Activity activity, NavigationView navigationView)
    {
        View header = navigationView.getHeaderView(0);

        ImageView textView = header.findViewById(R.id.sidenav_imagem_usuario);

        ActivityHelper.atualizarFotoUsuario(activity, textView);
    }

}
