package com.example.juanra.p08almacenamientodatospreferencesjuanraul;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etMailm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMailm=(EditText) findViewById(R.id.etMail);
        //Primero recuperamos de preferencias el valor del email
        //por eso creamos un objeto SharedPreferences
        SharedPreferences preferences=getSharedPreferences("dato", Context.MODE_PRIVATE);
        etMailm.setText(preferences.getString("mail", ""));
    }

    public void Guardar(View view){

        SharedPreferences preferences=getSharedPreferences("dato", Context.MODE_PRIVATE);
        //Como vamos a editar las SharedPreferences creamos un objeto editor
        SharedPreferences.Editor ObjetoEditor=preferences.edit();
        //Escribir en el campo email
        ObjetoEditor.putString("mail", etMailm.getText().toString());
        //Confirmar que hemos guardado los cambios
        ObjetoEditor.commit();
        //finalizamos para probar la aplicacion
        finish();
    }
}
