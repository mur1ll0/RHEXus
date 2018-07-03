package rhexa.rhexus.pkgPedido;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rhexa.rhexus.R;
import rhexa.rhexus.pkgPedido.Pedido;
import rhexa.rhexus.pkgPedido.PedidoDAO;
import rhexa.rhexus.pkgPedido.PedidoListActivity;
import rhexa.rhexus.pkgProduto.newProdActivity;

public class newPedidoFinalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EditText edtnumeroPed,edtemissao,edtCodPessoa,edtPessoaNome,edtvalorProd,edtDescV,edtparcelas,edtvalorFinal;
    PedidoDAO pedidoDAO;
    private List<Pedido> pedidos;
    
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
            public void onClick(View v)
            {
                drawer.openDrawer(navigationView);
            }

        });

        final TextView produtos = (TextView) findViewById(R.id.pedido_produtos);
        final TextView finalizar = (TextView) findViewById(R.id.pedido_finalizar);

        produtos.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                produtos.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                finalizar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            }
        });
        finalizar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                produtos.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                finalizar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            }
        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_pedido_final_layout);
        edtnumeroPed = (EditText) findViewById(R.id.new_pedido_final_numeroPed);
        edtemissao = (EditText) findViewById(R.id.new_pedido_final_emissao);
        edtCodPessoa = (EditText) findViewById(R.id.new_pedido_final_editCodPessoa);
        edtPessoaNome = (EditText) findViewById(R.id.new_pedido_final_editPessoa);
        edtvalorProd = (EditText) findViewById(R.id.new_pedido_final_valorProd);
        edtparcelas = (EditText) findViewById(R.id.new_pedido_final_parcelas);
        edtvalorFinal = (EditText) findViewById(R.id.new_pedido_final_valorFinal); 
        pedidoDAO = new PedidoDAO(this);
        Intent intent = getIntent();
        Pedido pedido;
        String message = intent.getStringExtra(PedidoListActivity.EXTRA_MESSAGE);
     
        if (message.length() > 0){
            pedidos = new ArrayList<Pedido>();
            pedidos = pedidoDAO.listar(Integer.valueOf(message));

            for (Pedido p : pedidos){
                edtnumeroPed.setText(String.valueOf(p.getId()));
                edtemissao.setText(p.getEmissao());
                edtCodPessoa.setText(String.valueOf(p.getPessoaID()));
            }
        }
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        Intent it = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_itens) {
            it = new Intent(this, newProdActivity.class);
        } else if (id == R.id.nav_people) {
            it = new Intent(this, PedidoListActivity.class);
        } else if (id == R.id.nav_pedido) {
            it = getIntent();
            finish();
        } else if (id == R.id.nav_opt) {

        } else if (id == R.id.nav_share) {

        }

        startActivity(it);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
