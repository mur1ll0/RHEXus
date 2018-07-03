package rhexa.rhexus.pkgPedido;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import rhexa.rhexus.R;
import rhexa.rhexus.pkgMovimento.MovimentoListActivity;
import rhexa.rhexus.pkgPedido.Pedido;
import rhexa.rhexus.pkgPedido.PedidoDAO;
import rhexa.rhexus.pkgPedido.PedidoListAdapter;
import rhexa.rhexus.pkgPessoa.PessoaListActivity;
import rhexa.rhexus.pkgProduto.ProdActivity;
import rhexa.rhexus.pkgTitulo.TituloListActivity;

public class PedidoListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ListView pedidoList;
    private PedidoDAO pedidoDAO;
    private List<Pedido> pedidos;
    private PedidoListAdapter pedidoListAdapter;

    public final static String EXTRA_MESSAGE = "";
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_pedido);

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

        pedidoDAO = new PedidoDAO(this);

        ///Listar Produtos
        pedidoList = findViewById(R.id.pedido_list);
        final EditText search = (EditText) findViewById(R.id.pedido_layout_busca);

        pedidos = pedidoDAO.listar();
        pedidoListAdapter = new PedidoListAdapter(this, R.layout.pedidos_adapter_activity, pedidos);
        pedidoList.setAdapter(pedidoListAdapter);


        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                pedidoListAdapter.clear();
                pedidos = pedidoDAO.listar(search.getText().toString());
                pedidoListAdapter.addAll(pedidos);
                return false;
            }
        });

        //Abrir tela Cadastro novos produtos
        final Button newProd = findViewById(R.id.pedido_layout_btNovo);
        newProd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                /*Intent it = new Intent(ProdActivity.this, newProdActivity.class);
                startActivity(it);
                drawer.closeDrawer(GravityCompat.START);*/
                novoCadastroPedido(v);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        pedidoListAdapter.clear();
        pedidos = pedidoDAO.listar();

        pedidoListAdapter.addAll(pedidos);
        pedidoListAdapter.notifyDataSetChanged();
    }


    public void abrirCadastroPedido(View v){
        Intent it = new Intent(this, newPedidoFinalActivity.class);

        TextView id = (TextView) v.findViewById(R.id.pedidos_adapter_activity_tvId);
        String message = id.getText().toString();
        it.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(it, 1);
    }

    public void novoCadastroPedido(View v){
        Intent it = new Intent(this,newPedidoFinalActivity.class);
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
            it = new Intent(this, ProdActivity.class);
        } else if (id == R.id.nav_people) {
            it = new Intent(this, PessoaListActivity.class);
        } else if (id == R.id.nav_pedido) {
            it = getIntent();
            finish();
        } else if (id == R.id.nav_titulo) {
            it = new Intent(this, TituloListActivity.class);
        } else if (id == R.id.nav_movimentos) {
            it = new Intent( this, MovimentoListActivity.class);
        } else if (id == R.id.nav_opt) {
            it = getIntent();
            finish();
        } else if (id == R.id.nav_share) {
            it = getIntent();
            finish();
        }

        startActivity(it);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

