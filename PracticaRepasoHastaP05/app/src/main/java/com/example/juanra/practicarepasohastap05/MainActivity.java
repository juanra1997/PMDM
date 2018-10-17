package com.example.juanra.practicarepasohastap05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvcontra, tvusuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvusuario=(TextView) findViewById(R.id.tfUsuario);
        tvcontra=(TextView) findViewById(R.id.tfContrasenna);
    }

    public void comprobar(View view){

        /*String pass="maria2018", us="Maria";

        if(tvusuario.getText().toString().equals("Maria")) {
            Toast.makeText(this, tvusuario.getText(), Toast.LENGTH_LONG).show();
        }*/

        //Toast.makeText(this, tvcontra.getText(), Toast.LENGTH_LONG).show();
        if(tvusuario.getText().toString().equals("Maria")&&tvcontra.getText().toString().equals("maria2018")){
            Toast.makeText(this, "Contraseña correcta", Toast.LENGTH_LONG).show();
            Intent siguiente=new Intent(this, SecondActivity.class);
            startActivity(siguiente);
        }else{
            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_LONG).show();
        }
    }
}
