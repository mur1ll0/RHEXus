package rhexa.rhexus.pkgMovimento;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import rhexa.rhexus.R;
import rhexa.rhexus.pkgPessoa.Pessoa;

public class MovimentoListAdapter extends ArrayAdapter<Movimento> {
    Context context;
    List<Movimento> movimentos;
    int layout;
    TextView tvId, tvData, tvValor;

    public MovimentoListAdapter(Context context, int layout, List<Movimento> movimentos){
        super(context, layout, movimentos);
        this.context = context;
        this.movimentos = movimentos;
        this.layout = layout;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(layout, null);

        tvId = (TextView) v.findViewById(R.id.movimento_list_item_tvId);
        tvData = (TextView) v.findViewById(R.id.movimento_list_item_tvData);
        tvValor = v.findViewById(R.id.movimento_list_item_tvValor);

        Movimento movimento = movimentos.get(position);

        tvId.setText(String.valueOf(movimento.getId()));
        tvData.setText(movimento.getDataMovimentacao());
        tvValor.setText(String.valueOf(movimento.getValor()));
        return v;
    }
}
