package rhexa.rhexus;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Pedido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedido_layout);

        ///MODO GAMBIARRA AppBar FUNCIONANDO
        ImageView nav_menu = findViewById(R.id.nav_menu);
        nav_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                //drawer.openDrawer(navigationView);
            }

        });

    }
}
