package com.example.juanra.p11repaso2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNombre, etPrecio, etOpinion, etNota, etRecomendable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre =(EditText) findViewById(R.id.etNombre);
        etPrecio =(EditText) findViewById(R.id.etDescripcion);
        etOpinion =(EditText) findViewById(R.id.etPrecio);
        etNota =(EditText) findViewById(R.id.etNota);
        etRecomendable =(EditText) findViewById(R.id.etRecomendable);
    }

    public void RegistrarClick(View view){

        //Crear la clase para poder administrar DQLite heredando de SQLiteOpenHelper
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //Abrir la base de datos
        //La base de datos debe ser de tipo SQLiteDatabase
        SQLiteDatabase baseDeDatos=admin.getWritableDatabase();

        //Almacenamos los datos introducidos por pantalla

        String nombre, opinion, precio, recomendable, nota;
        nombre= etNombre.getText().toString();
        opinion= etPrecio.getText().toString();
        precio= etOpinion.getText().toString();
        nota= etNota.getText().toString();
        recomendable= etRecomendable.getText().toString();

        //Comprobamos si estan vacios los coampos para dar error

        if(nombre.isEmpty()||opinion.isEmpty()||precio.isEmpty()||recomendable.isEmpty()||nota.isEmpty()){
            baseDeDatos.close();
            Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_LONG).show();

        } else {
            ContentValues registro=new ContentValues();
            registro.put("nombre", nombre);
            registro.put("precio", precio);
            registro.put("opinion", opinion);
            registro.put("nota", nota);
            registro.put("recomendable", recomendable);

            //Insertamos el registro en la tabla articulos de la base de datos
            ((SQLiteDatabase) baseDeDatos).insert("juegos", null, registro);

            //Cerramos la base de datos

            baseDeDatos.close();

            //Limpiar los campos de texto
            etNombre.setText("");
            etPrecio.setText("");
            etOpinion.setText("");
            etNota.setText("");
            etRecomendable.setText("");
        }
    }

    public void BuscarClick(View view){

        //Crear la clase para poder administrar SQLite heredando de SQLiteOpenHelper
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //Abrir la base de datos
        //La base de datos debe ser de tipo SQLiteDatabase
        SQLiteDatabase baseDeDatos=admin.getWritableDatabase();
        String nombre= etNombre.getText().toString();

        //Comprobar que no esté vacio el código introducido por el usuario
        if(!nombre.isEmpty()){
            //Toast.makeText(this, "Entro", Toast.LENGTH_LONG).show();
            //Creamos un cursor que es el que me referencia a los datos devueltos por la consulta sql


            Cursor fila=baseDeDatos.rawQuery("select precio, opinion, nota, recomendable from juegos where nombre like '%"+nombre+"%'", null);

            //mover el cursor al principio de los datos, si se puede mover correctamente es que hay datos.

            if(fila.moveToFirst()){
                //La columna 0 es la primera del conjunto de datos que devuelve la consulta a través del cursor

                etPrecio.setText(fila.getString(0));
                //La columna 0 contiene la descripcion y la 1 contiene el precio
                etOpinion.setText(fila.getString(1));

                etNota.setText(fila.getString(2));

                etRecomendable.setText(fila.getString(3));

            } else {
                Toast.makeText(this, "No existe el juego", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "Debes introducir el nombre del juego", Toast.LENGTH_LONG).show();
        }

    }

    public void ModificarClick(View view){

        //Crear la clase para poder administrar DQLite heredando de SQLiteOpenHelper
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //Abrir la base de datos
        //La base de datos debe ser de tipo SQLiteDatabase
        SQLiteDatabase baseDeDatos=admin.getWritableDatabase();
        String nombre= etNombre.getText().toString();
        String nota= etNota.getText().toString();
        String recomendable= etRecomendable.getText().toString();
        String opinion= etOpinion.getText().toString();
        String precio= etPrecio.getText().toString();
        //Comprobar que los campos estan completos

        if(nombre.isEmpty()||nota.isEmpty()||recomendable.isEmpty()||opinion.isEmpty()||precio.isEmpty()){
            Toast.makeText(this, "Rellena todos los datos", Toast.LENGTH_LONG).show();
        }else{
            //Creo el registro fila
            ContentValues registro=new ContentValues();
            registro.put("nombre", nombre);
            registro.put("precio", precio);
            registro.put("opinion", opinion);
            registro.put("nota", nota);
            registro.put("recomendable", recomendable);


            //Modificar un registro de la tabla (la nota o el recomendable)
            //Pasamos: la tabla, el registro ya modificado y la clausula where que nos va a servir para encontrar la tupla a modificar
            //Devuelve el numero de tuplas modificadas


            int numTuplas=baseDeDatos.update("juegos", registro, "nombre like '%"+nombre+"%'", null);

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
        String nombre= etNombre.getText().toString();
        //Comprobar que los campos estan completos

        if(nombre.isEmpty()){
            Toast.makeText(this, "Indica el nombre", Toast.LENGTH_LONG).show();
        }else {

            //Borrar un registro de la tabla
            //Pasamos: la tabla, la clausula where que nos va a servir para encontrar la tupla a borrar
            //Devuelve el numero de tuplas borradas


            int numTuplas = baseDeDatos.delete("juegos", "nombre like '%"+nombre+"%'", null);

            if (numTuplas == 1) {
                Toast.makeText(this, "Borrado correcto", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Borrado incorrecto", Toast.LENGTH_LONG).show();
            }

            baseDeDatos.close();
        }
    }

    public void siguienteActivity(View view){

        //Para comunicar activitus necesitamos hacerlo a traves de la clase Intent(como intento)

        //Creamos el objeto Intent
        Intent siguiente=new Intent(this, Main2Activity.class);

        //Iniciamos el activity que queremos
        startActivity(siguiente);

        //No olvidar asociar evento onClick
    }

}


