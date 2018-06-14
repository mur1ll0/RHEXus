package rhexa.rhexus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BancoDados {
    // Nome do banco
    private static final String NOME_BANCO = "trabalho";

    // Controle de versao
    private static final int VERSAO_BANCO = 1; // Mudar a cada nova versão

    //Script para fazer drop na tabela
    private static final String[] SCRIPT_DATABASE_DELETE
            = new String[] {"DROP TABLE IF EXISTS pessoa;"};

    // Cria a tabela com o "_id" sequencial
    private static final String[] SCRIPT_DATABASE_CREATE = new String[] { // Criar campos conforme necessidade
            "create table pessoa (id integer primary key,nome varchar(60), tipo varchar(10), cpfcnpj varchar(20), endereco varchar(100), telefone varchar(20));"
    };

    private static SQLiteDatabase db;

    public static SQLiteDatabase getDB(Context ctx) {
        if (db == null) {
            SQLiteHelper dbHelper = new SQLiteHelper(ctx, NOME_BANCO, VERSAO_BANCO, SCRIPT_DATABASE_CREATE, SCRIPT_DATABASE_DELETE);
            db = dbHelper.getWritableDatabase();
        }
        return db;
    }
}
