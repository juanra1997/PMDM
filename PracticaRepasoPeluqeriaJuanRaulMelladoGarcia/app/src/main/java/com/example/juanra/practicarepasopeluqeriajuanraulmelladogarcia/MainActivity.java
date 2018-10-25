package com.example.juanra.practicarepasopeluqeriajuanraulmelladogarcia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, contra;
    private Button acceso, bcontra;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario=(EditText) findViewById(R.id.etUsuario);
        contra=(EditText) findViewById(R.id.etContra);
        acceso=(Button) findViewById(R.id.bAcceder);
        bcontra=(Button) findViewById(R.id.bRegistrar);

        preferences=getSharedPreferences("datos", Context.MODE_PRIVATE);
    }

    public void registrar(View view){

         //SharedPreferences preferences = getSharedPreferences(usuario.getText().toString(), Context.MODE_PRIVATE);
         //Como vamos a editar las SharedPreferences creamos un objeto editor
         SharedPreferences.Editor ObjetoEditor = preferences.edit();
         ObjetoEditor.putString("usuario", usuario.getText().toString());
         ObjetoEditor.putString("contra", contra.getText().toString());
         //Confirmar que hemos guardado los cambios
         ObjetoEditor.commit();

    }
    public void siguienteActivity(View view){

        String miContra, miUser;



        miContra=preferences.getString("contra", "");
        miUser=preferences.getString("usuario", "");

        //Toast.makeText(this, miUser, Toast.LENGTH_LONG).show();

        if(usuario.getText().toString().equals(miUser)&&contra.getText().toString().equals(miContra)) {

            Intent i = new Intent(this, Main2Activity.class);
            i.putExtra("usuario", usuario.getText().toString());
            startActivity(i);
        }else{
            Toast.makeText(this, "Usuario o contraseña incorrecto", Toast.LENGTH_LONG).show();
        }
    }
}
