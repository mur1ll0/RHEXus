package rhexa.rhexus.pkgProduto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import rhexa.rhexus.BancoDados;

public class ProdutoDAO {

    SQLiteDatabase db;

    //Contrutor do banco
    public ProdutoDAO(Context context){
        this.db = BancoDados.getDB(context);
    }

    public void SalvarOuAlterar(Produto produto){
        if (produto.getId() > 0){
            alterar(produto);
        }else{
            salvar(produto);
        }
    }

    public void salvar(Produto produto){
        ContentValues values = new ContentValues();
        values.put(Produto.ID, produto.getId());
        values.put(Produto.NOME, produto.getNome());
        values.put(Produto.DESC, produto.getDesc());
        values.put(Produto.CODIGO, produto.getCodigo());
        values.put(Produto.CUSTO, produto.getCusto());
        values.put(Produto.PRECO, produto.getPreco());
        values.put(Produto.QUANTIDADE, produto.getQuantidade());
        values.put(Produto.MARGEM, produto.getQuantidade());
        //values.put(Produto.IMAGE, produto.getImage());

        db.insert(Produto.TABELA, null, values);
    }

    public void alterar(Produto produto){
        ContentValues values = new ContentValues();
        String[] where = new String[] {String.valueOf(produto.getId())};
        values.put(Produto.ID, produto.getId());
        values.put(Produto.NOME, produto.getNome());
        values.put(Produto.DESC, produto.getDesc());
        values.put(Produto.CODIGO, produto.getCodigo());
        values.put(Produto.CUSTO, produto.getCusto());
        values.put(Produto.PRECO, produto.getPreco());
        values.put(Produto.QUANTIDADE, produto.getQuantidade());
        values.put(Produto.MARGEM, produto.getQuantidade());
        //values.put(Produto.IMAGE, produto.getImage());

        db.update(Produto.TABELA, values, Produto.ID + " = ?", where);
    }

    public void excluir(String id){
        String [] where = new String[] {id};
        db.delete(Produto.TABELA, Produto.ID + " = ?", where);
    }

    public List<Produto> listar(){
        List<Produto> produtos = new java.util.ArrayList<Produto>();
        Cursor c = db.query(Produto.TABELA, Produto.COLUNAS, null, null,null,null,null);
        if(c.moveToFirst()){
            do {
                Produto produto = new Produto();
                produto.setId(c.getLong(c.getColumnIndex(Produto.ID)));
                produto.setNome(c.getString(c.getColumnIndex(Produto.NOME)));
                produto.setDesc(c.getString(c.getColumnIndex(Produto.DESC)));
                produto.setCodigo(c.getString(c.getColumnIndex(Produto.CODIGO)));
                produto.setCusto(c.getDouble(c.getColumnIndex(Produto.CUSTO)));
                produto.setPreco(c.getDouble(c.getColumnIndex(Produto.PRECO)));
                produto.setQuantidade(c.getDouble(c.getColumnIndex(Produto.QUANTIDADE)));
                produto.setMargem(c.getDouble(c.getColumnIndex(Produto.MARGEM)));
                //produto.setImage(c.getBlob(c.getColumnIndex(Produto.IMAGE)));
                produtos.add(produto);
            } while (c.moveToNext());

        }
        return produtos;
    }

    public List<Produto> listar(String arg){
        List<Produto> produtos = new java.util.ArrayList<Produto>();
        Cursor c = db.query(Produto.TABELA, Produto.COLUNAS, null, null,null,null,null);
        if(c.moveToFirst()){
            do {
                Produto produto = new Produto();
                produto.setId(c.getLong(c.getColumnIndex(Produto.ID)));
                produto.setNome(c.getString(c.getColumnIndex(Produto.NOME)));
                produto.setDesc(c.getString(c.getColumnIndex(Produto.DESC)));
                produto.setCodigo(c.getString(c.getColumnIndex(Produto.CODIGO)));
                produto.setCusto(c.getDouble(c.getColumnIndex(Produto.CUSTO)));
                produto.setPreco(c.getDouble(c.getColumnIndex(Produto.PRECO)));
                produto.setQuantidade(c.getDouble(c.getColumnIndex(Produto.QUANTIDADE)));
                produto.setMargem(c.getDouble(c.getColumnIndex(Produto.MARGEM)));
                //produto.setImage(c.getBlob(c.getColumnIndex(Produto.IMAGE)));

                if(produto.toString().indexOf(arg) != -1) {
                    produtos.add(produto);
                }
            } while (c.moveToNext());

        }
        return produtos;
    }

    public List<Produto> listar(int id){
        List<Produto> produtos = new java.util.ArrayList<Produto>();
        Cursor c = db.query(Produto.TABELA, Produto.COLUNAS, null, null,null,null,null);
        if(c.moveToFirst()){
            do {
                Produto produto = new Produto();
                produto.setId(c.getLong(c.getColumnIndex(Produto.ID)));
                produto.setNome(c.getString(c.getColumnIndex(Produto.NOME)));
                produto.setDesc(c.getString(c.getColumnIndex(Produto.DESC)));
                produto.setCodigo(c.getString(c.getColumnIndex(Produto.CODIGO)));
                produto.setCusto(c.getDouble(c.getColumnIndex(Produto.CUSTO)));
                produto.setPreco(c.getDouble(c.getColumnIndex(Produto.PRECO)));
                produto.setQuantidade(c.getDouble(c.getColumnIndex(Produto.QUANTIDADE)));
                produto.setMargem(c.getDouble(c.getColumnIndex(Produto.MARGEM)));
                //produto.setImage(c.getBlob(c.getColumnIndex(Produto.IMAGE)));

                if (produto.getId() == id){
                    produtos.add(produto);
                }
            } while (c.moveToNext());

        }
        return produtos;
    }
}
