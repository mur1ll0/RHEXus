package rhexa.rhexus.pkgMovimento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import rhexa.rhexus.BancoDados;

public class MovimentoDAO {
    SQLiteDatabase db;

    public MovimentoDAO(Context context){
        db = BancoDados.getDB(context);
    }

    public void SalvarOuAlterar(Movimento movimento){
        if (movimento.getId() > 0){
            alterar(movimento);
        }else{
            salvar(movimento); // inthérnuu
        }
    }

    public void salvar(Movimento movimento){
        ContentValues values = new ContentValues();
        values.put(Movimento.DATAMOVIMENTACAO, movimento.getDataMovimentacao());
        values.put(Movimento.PRODUTOID, movimento.getProdutoId());
        values.put(Movimento.QUANTIDADE, movimento.getQuantidade());
        values.put(Movimento.TIPO, movimento.getTipo());
        values.put(Movimento.VALOR,movimento.getValor());
        db.insert(Movimento.TABELA, null, values);
    }

    public void alterar(Movimento movimento){
        ContentValues values = new ContentValues();
        String[] where = new String[] {String.valueOf(movimento.getId())};
        values.put(Movimento.DATAMOVIMENTACAO, movimento.getDataMovimentacao());
        values.put(Movimento.PRODUTOID, movimento.getProdutoId());
        values.put(Movimento.QUANTIDADE, movimento.getQuantidade());
        values.put(Movimento.TIPO, movimento.getTipo());
        values.put(Movimento.VALOR,movimento.getValor());

        db.update(Movimento.TABELA, values, Movimento.ID + " = ?", where);
    }

    public void excluir(String id){
        String [] where = new String[] {id};
        db.delete(Movimento.TABELA, Movimento.ID + " = ?", where);
    }

    public List<Movimento> listar(){
        List<Movimento> movimentos = new java.util.ArrayList<Movimento>();
        Cursor c = db.query(Movimento.TABELA, Movimento.COLUNAS, null, null,null,null,null);
        if(c.moveToFirst()){
            do {
                Movimento movimento = new Movimento();
                movimento.setId(c.getLong(c.getColumnIndex(Movimento.ID)));
                movimento.setDataMovimentacao(c.getString(c.getColumnIndex(Movimento.DATAMOVIMENTACAO)));
                movimento.setTipo(c.getString(c.getColumnIndex(Movimento.TIPO)));
                movimento.setProdutoId(c.getLong(c.getColumnIndex(Movimento.PRODUTOID)));
                movimento.setQuantidade(c.getFloat(c.getColumnIndex(Movimento.QUANTIDADE)));
                movimento.setValor(c.getFloat(c.getColumnIndex(Movimento.VALOR)));
                movimentos.add(movimento);
            } while (c.moveToNext());

        }
        return movimentos;
    }

    public List<Movimento> listar(int id){
        List<Movimento> movimentos = new java.util.ArrayList<Movimento>();
        Cursor c = db.query(Movimento.TABELA, Movimento.COLUNAS, null, null,null,null,null);
        if(c.moveToFirst()){
            do {
                Movimento movimento = new Movimento();
                movimento.setId(c.getLong(c.getColumnIndex(Movimento.ID)));
                movimento.setDataMovimentacao(c.getString(c.getColumnIndex(Movimento.DATAMOVIMENTACAO)));
                movimento.setTipo(c.getString(c.getColumnIndex(Movimento.TIPO)));
                movimento.setProdutoId(c.getLong(c.getColumnIndex(Movimento.PRODUTOID)));
                movimento.setQuantidade(c.getFloat(c.getColumnIndex(Movimento.QUANTIDADE)));
                movimento.setValor(c.getFloat(c.getColumnIndex(Movimento.VALOR)));
                if (id == movimento.getId())
                    movimentos.add(movimento);
            } while (c.moveToNext());

        }
        return movimentos;
    }

}
