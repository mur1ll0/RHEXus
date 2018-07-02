package rhexa.rhexus.pkgProduto;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import rhexa.rhexus.R;
import rhexa.rhexus.pkgPessoa.PessoaListActivity;

public class PedidoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
            it = new Intent(this, PessoaListActivity.class);
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
