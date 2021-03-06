package rhexa.rhexus.pkgProduto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import rhexa.rhexus.R;
import rhexa.rhexus.pkgPedido.PedidoListActivity;
import rhexa.rhexus.pkgPessoa.PessoaListActivity;

public class newProdActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EditText edtId, edtNome, edtDesc, edtCodigo, edtCusto, edtPreco, edtQuantidade, edtMargem;
    Switch swAtivo;
    ProdutoDAO produtoDAO;
    private List<Produto> produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_newprod);
        edtId = (EditText) findViewById(R.id.new_prod_layout_editId);
        edtNome = (EditText) findViewById(R.id.new_prod_layout_editNome);
        edtDesc = (EditText) findViewById(R.id.new_prod_layout_editdesc);
        edtCodigo = (EditText) findViewById(R.id.new_prod_layout_editcodigo);
        edtCusto = (EditText) findViewById(R.id.new_prod_layout_editcusto);
        edtPreco = (EditText) findViewById(R.id.new_prod_layout_editpreco);
        edtQuantidade = (EditText) findViewById(R.id.new_prod_layout_editqntd);
        edtMargem = (EditText) findViewById(R.id.new_prod_layout_editmargem);
        swAtivo = (Switch) findViewById(R.id.new_prod_ativosw);
        produtoDAO = new ProdutoDAO(this);
        Produto produto;

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ///MODO GAMBIARRA AppBar FUNCIONANDO
        ImageView nav_menu = findViewById(R.id.nav_menu);
        nav_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                drawer.openDrawer(navigationView);
            }

        });

        //Salvar Produto
        Button save = findViewById(R.id.new_prod_layout_savebt);
        save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                salvarCadProduto(v);
            }
        });

        //Carregar Produto ao clicar
        Intent intent = getIntent();
        String message = intent.getStringExtra(ProdActivity.EXTRA_MESSAGE);

        if (message.length() > 0){
            produtos = new ArrayList<Produto>();
            produtos = produtoDAO.listar(Integer.valueOf(message));

            for (Produto p : produtos){
                edtId.setText(String.valueOf(p.getId()));
                edtNome.setText(p.getNome());
                edtDesc.setText(p.getDesc());
                edtCodigo.setText(p.getCodigo());
                edtCusto.setText(String.valueOf(p.getCusto()));
                edtPreco.setText(String.valueOf(p.getPreco()));
                edtQuantidade.setText(String.valueOf(p.getQuantidade()));
                edtMargem.setText(String.valueOf(p.getMargem()));

                if(p.getAtivo() == 1)swAtivo.setChecked(true);
                else swAtivo.setChecked(false);
            }
        }

        //Leitor Código de Barras
        ImageView scanNewProd = (ImageView) findViewById(R.id.new_prod_layout_readbt);
        scanNewProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(newProdActivity.this);
                integrator.initiateScan();
            }
        });


        //Atualizar Preço / Margem
        edtPreco.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Double margem;
                if(!hasFocus){
                    if(!edtCusto.getText().equals("") && !edtPreco.getText().equals("")){
                        margem = Double.parseDouble(edtPreco.getText().toString()) - Double.parseDouble(edtCusto.getText().toString());
                        margem = margem / Double.parseDouble(edtCusto.getText().toString()) * 100.0;
                        edtMargem.setText(String.valueOf(margem));
                    }
                }
            }
        });
        edtMargem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Double preco;
                if(!hasFocus){
                    if(!edtCusto.getText().equals("") && !edtMargem.getText().equals("")){
                        preco = Double.parseDouble(edtCusto.getText().toString());
                        preco = preco + (preco * Double.parseDouble(edtMargem.getText().toString())/100.0);
                        edtPreco.setText(String.valueOf(preco));
                    }
                }
            }
        });
    }

    //Resultado do leitor de código de barras
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();

            // display it on screen
            edtCodigo.setText(scanContent);

        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void salvarCadProduto(View v){
        Produto produto = new Produto();

        if (edtNome.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Informe o nome", Toast.LENGTH_SHORT).show();
        }else{
            if (String.valueOf(edtId.getText().toString()).length() > 0){
                produto.setId(Long.valueOf(edtId.getText().toString()));
            }else{
                produto.setId(Long.valueOf("0"));
            }
            produto.setNome(edtNome.getText().toString());
            produto.setDesc(edtDesc.getText().toString());
            produto.setCodigo(edtCodigo.getText().toString());

            if(edtCusto.getText().toString() == "") produto.setCusto(0);
            else produto.setCusto(Double.parseDouble(edtCusto.getText().toString()));

            if(edtPreco.getText().toString() == "") produto.setPreco(0);
            else produto.setPreco(Double.parseDouble(edtPreco.getText().toString()));

            if(edtQuantidade.getText().toString() == "") produto.setQuantidade(0);
            else produto.setQuantidade(Double.parseDouble(edtQuantidade.getText().toString()));

            if(edtMargem.getText().toString() == "") produto.setMargem(0);
            else produto.setMargem(Double.parseDouble(edtMargem.getText().toString()));

            if(swAtivo.isChecked()) produto.setAtivo(1);
            else produto.setAtivo(0);

            produtoDAO.SalvarOuAlterar(produto);

            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        Intent it = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_itens) {
            it = new Intent(this, ProdActivity.class);
        } else if (id == R.id.nav_people) {
            it = new Intent(this, PessoaListActivity.class);
        } else if (id == R.id.nav_pedido) {
            it = new Intent(this, PedidoListActivity.class);
        } else if (id == R.id.nav_opt) {

        } else if (id == R.id.nav_share) {

        }

        startActivity(it);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
