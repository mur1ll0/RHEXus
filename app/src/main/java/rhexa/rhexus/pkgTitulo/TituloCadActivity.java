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

import rhexa.rhexus.R;

public class TituloCadActivity extends AppCompatActivity {

    EditText edtId,edtEmissao,edtVencimento,edtPessoaId,edtPessoaNome,edtValor,edtValorBaixa;
    Spinner spTipo;
    private String[] tiposDescricao = new String[]{"Débito","Crédito"};
    TituloDAO tituloDAO;
    private List<Titulo> titulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titulo_cad);
        edtId = (EditText) findViewById(R.id.activity_pessoa_cad_edtId);
        edtEmissao = (EditText) findViewById(R.id.activity_titulo_cad_edtEmissao);
        edtVencimento = (EditText) findViewById(R.id.activity_titulo_cad_edtVencimento);
        edtPessoaId = (EditText) findViewById(R.id.activity_titulo_cad_edtPessoaId);
        edtPessoaNome = (EditText) findViewById(R.id.activity_titulo_cad_edtPessoaNome);
        edtValor = (EditText) findViewById(R.id.activity_titulo_cad_edtValor);
        edtValorBaixa = (EditText) findViewById(R.id.activity_titulo_cad_edtValorBaixa);

        tituloDAO = new TituloDAO(this);

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
            }
        }
    }

    public void salvarCadTitulo(View v){
        Titulo titulo = new Titulo();

        if (String.valueOf(edtId.getText()).length() > 0) {
            titulo.setId(Long.valueOf(edtId.getText().toString()));
        }else {
            titulo.setId(Long.valueOf("0"));
        }

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

















