package com.example.juanra.p06pasoinfdosactivitysjuanraulmelladogarcia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity {

    private EditText etNombrem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombrem=(EditText) findViewById(R.id.etNombre);
    }

    //método para enviar informacion al segundo activity
    public void Enviar(View view){

        Intent i=new Intent(this, SegundoActivity.class);
        i.putExtra("Nombre", etNombrem.getText().toString());
        startActivity(i);
    }
}
