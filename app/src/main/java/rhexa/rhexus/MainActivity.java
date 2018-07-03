package rhexa.rhexus;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import rhexa.rhexus.pkgMovimento.MovimentoListActivity;
import rhexa.rhexus.pkgPedido.PedidoActivity;
import rhexa.rhexus.pkgPessoa.PessoaListActivity;
import rhexa.rhexus.pkgProduto.ProdActivity;
import rhexa.rhexus.pkgTitulo.TituloListActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public boolean onNavigationItemSelected(MenuItem item) {
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
        } else if (id == R.id.nav_titulo) {
            it = new Intent(this, TituloListActivity.class);
        } else if (id == R.id.nav_movimentos) {
            it = new Intent( this, MovimentoListActivity.class);
        } else if (id == R.id.nav_opt) {

        } else if (id == R.id.nav_share) {

        }

        startActivity(it);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
