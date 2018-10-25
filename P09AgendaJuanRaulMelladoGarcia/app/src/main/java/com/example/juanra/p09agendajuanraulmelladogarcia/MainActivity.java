package com.example.juanra.p09agendajuanraulmelladogarcia;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etNombrem, etDatosm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombrem=(EditText) findViewById(R.id.etNombre);
        etDatosm=(EditText) findViewById(R.id.etDatos);
    }

    public void Guardar(View view){

        //Guarda en preferences "Agenda" el nombre y los datos asociados a ese nombre
        SharedPreferences preferencias=getSharedPreferences("Agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor objEditor=preferencias.edit();
        objEditor.putString(etNombrem.getText().toString(), etDatosm.getText().toString());
        objEditor.commit();
        Toast.makeText(this, "El contacto ha sido guardado", Toast.LENGTH_LONG).show();
        Limpiar(view);
    }

    //Busca una clave que será el nombre y si la encuentra la pone en el edittext datos
    public void Buscar(View view){

        SharedPreferences preferencias=getSharedPreferences("Agenda", Context.MODE_PRIVATE);
        String Datos=preferencias.getString(etNombrem.getText().toString(), "");
        //Si no encuentra la clave va a devolver cadena vacia en datos.
        if(Datos.length()==0){
            Toast.makeText(this, "No se ha encontrado el registro", Toast.LENGTH_LONG).show();
            Limpiar(view);
        }else {
            etDatosm.setText(Datos);
        }
    }

    public void Limpiar(View view){
        etDatosm.setText("");
        etNombrem.setText("");
    }
}
