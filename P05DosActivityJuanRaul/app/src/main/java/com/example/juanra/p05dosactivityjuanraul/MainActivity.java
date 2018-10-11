package com.example.juanra.p05dosactivityjuanraul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void siguienteActivity(View view){

        //Para comunicar activitus necesitamos hacerlo a traves de la clase Intent(como intento)

        //Creamos el objeto Intent
        Intent siguiente=new Intent(this, activity_segundo.class);

        //Iniciamos el activity que queremos
        startActivity(siguiente);

        //No olvidar asociar evento onClick
    }
}
