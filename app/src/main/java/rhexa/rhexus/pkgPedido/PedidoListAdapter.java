package rhexa.rhexus.pkgPedido;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import rhexa.rhexus.R;
import rhexa.rhexus.pkgPessoa.Pessoa;

public class PedidoListAdapter extends ArrayAdapter<Pedido>{

    Context context;
    List<Pedido> pedidos;
    int layout;
    TextView tvId, tvNome, tvEndereco;

    public PedidoListAdapter(Context context, int layout, List<Pedido> pedidos){
        super(context, layout, pedidos);
        this.context = context;
        this.pedidos = pedidos;
        this.layout = layout;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(layout, null);

        tvId = (TextView) v.findViewById(R.id.pedidos_adapter_activity_tvId);

        Pedido pedido = pedidos.get(position);

        tvId.setText(String.valueOf(pedido.getId()));
        return v;
    }
}
