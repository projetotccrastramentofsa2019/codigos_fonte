package silva.danilo.appprojetotcc.adapter;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import silva.danilo.appprojetotcc.R;
import silva.danilo.appprojetotcc.model.dto.LocalizacaoDto;
import silva.danilo.appprojetotcc.model.form.LocalizacaoVeiculo;

public class ListaLocalizacoesAdapter extends BaseAdapter {

    private List<LocalizacaoDto> listaLocalizacoes;
    private Context context;

    public ListaLocalizacoesAdapter(Context context, List<LocalizacaoDto> listaLocalizacoes)
    {
        this.context = context;
        this.listaLocalizacoes = listaLocalizacoes;
    }

    @Override
    public int getCount() {

        return listaLocalizacoes != null ? listaLocalizacoes.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return listaLocalizacoes != null ? listaLocalizacoes.get(position) : null;
    }

    @Override
    public long getItemId(int position) {

        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LocalizacaoDto localizacaoVeiculo = listaLocalizacoes.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;

        if(view == null)
        {
            view = inflater.inflate(R.layout.item_lista_localizacoes, parent, false);
        }

        TextView txtLat = view.findViewById(R.id.item_lista_localizacao_lblLat);
        TextView txtLng = view.findViewById(R.id.item_lista_localizacao_lblLng);
        TextView txtData = view.findViewById(R.id.item_lista_localizaca_txtData);
        TextView txtEndereco = view.findViewById(R.id.item_lista_localizacao_txtEndereco);

        String strLat = "Lat.: " + localizacaoVeiculo.getLat().toString();
        String strLng = "Lng.: " + localizacaoVeiculo.getLng().toString();

        txtLat.setText(strLat);
        txtLng.setText(strLng);
        txtEndereco.setText(obterEnderecoPorCoordenada(localizacaoVeiculo.getLat(), localizacaoVeiculo.getLng()));
        txtData.setText(localizacaoVeiculo.retornarDataFormatada());

        return view;
    }

    private String obterEnderecoPorCoordenada(BigDecimal lat, BigDecimal lng)
    {
        Geocoder geocoder = new Geocoder(context);

        try
        {
            List<Address> enderecos = geocoder.getFromLocation(lat.doubleValue(), lng.doubleValue(), 1);

            if(enderecos.size() > 0)
            {
                String rua = enderecos.get(0).getThoroughfare();
                String bairro = enderecos.get(0).getSubAdminArea();
                String cep = enderecos.get(0).getPostalCode();
                String end = rua + ", " + bairro + "," + cep;

                return end;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return "[sem endereço]";
    }
}
