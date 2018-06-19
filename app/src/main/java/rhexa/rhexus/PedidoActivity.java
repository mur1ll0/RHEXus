package rhexa.rhexus;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedido_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ///MODO GAMBIARRA AppBar FUNCIONANDO
        ImageView nav_menu = findViewById(R.id.nav_menu);
        nav_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                //drawer.openDrawer(navigationView);
            }

        });

        final TextView produtos = (TextView) findViewById(R.id.produtos);
        final TextView finalizar = (TextView) findViewById(R.id.finalizar);

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
}