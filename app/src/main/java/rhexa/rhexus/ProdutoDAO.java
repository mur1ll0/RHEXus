package rhexa.rhexus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProdutoDAO {

    SQLiteDatabase db;

    //Contrutor do banco
    public ProdutoDAO(Context context){
        this.db = BancoDados.getDB(context);
    }
}
