package rhexa.rhexus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import rhexa.rhexus.R;
import java.util.List;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PessoaListActivity extends ListActivity {
    private PessoaDAO pessoaDAO;
    private List<Pessoa> pessoas;
    private PessoaListAdapter pessoaListAdapter;
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
}

