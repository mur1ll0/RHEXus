package rhexa.rhexus;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
                drawer.openDrawer(navigationView);
            }

        });

        //MENU RELATÓRIO RÁPIDO
        final TextView ano = (TextView) findViewById(R.id.ano);
        final TextView mes = (TextView) findViewById(R.id.mes);
        final TextView semana = (TextView) findViewById(R.id.semana);

        ano.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ano.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                mes.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                semana.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        mes.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ano.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mes.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                semana.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        semana.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ano.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mes.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                semana.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        });

        //PIECHART
        final PieChart graph = findViewById(R.id.graph);
        graph.setTransparentCircleRadius(0);
        graph.setHoleRadius(0);
        graph.setDescription(null);

        ArrayList<PieEntry> yData = new ArrayList<PieEntry>();
        yData.add(new PieEntry(new Float(99133.50), 0));
        yData.add(new PieEntry(new Float(9523.40), 1));

        final PieDataSet data = new PieDataSet(yData, "");
        data.setSliceSpace(0);
        //data.setSelectionShift(2);
        data.setValueTextSize(12);

        ArrayList<Integer> graphColors = new ArrayList<Integer>();
        graphColors.add(Color.GREEN);
        graphColors.add(Color.BLUE);
        data.setColors(graphColors);

        PieData dados = new PieData(data);
        graph.setData(dados);
        graph.highlightValues(null);
        graph.invalidate();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_itens) {
            // Handle the camera action
        } else if (id == R.id.nav_people) {

        } else if (id == R.id.nav_pedido) {

        } else if (id == R.id.nav_opt) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
