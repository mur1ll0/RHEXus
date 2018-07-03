package rhexa.rhexus.pkgPedido;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import rhexa.rhexus.R;
import rhexa.rhexus.pkgPedido.Pedido;
import rhexa.rhexus.pkgPedido.PedidoDAO;
import rhexa.rhexus.pkgPedido.PedidoListAdapter;

public class PedidoListActivity extends ListActivity {
    private PedidoDAO pedidoDAO;
    private List<Pedido> pedidos;
    private PedidoListAdapter pedidoListAdapter;

    public final static String EXTRA_MESSAGE = "";
    private EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedido_layout);

        pedidoDAO = new PedidoDAO(this);

        pedidos = pedidoDAO.listar();
        pedidoListAdapter = new PedidoListAdapter(this, R.layout.pedidos_adapter_activity, pedidos);
        setListAdapter(pedidoListAdapter);

        search = (EditText) findViewById(R.id.pedido_layout_busca);

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

        String scanContent = "";
        search = (EditText) findViewById(R.id.pedido_layout_busca);


        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (scanningResult != null) {
            //we have a result
            scanContent = scanningResult.getContents();

            // display it on screen
            search.setText(scanContent);
        }
        pedidos = pedidoDAO.listar(scanContent);

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
}

