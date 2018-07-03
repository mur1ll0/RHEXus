package rhexa.rhexus.pkgMovimento;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import rhexa.rhexus.R;
import rhexa.rhexus.pkgPessoa.PessoaCadActivity;

public class MovimentoListActivity extends ListActivity {

    private MovimentoDAO movimentoDAO;
    private List<Movimento> movimentos;
    private MovimentoListAdapter movimentoListAdapter;

    public final static String EXTRA_MESSAGE = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimento_list);

        movimentoDAO = new MovimentoDAO(this);

        movimentos = movimentoDAO.listar();
        movimentoListAdapter = new MovimentoListAdapter(this, R.layout.activity_movimento_list_item,movimentos);
        setListAdapter(movimentoListAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        movimentoListAdapter.clear();
        movimentos = movimentoDAO.listar();

        movimentoListAdapter.addAll(movimentos);
        movimentoListAdapter.notifyDataSetChanged();
    }

    public void abrirCadastroMovimento(View v){
        Intent it = new Intent(this, MovimentoCadActivity.class);

        TextView id = (TextView) v.findViewById(R.id.movimento_list_item_tvId);
        String message = id.getText().toString();
        it.putExtra(EXTRA_MESSAGE, message);

        startActivityForResult(it, 1);
    }

    public void novoCadastroMovimento(View v){
        Intent it = new Intent(this,MovimentoCadActivity.class);
        String message = "";
        it.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(it,1);
    }
}
