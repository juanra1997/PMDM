package com.example.juegopreguntas3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //Esta clase nos va a permitir movernos por las diferentes activitys de la aplicacion

    TextView grupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grupo=(TextView) findViewById(R.id.autor);
    }

    public void crearPreguntas(View v) {
        if(!grupo.getText().toString().isEmpty()) {
            Bundle dato=new Bundle();
            dato.putString("elgrupo", grupo.getText().toString());
            Intent i = new Intent(this, IntroducirActivity.class);
            i.putExtras(dato);
            startActivity(i);
        }else{
            Toast.makeText(this, "Introduce el nombre del grupo", Toast.LENGTH_SHORT).show();
        }
    }

    public void revisarPreguntas(View v) {
        if(!grupo.getText().toString().isEmpty()) {
            Bundle dato=new Bundle();
            dato.putString("elgrupo", grupo.getText().toString());
            Intent i = new Intent(this, VisualizarActivity.class);
            i.putExtras(dato);
            startActivity(i);
        }else{
            Toast.makeText(this, "Introduce el nombre del grupo", Toast.LENGTH_SHORT).show();
        }
    }

    public void crearJuego(View v) {
        if(!grupo.getText().toString().isEmpty()) {
            Bundle dato=new Bundle();
            dato.putString("elgrupo", grupo.getText().toString());
            Intent i = new Intent(this, JuegoActivity.class);
            i.putExtras(dato);
            startActivity(i);
        }else{
            Toast.makeText(this, "Introduce el nombre del grupo", Toast.LENGTH_SHORT).show();
        }
    }

    public void twitter(View v) {
        Intent i = new Intent(this, WebActivity.class);
        startActivity(i);
    }

}
