package rhexa.rhexus.pkgTitulo;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import rhexa.rhexus.R;

import java.util.List;

public class TituloListActivity extends ListActivity {
    private TituloDAO tituloDAO;
    private List<Titulo> titulos;
    private EditText search;
    private TituloListAdapter tituloListAdapter;

    public final static String EXTRA_MESSAGE = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titulo_list);

        tituloDAO = new TituloDAO(this);
        search = (EditText) findViewById(R.id.activity_titulo_list_edtBuscar);

        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                tituloListAdapter.clear();
                titulos = tituloDAO.listar(search.getText().toString());
                //produtosAdapter = new ProdutosAdapter(ProdActivity.this, R.layout.produtos_adapter_activity, produtos);
                //prodList.setAdapter(produtosAdapter);
                tituloListAdapter.addAll(titulos);
                return false;
            }
        });

        titulos = tituloDAO.listar();
        tituloListAdapter = new TituloListAdapter(this,R.layout.activity_titulo_list_item, titulos);
        setListAdapter(tituloListAdapter);
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data    ){
        super.onActivityResult(requestCode,resultCode,data);
        tituloListAdapter.clear();

        search = (EditText) findViewById(R.id.activity_titulo_list_edtBuscar);

        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                tituloListAdapter.clear();
                titulos = tituloDAO.listar(search.getText().toString());
                //produtosAdapter = new ProdutosAdapter(ProdActivity.this, R.layout.produtos_adapter_activity, produtos);
                //prodList.setAdapter(produtosAdapter);
                tituloListAdapter.addAll(titulos);
                return false;
            }
        });

        tituloListAdapter.addAll(titulos);
        tituloListAdapter.notifyDataSetChanged();
    }

    public void abrirCadastroTitulo(View v){
        Intent it = new Intent(this, TituloCadActivity.class);

        TextView id = (TextView) v.findViewById(R.id.titulo_list_item_tvId);
        String message = id.getText().toString();
        it.putExtra(EXTRA_MESSAGE, message);

        startActivityForResult(it, 1);
    }

    public void novoCadastroTitulo(View v){
        Intent it = new Intent(this,TituloCadActivity.class);
        String message = "";
        it.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(it,1);
    }

}
