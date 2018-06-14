package rhexa.rhexus;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import static rhexa.rhexus.BancoDados.getDB;

public class PessoaDAO {
    SQLiteDatabase db;

    public PessoaDAO(Context context){
        db = BancoDados.getDB(context);
    }

    public void salvarOuAlterar(Pessoa pessoa){
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


}
