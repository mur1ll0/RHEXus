package rhexa.rhexus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import static rhexa.rhexus.PessoaListActivity.EXTRA_MESSAGE;

public class PedidoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ListView prodList;
    private ProdutosAdapter produtosAdapter;
    private ProdutoDAO produtoDAO;
    private List<Produto> produtos;

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
