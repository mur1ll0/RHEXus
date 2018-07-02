package rhexa.rhexus.pkgTitulo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import rhexa.rhexus.R;
import rhexa.rhexus.pkgPessoa.PessoaListAdapter;

import java.util.List;

public class TituloListAdapter extends ArrayAdapter<Titulo> {
    Context context;
    List<Titulo> titulos;
    int layout;
    TextView tvId, tvValor, tvTipo, tvEmissao;

    public TituloListAdapter(Context context, int layout, List<Titulo> titulos){
        super(context, layout,titulos);
        this.context = context;
        this.layout = layout;
        this.titulos = titulos;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(layout, null);

        tvId = (TextView) v.findViewById(R.id.titulo_list_item_tvId);
        tvEmissao = (TextView) v.findViewById(R.id.titulo_list_Item_tvEmissao);
        tvTipo = (TextView) v.findViewById(R.id.titulo_list_item_tvTipo);
        tvValor = (TextView) v.findViewById(R.id.titulo_list_item_tvValor);

        Titulo titulo = titulos.get(position);

        tvId.setText(String.valueOf(titulo.getId()));
        tvEmissao.setText(titulo.getEmissao());
        tvTipo.setText(titulo.getTipo());
        tvValor.setText(String.valueOf(titulo.getValor()));

        return v;
    }
}
