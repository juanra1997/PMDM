package com.example.juanra.p03imagebuttonnombre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BotonManoClick(View view){

        //Metodo que responde al evento onClick del boton mano
        Toast.makeText(this, "Pulsando el boton mano", Toast.LENGTH_SHORT).show();
    }

    public void BotonLibroClick(View view){

        //Metodo que responde al evento onClick del boton mano
        Toast.makeText(this, "Pulsando el boton libro", Toast.LENGTH_SHORT).show();
    }
}
