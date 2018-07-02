package rhexa.rhexus.pkgPessoa;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import android.database.Cursor;
import rhexa.rhexus.BancoDados;

public class PessoaDAO {
    SQLiteDatabase db;

    public PessoaDAO(Context context){
        db = BancoDados.getDB(context);
    }

    public void SalvarOuAlterar(Pessoa pessoa){
        if (pessoa.getId() > 0){
            alterar(pessoa);
        }else{
            salvar(pessoa); // inthérnuu
        }
    }

    public void salvar(Pessoa pessoa){
        ContentValues values = new ContentValues();
        values.put(Pessoa.NOME, pessoa.getNome());
        values.put(Pessoa.TELEFONE, pessoa.getTelefone());
        values.put(Pessoa.CPFCNPJ, pessoa.getCpfcnpj());
        values.put(Pessoa.ENDERECO, pessoa.getEndereco());
        values.put(Pessoa.TIPO, pessoa.getTipo());
        db.insert(Pessoa.TABELA, null, values);
    }

    public void alterar(Pessoa pessoa){
        ContentValues values = new ContentValues();
        String[] where = new String[] {String.valueOf(pessoa.getId())};
        values.put(Pessoa.NOME, pessoa.getNome());
        values.put(Pessoa.TIPO, pessoa.getTipo());
        values.put(Pessoa.ENDERECO, pessoa.getEndereco());
        values.put(Pessoa.CPFCNPJ, pessoa.getCpfcnpj());
        values.put(Pessoa.TELEFONE, pessoa.getTelefone());

        db.update(Pessoa.TABELA, values, Pessoa.ID + " = ?", where);
    }

    public void excluir(String id){
        String [] where = new String[] {id};
        db.delete(Pessoa.TABELA, Pessoa.ID + " = ?", where);
    }

    public List<Pessoa> listar(){
        List<Pessoa> pessoas = new java.util.ArrayList<Pessoa>();
        Cursor c = db.query(Pessoa.TABELA, Pessoa.COLUNAS, null, null,null,null,null);
        if(c.moveToFirst()){
            do {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(c.getLong(c.getColumnIndex(Pessoa.ID)));
                pessoa.setNome(c.getString(c.getColumnIndex(Pessoa.NOME)));
                pessoa.setTipo(c.getString(c.getColumnIndex(Pessoa.TIPO)));
                pessoa.setCpfcnpj(c.getString(c.getColumnIndex(Pessoa.CPFCNPJ)));
                pessoa.setEndereco(c.getString(c.getColumnIndex(Pessoa.ENDERECO)));
                pessoa.setTelefone(c.getString(c.getColumnIndex(Pessoa.TELEFONE)));
                pessoas.add(pessoa);
            } while (c.moveToNext());

        }
        return pessoas;
    }

    public List<Pessoa> listar(int id){
        List<Pessoa> pessoas = new java.util.ArrayList<Pessoa>();
        Cursor c = db.query(Pessoa.TABELA, Pessoa.COLUNAS, null, null,null,null,null);
        if(c.moveToFirst()){
            do {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(c.getLong(c.getColumnIndex(Pessoa.ID)));
                pessoa.setNome(c.getString(c.getColumnIndex(Pessoa.NOME)));
                pessoa.setTipo(c.getString(c.getColumnIndex(Pessoa.TIPO)));
                pessoa.setCpfcnpj(c.getString(c.getColumnIndex(Pessoa.CPFCNPJ)));
                pessoa.setEndereco(c.getString(c.getColumnIndex(Pessoa.ENDERECO)));
                pessoa.setTelefone(c.getString(c.getColumnIndex(Pessoa.TELEFONE)));
                if (pessoa.getId() == id){
                    pessoas.add(pessoa);
                }
            } while (c.moveToNext());

        }
        return pessoas;
    }

}
