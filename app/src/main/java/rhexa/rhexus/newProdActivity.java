package rhexa.rhexus;

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
import android.widget.Toast;

import java.util.List;

public class newProdActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EditText edtId, edtNome, edtDesc, edtCodigo, edtCusto, edtPreco, edtQuantidade, edtMargem;
    ProdutoDAO produtoDAO;
    private List<Produto> produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_newprod);
        edtNome = (EditText) findViewById(R.id.new_prod_layout_editNome);
        edtDesc = (EditText) findViewById(R.id.new_prod_layout_editdesc);
        edtCodigo = (EditText) findViewById(R.id.new_prod_layout_editcodigo);
        edtCusto = (EditText) findViewById(R.id.new_prod_layout_editcusto);
        edtPreco = (EditText) findViewById(R.id.new_prod_layout_editpreco);
        edtQuantidade = (EditText) findViewById(R.id.new_prod_layout_editqntd);
        edtMargem = (EditText) findViewById(R.id.new_prod_layout_editmargem);
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

    }

    public void salvarCadProduto(View v){
        Produto produto = new Produto();

        if (edtNome.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Informe o nome", Toast.LENGTH_SHORT).show();
        }else{
            produto.setNome(edtNome.getText().toString());
            produto.setDesc(edtDesc.getText().toString());
            produto.setCodigo(edtCodigo.getText().toString());
            produto.setCusto(Double.parseDouble(edtCusto.getText().toString()));
            produto.setPreco(Double.parseDouble(edtPreco.getText().toString()));
            produto.setQuantidade(Double.parseDouble(edtQuantidade.getText().toString()));
            produto.setMargem(Double.parseDouble(edtMargem.getText().toString()));

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
            it = new Intent(this, PedidoActivity.class);
        } else if (id == R.id.nav_opt) {

        } else if (id == R.id.nav_share) {

        }

        startActivity(it);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
