package com.example.juegopreguntas;

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

    public void crearPreguntas(View v) {
        Intent i = new Intent(this, IntroducirActivity.class);
        startActivity(i);
    }

    public void revisarPreguntas(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

}
