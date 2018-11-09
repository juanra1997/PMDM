package com.example.juanra.p11sqlitejuanraulmelladogarcia;

import android.content.ContentValues;
import android.database.Cursor;
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

    public void BuscarClick(View view){

        //Crear la clase para poder administrar DQLite heredando de SQLiteOpenHelper
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //Abrir la base de datos
        //La base de datos debe ser de tipo SQLiteDatabase
        SQLiteDatabase baseDeDatos=admin.getWritableDatabase();
        String codigo=etCodm.getText().toString();

        //Comprobar que no esté vacio el código introducido por el usuario
        if(!codigo.isEmpty()){
            //Creamos un cursor que es el que me referencia a los datos devueltos por la consulta sql
            Cursor fila=baseDeDatos.rawQuery("select descripcion, precio from articulos where codigo="+codigo, null);
            //mover el cursor al principio de los datos, si se puede mover correctamente es que hay datos.
            if(fila.moveToFirst()){
            //La columna 0 es la primera del conjunto de datos que devuelve la consulta a través del cursor
                etDescipm.setText(fila.getString(0));
             //La columna 0 contiene la descripcion y la 1 contiene el precio
                etPreciom.setText(fila.getString(1));
            } else {
                Toast.makeText(this, "No existe el articulo", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "Debes introducir el codigo del articulo", Toast.LENGTH_LONG).show();
        }

        }

    public void ModificarClick(View view){

        //Crear la clase para poder administrar DQLite heredando de SQLiteOpenHelper
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //Abrir la base de datos
        //La base de datos debe ser de tipo SQLiteDatabase
        SQLiteDatabase baseDeDatos=admin.getWritableDatabase();
        String codigo=etCodm.getText().toString();
        String descripcion=etDescipm.getText().toString();
        String precio=etPreciom.getText().toString();
        //Comprobar que los campos estan completos

        if(codigo.isEmpty()||descripcion.isEmpty()||precio.isEmpty()){
            Toast.makeText(this, "Rellena todos los datos", Toast.LENGTH_LONG).show();
        }else{
            //Creo el registro fila
            ContentValues registro=new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);
            //Modificar un registro de la tabla (la descripcion o el precio)
            //Pasamos: la tabla, el registro ya modificado y la clausula where que nos va a servir para encontrar la tupla a modificar
            //Devuelve el numero de tuplas modificadas


            int numTuplas=baseDeDatos.update("articulos", registro, "codigo="+codigo, null);

            if(numTuplas==1){
                Toast.makeText(this, "Cambio correcto", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Cambio incorrecto", Toast.LENGTH_LONG).show();
            }

            baseDeDatos.close();
        }
    }

    public void EliminarClick(View view){

        //Crear la clase para poder administrar DQLite heredando de SQLiteOpenHelper
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //Abrir la base de datos
        //La base de datos debe ser de tipo SQLiteDatabase
        SQLiteDatabase baseDeDatos=admin.getWritableDatabase();
        String codigo=etCodm.getText().toString();
        //Comprobar que los campos estan completos

        if(codigo.isEmpty()){
            Toast.makeText(this, "Indica el codigo", Toast.LENGTH_LONG).show();
        }else {

            //Borrar un registro de la tabla
            //Pasamos: la tabla, la clausula where que nos va a servir para encontrar la tupla a borrar
            //Devuelve el numero de tuplas borradas


            int numTuplas = baseDeDatos.delete("articulos", "codigo=" + codigo, null);

            if (numTuplas == 1) {
                Toast.makeText(this, "Borrado correcto", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Borrado incorrecto", Toast.LENGTH_LONG).show();
            }

            baseDeDatos.close();
        }
    }

}
