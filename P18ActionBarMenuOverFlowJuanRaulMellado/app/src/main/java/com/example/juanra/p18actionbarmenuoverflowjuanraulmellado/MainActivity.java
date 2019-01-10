package com.example.juanra.p18actionbarmenuoverflowjuanraulmellado;

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

    //Crear un método para mostrar y ocultar el menu overflow
    //Tiene un nombre especifico y tiene que devolver verdadero
    /*
    En el metodo onCreateOptionsMenu creamos un objeto de la clase MenuInflater
    mediante el método inflate vinculamos el identificador del archivo de recursos
    R.menu.overflow y el objeto de la clase menu que llega como parámetro
     */

    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }
    /*
    Método para asignar la funcionalidad a cada una de las opciones
     */

    public boolean onOptionsItemSelected(MenuItem item){

        //Capturar el identificador del item que ha sido pulsado

        int id=item.getItemId();
        if(id==R.id.miOpcion1){
            Toast.makeText(this, "Eh?", Toast.LENGTH_SHORT).show();
        }
        if(id==R.id.miOpcion2){
            Toast.makeText(this, "Que ha pachao?", Toast.LENGTH_SHORT).show();
        }
        if(id==R.id.miOpcion3){
            Toast.makeText(this, "Talué", Toast.LENGTH_SHORT).show();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
