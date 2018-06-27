package rhexa.rhexus;


import android.os.Bundle;
import rhexa.rhexus.R;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;

import android.view.View;

import android.widget.TextView;


public class PessoaListActivity extends ListActivity {
    private PessoaDAO pessoaDAO;
    private List<Pessoa> pessoas;
    private PessoaListAdapter pessoaListAdapter;
    public final static String EXTRA_MESSAGE = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_list);

        pessoaDAO = new PessoaDAO(this);

        pessoas = pessoaDAO.listar();
        pessoaListAdapter = new PessoaListAdapter(this, R.layout.activity_pessoa_list_item, pessoas);
        setListAdapter(pessoaListAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        pessoaListAdapter.clear();
        pessoas = pessoaDAO.listar();

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

        startActivityForResult(it,1);
    }
}

