package com.example.juegopreguntas3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    //Esta clase introduce el numero de respuestas correctas en un textview

    TextView resul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        resul=(TextView) findViewById(R.id.puntuacion);
        resul.setText(getIntent().getExtras().getString("resultado"));
    }
}
