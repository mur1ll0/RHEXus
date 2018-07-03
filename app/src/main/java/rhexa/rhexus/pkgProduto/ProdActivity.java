package rhexa.rhexus.pkgProduto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import rhexa.rhexus.R;
import rhexa.rhexus.newPedidoActivity;
import rhexa.rhexus.pkgPessoa.PessoaListActivity;

public class ProdActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ListView prodList;
    private ProdutosAdapter produtosAdapter;
    private ProdutoDAO produtoDAO;
    private List<Produto> produtos;
    public final static String EXTRA_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_prod);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ///MODO GAMBIARRA AppBar FUNCIONANDO
        ImageView nav_menu = findViewById(R.id.nav_menu);
        nav_menu.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            drawer.openDrawer(navigationView);
        }

        });

        //Abrir tela Cadastro novos produtos
        final Button newProd = findViewById(R.id.prod_layout_novobt);
        newProd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                /*Intent it = new Intent(ProdActivity.this, newProdActivity.class);
                startActivity(it);
                drawer.closeDrawer(GravityCompat.START);*/
                novoCadastroProduto(v);
            }
        });

        ///Listar Produtos
        prodList = findViewById(R.id.prodList);
        produtoDAO = new ProdutoDAO(this);
        final EditText search = (EditText) findViewById(R.id.prod_layout_busca);

        produtos = produtoDAO.listar();
        produtosAdapter = new ProdutosAdapter(this, R.layout.produtos_adapter_activity, produtos);
        prodList.setAdapter(produtosAdapter);

        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                produtosAdapter.clear();
                produtos = produtoDAO.listar(search.getText().toString());
                produtosAdapter = new ProdutosAdapter(ProdActivity.this, R.layout.produtos_adapter_activity, produtos);
                prodList.setAdapter(produtosAdapter);
                return false;
            }
        });
    }

    //Leitor código de barras



    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        produtosAdapter.clear();
        produtos = produtoDAO.listar();

        produtosAdapter.addAll(produtos);
        produtosAdapter.notifyDataSetChanged();
    }

    public void abrirCadastroProduto(View v){
        Intent it = new Intent(this, newProdActivity.class);

        TextView id = (TextView) v.findViewById(R.id.produto_adapter_id);
        String message = id.getText().toString();
        it.putExtra(EXTRA_MESSAGE, message);

        startActivityForResult(it, 1);
    }

    public void novoCadastroProduto(View v){
        Intent it = new Intent(this,newProdActivity.class);
        String message = "";
        it.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(it,1);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        Intent it = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_itens) {
            it = getIntent();
            finish();
        } else if (id == R.id.nav_people) {
            it = new Intent(this, PessoaListActivity.class);
        } else if (id == R.id.nav_pedido) {
            it = new Intent(this, newPedidoActivity.class);
        } else if (id == R.id.nav_opt) {

        } else if (id == R.id.nav_share) {

        }

        startActivity(it);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
