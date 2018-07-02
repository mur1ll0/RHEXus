package rhexa.rhexus.pkgProduto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import rhexa.rhexus.R;

public class ProdutosAdapter extends ArrayAdapter<Produto> {

    Context context;
    List<Produto> produtos;
    int layout;
    TextView tvId, tvNome, tvQuantidade, tvCusto, tvPreco;

    public ProdutosAdapter(Context context, int layout, List<Produto> produtos){
        super(context, layout, produtos);
        this.context = context;
        this.produtos = produtos;
        this.layout = layout;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(layout, null);

        tvId = (TextView) v.findViewById(R.id.produto_adapter_id);
        tvNome = v.findViewById(R.id.produto_adapter_editNome);
        tvQuantidade = v.findViewById(R.id.produto_adapter_editQuantidade);
        tvCusto = v.findViewById(R.id.produto_adapter_editCusto);
        tvPreco = v.findViewById(R.id.produto_adapter_editPreco);

        Produto produto = produtos.get(position);

        tvId.setText(String.valueOf(produto.getId()));
        tvNome.setText(produto.getNome());
        tvQuantidade.setText(String.valueOf(produto.getQuantidade()));
        tvCusto.setText(String.valueOf(produto.getCusto()));
        tvPreco.setText(String.valueOf(produto.getPreco()));
        return v;
    }
}
