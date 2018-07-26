package rhexa.rhexus.pkgMovimento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rhexa.rhexus.R;
import rhexa.rhexus.pkgPessoa.Pessoa;
import rhexa.rhexus.pkgPessoa.PessoaDAO;
import rhexa.rhexus.pkgPessoa.PessoaListActivity;
import rhexa.rhexus.pkgProduto.Produto;
import rhexa.rhexus.pkgProduto.ProdutoDAO;

public class MovimentoCadActivity extends AppCompatActivity {


    EditText edtId,edtData,edtProdutoId,edtQuantidade,edtValor,edtProdutoNome;
    Spinner spTipo;
    private String[] tiposDescricao = new String[]{"Entrada","Saída"};
    MovimentoDAO movimentoDAO;
    ProdutoDAO produtoDAO;
    private List<Movimento> movimentos;
    private List<Produto> produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimento_cad);
        edtId = (EditText) findViewById(R.id.activity_movimento_cad_edtId);
        edtData = (EditText) findViewById(R.id.activity_movimento_cad_edtData);
        edtProdutoId = (EditText) findViewById(R.id.activity_movimento_cad_edtProdutoId);
        edtProdutoNome = (EditText) findViewById(R.id.activity_movimento_cad_edtProdutoNome);
        edtQuantidade = (EditText) findViewById(R.id.activity_movimento_cad_edtQuantidade);
        spTipo = (Spinner) findViewById(R.id.activity_movimento_cad_spTipo);
        edtValor = (EditText) findViewById(R.id.activity_movimento_cad_edtValor);
        movimentoDAO = new MovimentoDAO(this);
        produtoDAO = new ProdutoDAO(this);
        Intent intent = getIntent();
        Movimento movimento;
        String message = intent.getStringExtra(PessoaListActivity.EXTRA_MESSAGE);


        ArrayAdapter<String> aptMovimentoTipo = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, tiposDescricao);
        spTipo.setAdapter(aptMovimentoTipo);

        if (message.length() > 0){
            movimentos = new ArrayList<Movimento>();
            movimentos = movimentoDAO.listar(Integer.valueOf(message));

            for (Movimento m : movimentos){
                edtId.setText(String.valueOf(m.getId()));
                edtData.setText(m.getDataMovimentacao());
                edtProdutoId.setText(String.valueOf(m.getProdutoId()));
                spTipo.setSelection(getIndex(spTipo,m.getTipo()));
                edtQuantidade.setText(String.valueOf(m.getQuantidade()));
                edtValor.setText(String.valueOf(m.getValor()));
                produtos = produtoDAO.listar(Integer.valueOf(edtProdutoId.getText().toString()));
                edtProdutoNome.setText("");
                for (Produto p : produtos){
                    edtProdutoNome.setText(p.getNome());
                    edtValor.setText(String.valueOf(p.getPreco()));
                }
            }
        }

        edtProdutoId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    produtos = produtoDAO.listar(Integer.valueOf(edtProdutoId.getText().toString()));
                    edtProdutoNome.setText("");
                    for (Produto p : produtos){
                        edtProdutoNome.setText(p.getNome());
                        edtValor.setText(String.valueOf(p.getPreco()));
                    }
                }
            }
        });
    }

    public void salvarCadMovimento(View v){
        Movimento movimento = new Movimento();



        if (String.valueOf(edtId.getText().toString()).length() > 0){
            movimento.setId(Long.valueOf(edtId.getText().toString()));
        }else{
            movimento.setId(Long.valueOf("0"));
        }
        movimento.setQuantidade(Float.valueOf(edtQuantidade.getText().toString()));
        movimento.setDataMovimentacao(edtData.getText().toString());
        movimento.setProdutoId(Long.valueOf(edtProdutoId.getText().toString()));
        movimento.setTipo(spTipo.getSelectedItem().toString());
        movimento.setValor(Float.valueOf(edtValor.getText().toString()));
        movimentoDAO.SalvarOuAlterar(movimento);

        produtoDAO.atualizarEstoque(Integer.valueOf(edtProdutoId.getText().toString()),movimento.getTipo(),Double.valueOf(edtQuantidade.getText().toString()));

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
