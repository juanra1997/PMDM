package com.example.juanra.p05dosactivityjuanraul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class activity_segundo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);
    }

    public void anteriorActivity(View view){

        //Para comunicar activitus necesitamos hacerlo a traves de la clase Intent(como intento)

        //Creamos el objeto Intent
        Intent anterior=new Intent(this, MainActivity.class);

        //Iniciamos el activity que queremos
        startActivity(anterior);

        //No olvidar asociar evento onClick
    }
}
