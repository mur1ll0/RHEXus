package rhexa.rhexus;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PessoaCadActivity extends Activity {

    EditText edtId,edtNome;
    Button btnExcluir;
    PessoaDAO pessoaDAO;
    private List<Pessoa> pessoas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_cad);
        edtId = (EditText) findViewById(R.id.activity_pessoa_cad_edId);
        edtNome = (EditText) findViewById(R.id.activity_pessoa_cad_edtNome);
        Intent intent = getIntent();
        String message = intent.getStringExtra(PessoaListActivity.EXTRA_MESSAGE);

        if (message.length() > 0){
            pessoas = new ArrayList<Pessoa>();
            pessoas = pessoaDAO.listar(Integer.valueOf(message));

            for (Pessoa p : pessoas){
                edtId.setText(String.valueOf(p.getId()));
                edtNome.setText(p.getNome());
            }
        }
    }

    public void salvarCadPessoa(View v){
        Pessoa pessoa = new Pessoa();

        if (edtNome.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Informe um nome", Toast.LENGTH_SHORT).show();
        }else{
            pessoa.setNome(edtNome.getText().toString());
            if (String.valueOf(edtId.getText().toString()).length() > 0){
                pessoa.setId(Long.valueOf(edtId.getText().toString()));
            }else{
                pessoa.setId(Long.valueOf("0"));
            }
            pessoaDAO.salvarOuAlterar(pessoa);
            finish();
        }
    }
}
