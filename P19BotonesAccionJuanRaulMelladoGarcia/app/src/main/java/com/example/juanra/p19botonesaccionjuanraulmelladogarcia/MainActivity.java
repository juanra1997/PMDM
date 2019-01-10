package com.example.juanra.p19botonesaccionjuanraulmelladogarcia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Mostrar y ocultar el menu contextual

    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menuacciones, menu);

        return true;
    }

    //Agregar acciones a nuestros botones

    public boolean onOptionsItemSelected(MenuItem item){

        int id=item.getItemId();

        switch (id){

            case R.id.compartir:

                Toast.makeText(this, "Compartir", Toast.LENGTH_SHORT).show();
                return true;
                //break;

            case R.id.buscar:

                Toast.makeText(this, "Buscar", Toast.LENGTH_SHORT).show();
                return true;
                //break;

            case R.id.miOpcion1:

                Toast.makeText(this, "Opcion 1", Toast.LENGTH_SHORT).show();
                return true;
                //break;

            case R.id.miOpcionsalir:

                finish();
                return true;
                //break;
        }

        return super.onOptionsItemSelected(item);
    }
}
