package rhexa.rhexus;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Console;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PessoaCadActivity extends Activity {

    EditText edtId,edtNome,edtCPFCNPJ,edtTipo,edtEndereco,edtTelefone;
    Spinner spTipo;
    private String[] tiposDescricao = new String[]{"Física","Jurídica"};
    PessoaDAO pessoaDAO;
    private List<Pessoa> pessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_cad);
        edtId = (EditText) findViewById(R.id.activity_pessoa_cad_edtId);
        edtNome = (EditText) findViewById(R.id.activity_pessoa_cad_edtNome);
        edtCPFCNPJ = (EditText) findViewById(R.id.activity_pessoa_cad_edtCPFCNPJ);
        edtEndereco = (EditText) findViewById(R.id.activity_pessoa_cad_edtEndereco);
        spTipo = (Spinner) findViewById(R.id.activity_pessoa_cad_spTipo);
        edtTelefone = (EditText) findViewById(R.id.activity_pessoa_cad_edtTelefone);
        pessoaDAO = new PessoaDAO(this);
        Intent intent = getIntent();
        Pessoa pessoa;
        String message = intent.getStringExtra(PessoaListActivity.EXTRA_MESSAGE);


        ArrayAdapter<String> aptPessoaTipo = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, tiposDescricao);
        spTipo.setAdapter(aptPessoaTipo);

        if (message.length() > 0){
            pessoas = new ArrayList<Pessoa>();
            pessoas = pessoaDAO.listar(Integer.valueOf(message));

            for (Pessoa p : pessoas){
                edtId.setText(String.valueOf(p.getId()));
                edtNome.setText(p.getNome());
                edtTelefone.setText(p.getTelefone());
                spTipo.setSelection(getIndex(spTipo,p.getTipo()));
                edtEndereco.setText(p.getEndereco());
                edtCPFCNPJ.setText(p.getCpfcnpj());
            }
        }
    }

    public void salvarCadPessoa(View v){
        Pessoa pessoa = new Pessoa();

        if (edtNome.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Informe o nome", Toast.LENGTH_SHORT).show();
        }else{
            pessoa.setNome(edtNome.getText().toString());
            if (String.valueOf(edtId.getText().toString()).length() > 0){
                pessoa.setId(Long.valueOf(edtId.getText().toString()));
                System.out.println("tem valor no Id");
            }else{
                pessoa.setId(Long.valueOf("0"));
                System.out.println("Não tem valor no id");
            }
            pessoa.setCpfcnpj(edtCPFCNPJ.getText().toString());
            pessoa.setEndereco(edtEndereco.getText().toString());
            pessoa.setTelefone(edtTelefone.getText().toString());
            pessoa.setTipo(spTipo.getSelectedItem().toString());
            pessoaDAO.SalvarOuAlterar(pessoa);

            finish();
        }
    }


    private int getIndex(Spinner spinner, String myString){ // Retorna a posição da spinner conforme o valor do banco

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }
}
