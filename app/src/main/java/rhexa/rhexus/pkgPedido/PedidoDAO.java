package rhexa.rhexus.pkgPedido;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import rhexa.rhexus.BancoDados;
import rhexa.rhexus.pkgPedido.Pedido;

public class PedidoDAO {
    SQLiteDatabase db;

    public PedidoDAO(Context context){db = BancoDados.getDB(context);}

    public void salvarOuAlterar(Pedido pedido){
        if (pedido.getId() > 0){
            alterar(pedido);
        }else {
            salvar(pedido);
        }
    }

    public void salvar(Pedido pedido){
        ContentValues values = new ContentValues();
        values.put(Pedido.SITUACAO,pedido.getSituacao());
        values.put(Pedido.OPERACAO,pedido.getOperacao());
        values.put(Pedido.PESSOAID,pedido.getPessoaID());
        values.put(Pedido.EMISSAO,pedido.getEmissao());
        db.insert(Pedido.TABELA,null,values);
    }

    public void alterar(Pedido pedido){
        ContentValues values = new ContentValues();
        String[] where = new String[]{String.valueOf(pedido.getId())};
        values.put(Pedido.SITUACAO,pedido.getSituacao());
        values.put(Pedido.OPERACAO,pedido.getOperacao());
        values.put(Pedido.PESSOAID,pedido.getPessoaID());
        values.put(Pedido.EMISSAO,pedido.getEmissao());
        db.update(Pedido.TABELA,values,Pedido.ID + " = ?",where);
    }

    public List<Pedido> listar(){
        List<Pedido> pedidos = new ArrayList<>();
        Cursor c = db.query(Pedido.TABELA,Pedido.COLUNAS,null,null,null,null,null);
        if (c.moveToFirst()){
            do {
                Pedido pedido = new Pedido();
                pedido.setId(c.getLong(c.getColumnIndex(Pedido.ID)));
                pedido.setSituacao(c.getString(c.getColumnIndex(Pedido.SITUACAO)));
                pedido.setOperacao(c.getString(c.getColumnIndex(Pedido.OPERACAO)));
                pedido.setPessoaID(c.getLong(c.getColumnIndex(Pedido.PESSOAID)));
                pedido.setEmissao(c.getString(c.getColumnIndex(Pedido.EMISSAO)));
                pedidos.add(pedido);
            } while (c.moveToNext());
        }
        return pedidos;
    }

    public List<Pedido> listar(int id){
        List<Pedido> pedidos = new ArrayList<>();
        Cursor c = db.query(Pedido.TABELA,Pedido.COLUNAS,null,null,null,null,null);
        if (c.moveToFirst()){
            do {
                Pedido pedido = new Pedido();
                pedido.setId(c.getLong(c.getColumnIndex(Pedido.ID)));
                pedido.setSituacao(c.getString(c.getColumnIndex(Pedido.SITUACAO)));
                pedido.setOperacao(c.getString(c.getColumnIndex(Pedido.OPERACAO)));
                pedido.setPessoaID(c.getLong(c.getColumnIndex(Pedido.PESSOAID)));
                pedido.setEmissao(c.getString(c.getColumnIndex(Pedido.EMISSAO)));
                if (id == pedido.getId())
                    pedidos.add(pedido);
            } while (c.moveToNext());
        }
        return pedidos;
    }

    public List<Pedido> listar(String arg){
        List<Pedido> pedidos = new ArrayList<>();
        Cursor c = db.query(Pedido.TABELA,Pedido.COLUNAS,null,null,null,null,null);
        if (c.moveToFirst()){
            do {
                Pedido pedido = new Pedido();
                pedido.setId(c.getLong(c.getColumnIndex(Pedido.ID)));
                pedido.setSituacao(c.getString(c.getColumnIndex(Pedido.SITUACAO)));
                pedido.setOperacao(c.getString(c.getColumnIndex(Pedido.OPERACAO)));
                pedido.setPessoaID(c.getLong(c.getColumnIndex(Pedido.PESSOAID)));
                pedido.setEmissao(c.getString(c.getColumnIndex(Pedido.EMISSAO)));
                if (pedido.toString().contains(arg))
                    pedidos.add(pedido);
            } while (c.moveToNext());
        }
        return pedidos;
    }

}
