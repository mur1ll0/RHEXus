package rhexa.rhexus;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.List;

import rhexa.rhexus.R;

public class PessoaListAdapter extends ArrayAdapter<Pessoa>{

    Context context;
    List<Pessoa> pessoas;
    int layout;
    TextView tvId, tvNome;

    public PessoaListAdapter(Context context, int layout, List<Pessoa> pessoas){
        super(context, layout, pessoas);
        this.context = context;
        this.pessoas = pessoas;
        this.layout = layout;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(layout, null);

        tvId = (TextView) v.findViewById(R.id.pessoa_list_item_tvId);
        tvNome = v.findViewById(R.id.pessoa_list_item_tvNome);

        Pessoa pessoa = pessoas.get(position);

        tvId.setText(String.valueOf(pessoa.getId()));
        tvNome.setText(pessoa.getNome());
        return v;
    }



}
