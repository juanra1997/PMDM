package com.example.juegopreguntas4;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //Esta clase nos va a permitir movernos por las diferentes activitys de la aplicacion

    TextView grupo, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grupo=(TextView) findViewById(R.id.autor);
        pass=(TextView) findViewById(R.id.contra);
    }

    public void crearPreguntas(View v) {
        if(!grupo.getText().toString().isEmpty()&&!pass.getText().toString().isEmpty()) {
            Bundle dato=new Bundle();
            dato.putString("elgrupo", grupo.getText().toString());
            dato.putString("contra", pass.getText().toString());
            Intent i = new Intent(this, IntroducirActivity.class);
            i.putExtras(dato);
            startActivity(i);
        }else{
            Toast.makeText(this, "Introduce el nombre del grupo y la contraseña", Toast.LENGTH_SHORT).show();
        }
    }

    public void revisarPreguntas(View v) {
        if(!grupo.getText().toString().isEmpty()&&!pass.getText().toString().isEmpty()) {
            Bundle dato=new Bundle();
            dato.putString("elgrupo", grupo.getText().toString());
            dato.putString("contra", pass.getText().toString());
            Intent i = new Intent(this, VisualizarActivity.class);
            i.putExtras(dato);
            startActivity(i);
        }else{
            Toast.makeText(this, "Introduce el nombre del grupo y la contraseña", Toast.LENGTH_SHORT).show();
        }
    }

    public void crearJuego(View v) {
        if(!grupo.getText().toString().isEmpty()&&!pass.getText().toString().isEmpty()) {
            Bundle dato=new Bundle();
            dato.putString("elgrupo", grupo.getText().toString());
            dato.putString("contra", pass.getText().toString());
            Intent i = new Intent(this, JuegoActivity.class);
            i.putExtras(dato);
            startActivity(i);
        }else{
            Toast.makeText(this, "Introduce el nombre del grupo y la contraseña", Toast.LENGTH_SHORT).show();
        }
    }

    public void twitter(View v) {
        Intent i = new Intent(this, WebActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.informacion){
            new AlertDialog.Builder(this)
                    .setTitle("Info")
                    .setMessage(R.string.explicacion)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setCancelable(false)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
}
