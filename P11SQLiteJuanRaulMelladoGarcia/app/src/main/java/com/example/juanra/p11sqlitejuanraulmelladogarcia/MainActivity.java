package com.example.juanra.p11sqlitejuanraulmelladogarcia;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etCodm, etDescipm, etPreciom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCodm=(EditText) findViewById(R.id.etNombre);
        etDescipm=(EditText) findViewById(R.id.etDescripcion);
        etPreciom=(EditText) findViewById(R.id.etPrecio);
    }

    public void RegistrarClick(View view){

        //Crear la clase para poder administrar DQLite heredando de SQLiteOpenHelper
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //Abrir la base de datos
        //La base de datos debe ser de tipo SQLiteDatabase
        SQLiteDatabase baseDeDatos=admin.getWritableDatabase();

        //Almacenamos los datos introducidos por pantalla

        String codigo, descripcion, precio;
        codigo=etCodm.getText().toString();
        descripcion=etDescipm.getText().toString();
        precio=etPreciom.getText().toString();

        //Comprobamos si estan vacios los coampos para dar error

        if(codigo.isEmpty()||descripcion.isEmpty()||precio.isEmpty()){
            baseDeDatos.close();
            Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_LONG).show();

        } else {
            ContentValues registro=new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);
            //Insertamos el registro en la tabla articulos de la base de datos
            ((SQLiteDatabase) baseDeDatos).insert("articulos", null, registro);

            //Cerramos la base de datos

            baseDeDatos.close();

            //Limpiar los campos de texto
            etCodm.setText("");
            etDescipm.setText("");
            etPreciom.setText("");
        }
    }
}
