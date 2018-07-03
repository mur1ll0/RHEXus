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

import rhexa.rhexus.R;
import rhexa.rhexus.pkgPessoa.Pessoa;
import rhexa.rhexus.pkgPessoa.PessoaDAO;
import rhexa.rhexus.pkgPessoa.PessoaListActivity;
import rhexa.rhexus.pkgProduto.newProdActivity;

public class newPedidoFinalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EditText numeroPed,emissao,editCodPessoa,alorProd,edtEndereco,edtTelefone;
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
        setContentView(R.layout.activity_pessoa_cad);
        edtId = (EditText) findViewById(R.id.activity_pessoa_cad_edtId);
        edtNome = (EditText) findViewById(R.id.activity_pessoa_cad_edtNome);
        edtCPFCNPJ = (EditText) findViewById(R.id.activity_pessoa_cad_edtCPFCNPJ);
        edtEndereco = (EditText) findViewById(R.id.activity_pessoa_cad_edtEndereco);
        spTipo = (Spinner) findViewById(R.id.activity_pessoa_cad_spTipo);
        edtTelefone = (EditText) findViewById(R.id.activity_pessoa_cad_edtTelefone);
        pessoaDAO = new PessoaDAO(this);
        Intent intent = getIntent();
        Pessoa pessoa;
        String message = intent.getStringExtra(PessoaListActivity.EXTRA_MESSAGE);


        ArrayAdapter<String> aptPessoaTipo = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, tiposDescricao);
        spTipo.setAdapter(aptPessoaTipo);

        if (message.length() > 0){
            pessoas = new ArrayList<Pessoa>();
            pessoas = pessoaDAO.listar(Integer.valueOf(message));

            for (Pessoa p : pessoas){
                edtId.setText(String.valueOf(p.getId()));
                edtNome.setText(p.getNome());
                edtTelefone.setText(p.getTelefone());
                spTipo.setSelection(getIndex(spTipo,p.getTipo()));
                edtEndereco.setText(p.getEndereco());
                edtCPFCNPJ.setText(p.getCpfcnpj());
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
