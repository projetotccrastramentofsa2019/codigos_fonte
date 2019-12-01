package silva.danilo.appprojetotcc.fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import silva.danilo.appprojetotcc.model.Coordenada;
import silva.danilo.appprojetotcc.model.LocalizacaoJson;
import silva.danilo.appprojetotcc.model.Veiculo;
import silva.danilo.appprojetotcc.model.VeiculoJson;

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    private Veiculo veiculo;

    private void setVeiculo(Veiculo veiculo)
    {
        this.veiculo = veiculo;
    }

    public static MapaFragment newInstance(Veiculo veiculo)
    {
        MapaFragment fragment = new MapaFragment();
//       Bundle args = new Bundle();
//       args.putSerializable("veiculoJson", veiculoJson);
//       fragment.setArguments(args);

        fragment.setVeiculo(veiculo);
        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

//        veiculoJson = (VeiculoJson) bundle.getSerializable("veiculoJson");
//        // SOMENTE PARA TESTE:
//        //
//        veiculoJson.setUltimoLocalizacaoJson(new LocalizacaoJson());
//        veiculoJson.getUltimoLocalizacaoJson().setCoordenada(new Coordenada());
//        veiculoJson.getUltimoLocalizacaoJson().getCoordenada().setLat(-23.5513);
//        veiculoJson.getUltimoLocalizacaoJson().getCoordenada().setLng(-46.6343);
        // ------------------------------------------------------------------------

        getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {

        // TESTE!!!!!!
//        LatLng posicao = pegaCoordenadaDoEndereco("Rua Marquês de Lages, 721, Vila Moraes, São Paulo, SP");
//
//        if(posicao != null)
//        {
//            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicao, 22);
//            googleMap.moveCamera(update);
//        }
//
//        LatLng coordenada = pegaCoordenadaDoEndereco("Rua Jean de la Huerta, 870, Vila Moraes, São Paulo, SP");
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        LatLng posicaoVeiculo = veiculo.getUltimaLocalizacao().getLatLng();

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoVeiculo, 20);
        googleMap.moveCamera(update);

        MarkerOptions marcador = new MarkerOptions();
        marcador.position(posicaoVeiculo);
        marcador.title(veiculo.getDescricao() + " - " + veiculo.getPlaca());

        googleMap.addMarker(marcador);
    }

    private LatLng pegaCoordenadaDoEndereco(String endereco)
    {
        try
        {
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> resultados =
                    geocoder.getFromLocationName(endereco, 1);
            if(!resultados.isEmpty())
            {
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());

                return posicao;
            }

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

}
