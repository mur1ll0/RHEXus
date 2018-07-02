package rhexa.rhexus.pkgTitulo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import rhexa.rhexus.BancoDados;
import rhexa.rhexus.pkgTitulo.Titulo;

import java.util.ArrayList;
import java.util.List;

public class TituloDAO {
    SQLiteDatabase db;

    public TituloDAO(Context context){db = BancoDados.getDB(context);}

    public void salvarOuAlterar(Titulo titulo){
        if (titulo.getId() > 0){
            alterar(titulo);
        }else {
            salvar(titulo);
        }
    }

    public void salvar(Titulo titulo){
        ContentValues values = new ContentValues();
        values.put(Titulo.EMISSAO,titulo.getEmissao());
        values.put(Titulo.PESSOAID,titulo.getPessoaID());
        values.put(Titulo.TIPO,titulo.getTipo());
        values.put(Titulo.VALOR,titulo.getValor());
        values.put(Titulo.VALORBAIXA,titulo.getValorBaixa());
        values.put(Titulo.VENCIMENTO,titulo.getVencimento());
        db.insert(Titulo.TABELA,null,values);
    }

    public void alterar(Titulo titulo){
        ContentValues values = new ContentValues();
        String[] where = new String[]{String.valueOf(titulo.getId())};
        values.put(Titulo.EMISSAO,titulo.getEmissao());
        values.put(Titulo.PESSOAID,titulo.getPessoaID());
        values.put(Titulo.TIPO,titulo.getTipo());
        values.put(Titulo.VALOR,titulo.getValor());
        values.put(Titulo.VALORBAIXA,titulo.getValorBaixa());
        values.put(Titulo.VENCIMENTO,titulo.getVencimento());
        db.update(Titulo.TABELA,values,Titulo.ID + " = ?",where);
    }

    public List<Titulo> listar(){
        List<Titulo> titulos = new ArrayList<>();
        Cursor c = db.query(Titulo.TABELA,Titulo.COLUNAS,null,null,null,null,null);
        if (c.moveToFirst()){
            do {
                Titulo titulo = new Titulo();
                titulo.setId(c.getLong(c.getColumnIndex(Titulo.ID)));
                titulo.setEmissao(c.getString(c.getColumnIndex(Titulo.EMISSAO)));
                titulo.setPessoaID(c.getLong(c.getColumnIndex(Titulo.PESSOAID)));
                titulo.setTipo(c.getString(c.getColumnIndex(Titulo.TIPO)));
                titulo.setValor(c.getFloat(c.getColumnIndex(Titulo.VALOR)));
                titulo.setValorBaixa(c.getFloat(c.getColumnIndex(Titulo.VALORBAIXA)));
                titulo.setVencimento(c.getString(c.getColumnIndex(Titulo.VENCIMENTO)));
                titulos.add(titulo);
            } while (c.moveToFirst());
        }
        return titulos;
    }

    public List<Titulo> listar(int id){
        List<Titulo> titulos = new ArrayList<>();
        Cursor c = db.query(Titulo.TABELA,Titulo.COLUNAS,null,null,null,null,null);
        if (c.moveToFirst()){
            do {
                Titulo titulo = new Titulo();
                titulo.setId(c.getLong(c.getColumnIndex(Titulo.ID)));
                titulo.setEmissao(c.getString(c.getColumnIndex(Titulo.EMISSAO)));
                titulo.setPessoaID(c.getLong(c.getColumnIndex(Titulo.PESSOAID)));
                titulo.setTipo(c.getString(c.getColumnIndex(Titulo.TIPO)));
                titulo.setValor(c.getFloat(c.getColumnIndex(Titulo.VALOR)));
                titulo.setValorBaixa(c.getFloat(c.getColumnIndex(Titulo.VALORBAIXA)));
                titulo.setVencimento(c.getString(c.getColumnIndex(Titulo.VENCIMENTO)));
                if (id == titulo.getId())
                    titulos.add(titulo);
            } while (c.moveToFirst());
        }
        return titulos;
    }

}
