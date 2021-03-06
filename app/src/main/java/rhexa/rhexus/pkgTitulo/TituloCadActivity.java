package rhexa.rhexus.pkgTitulo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import rhexa.rhexus.MainActivity;
import rhexa.rhexus.R;
import rhexa.rhexus.pkgPessoa.Pessoa;
import rhexa.rhexus.pkgPessoa.PessoaDAO;

public class TituloCadActivity extends MainActivity {

    EditText edtId,edtEmissao,edtVencimento,edtPessoaId,edtPessoaNome,edtValor,edtValorBaixa;
    Spinner spTipo;
    private String[] tiposDescricao = new String[]{"Débito","Crédito"};
    private TituloDAO tituloDAO;
    private PessoaDAO pessoaDAO;
    private List<Titulo> titulos;
    private List<Pessoa> pessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titulo_cad);
        edtId = (EditText) findViewById(R.id.activity_titulo_cad_edtId);
        edtEmissao = (EditText) findViewById(R.id.activity_titulo_cad_edtEmissao);
        edtVencimento = (EditText) findViewById(R.id.activity_titulo_cad_edtVencimento);
        edtPessoaId = (EditText) findViewById(R.id.activity_titulo_cad_edtPessoaId);
        edtPessoaNome = (EditText) findViewById(R.id.activity_titulo_cad_edtPessoaNome);
        edtValor = (EditText) findViewById(R.id.activity_titulo_cad_edtValor);
        edtValorBaixa = (EditText) findViewById(R.id.activity_titulo_cad_edtValorBaixa);
        spTipo = (Spinner) findViewById(R.id.activity_titulo_cad_spTipo);

        tituloDAO = new TituloDAO(this);
        pessoaDAO = new PessoaDAO(this);

        Intent intent = getIntent();

        Titulo titulo;
        String message = intent.getStringExtra(TituloListActivity.EXTRA_MESSAGE);

        ArrayAdapter<String> aptTituloTipo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tiposDescricao);
        spTipo.setAdapter(aptTituloTipo);

        if (message.length() > 0) {
            titulos = new ArrayList<Titulo>();
            titulos = tituloDAO.listar(Integer.valueOf(message));
            for (Titulo t : titulos) {
                edtId.setText(String.valueOf(t.getId()));
                edtEmissao.setText(t.getEmissao());
                spTipo.setSelection(getIndex(spTipo,t.getTipo()));
                edtVencimento.setText(t.getVencimento());
                edtPessoaId.setText(String.valueOf(t.getPessoaID()));
                edtValor.setText(String.valueOf(t.getValor()));
                edtValorBaixa.setText(String.valueOf(t.getValorBaixa()));
                edtPessoaNome.setText("");
                pessoas = pessoaDAO.listar(Integer.valueOf(edtPessoaId.getText().toString()));
                for (Pessoa p : pessoas){
                    edtPessoaNome.setText(p.getNome());
                }
            }
        }

        edtPessoaId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    pessoas = pessoaDAO.listar(Integer.valueOf(edtPessoaId.getText().toString()));
                    edtPessoaNome.setText("");
                    for (Pessoa p : pessoas){
                        edtPessoaNome.setText(p.getNome());
                    }
                }
            }
        });
    }

    public void salvarCadTitulo(View v){
        Titulo titulo = new Titulo();

        if (String.valueOf(edtId.getText()).length() > 0) {
            titulo.setId(Long.valueOf(edtId.getText().toString()));
        }else {
            titulo.setId(Long.valueOf("0"));
        }

        if (edtPessoaId.getText().toString().length() == 0)
            edtPessoaId.setText("0");
        if (edtValor.getText().toString().length() == 0)
            edtValor.setText("0.00");
        if (edtValorBaixa.getText().toString().length() == 0)
            edtValorBaixa.setText("0.00");


        titulo.setEmissao(edtEmissao.getText().toString());
        titulo.setPessoaID(Long.valueOf(edtPessoaId.getText().toString()));
        titulo.setTipo(spTipo.getSelectedItem().toString());
        titulo.setValor(Float.valueOf(edtValor.getText().toString()));
        titulo.setValorBaixa(Float.valueOf(edtValorBaixa.getText().toString()));
        titulo.setVencimento(edtVencimento.getText().toString());

        tituloDAO.salvarOuAlterar(titulo);

        finish();
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

















