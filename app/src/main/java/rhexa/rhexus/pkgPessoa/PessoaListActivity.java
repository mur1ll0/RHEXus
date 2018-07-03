package rhexa.rhexus.pkgPessoa;


import android.os.Bundle;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;

import android.view.KeyEvent;
import android.view.View;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import rhexa.rhexus.R;


public class PessoaListActivity extends ListActivity {
    private PessoaDAO pessoaDAO;
    private List<Pessoa> pessoas;
    private PessoaListAdapter pessoaListAdapter;

    public final static String EXTRA_MESSAGE = "";
    private EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_list);

        pessoaDAO = new PessoaDAO(this);

        pessoas = pessoaDAO.listar();
        pessoaListAdapter = new PessoaListAdapter(this, R.layout.activity_pessoa_list_item, pessoas);
        setListAdapter(pessoaListAdapter);

        search = (EditText) findViewById(R.id.activity_pessoa_list_edtBuscar);

        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                pessoaListAdapter.clear();
                pessoas = pessoaDAO.listar(search.getText().toString());
                //produtosAdapter = new ProdutosAdapter(ProdActivity.this, R.layout.produtos_adapter_activity, produtos);
                //prodList.setAdapter(produtosAdapter);
                pessoaListAdapter.addAll(pessoas);
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        pessoaListAdapter.clear();

        String scanContent = "";
        search = (EditText) findViewById(R.id.activity_pessoa_list_edtBuscar);


                //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (scanningResult != null) {
            //we have a result
            scanContent = scanningResult.getContents();

            // display it on screen
            search.setText(scanContent);
        }
        pessoas = pessoaDAO.listar(scanContent);

        pessoaListAdapter.addAll(pessoas);
        pessoaListAdapter.notifyDataSetChanged();
    }

    public void abrirCadastroPessoa(View v){
        Intent it = new Intent(this, PessoaCadActivity.class);

        TextView id = (TextView) v.findViewById(R.id.pessoa_list_item_tvId);
        String message = id.getText().toString();
        it.putExtra(EXTRA_MESSAGE, message);

        startActivityForResult(it, 1);
    }

    public void novoCadastroPessoa(View v){
        Intent it = new Intent(this,PessoaCadActivity.class);
        String message = "";
        it.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(it,1);
    }
}

