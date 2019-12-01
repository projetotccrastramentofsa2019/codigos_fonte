package silva.danilo.appprojetotcc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import silva.danilo.appprojetotcc.R;
import silva.danilo.appprojetotcc.model.VeiculoJson;

public class ListaVeiculosAdapter extends BaseAdapter {

    private List<VeiculoJson> listaVeiculos;
    private Context context;

    public ListaVeiculosAdapter(Context context, List<VeiculoJson> listaVeiculos)
    {
        this.context = context;

        this.listaVeiculos = listaVeiculos != null ? listaVeiculos : new ArrayList<VeiculoJson>();
    }

    @Override
    public int getCount() {
        return listaVeiculos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaVeiculos.get(position);
    }

    @Override
    public long getItemId(int position) {

        return listaVeiculos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VeiculoJson veiculo = listaVeiculos.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;

        if(view == null)
        {
            view = inflater.inflate(R.layout.item_lista_veiculos, parent, false);
        }

        TextView campoModeloVeiculo = (TextView) view.findViewById(R.id.item_modelo_veiculo);
        TextView campoPlacaVeiculo = (TextView) view.findViewById(R.id.item_placa_veiculo);

        campoModeloVeiculo.setText(veiculo.getDescricao());
        campoPlacaVeiculo.setText(veiculo.getPlaca());


        return view;
    }
}
