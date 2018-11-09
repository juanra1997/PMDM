package com.example.juanra.p11sqlitejuanraulmelladogarcia;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etCodm, etDescipm, etPreciom, etColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCodm=(EditText) findViewById(R.id.etNombre);
        etDescipm=(EditText) findViewById(R.id.etDescripcion);
        etPreciom=(EditText) findViewById(R.id.etPrecio);
        etColor=(EditText) findViewById(R.id.color);
    }

    public void RegistrarClick(View view){

        //Crear la clase para poder administrar DQLite heredando de SQLiteOpenHelper
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //Abrir la base de datos
        //La base de datos debe ser de tipo SQLiteDatabase
        SQLiteDatabase baseDeDatos=admin.getWritableDatabase();

        //Almacenamos los datos introducidos por pantalla

        String codigo, descripcion, precio, color;
        codigo=etCodm.getText().toString();
        descripcion=etDescipm.getText().toString();
        precio=etPreciom.getText().toString();
        color=etColor.getText().toString();

        //Comprobamos si estan vacios los coampos para dar error

        if(codigo.isEmpty()||descripcion.isEmpty()||precio.isEmpty()||color.isEmpty()){
            baseDeDatos.close();
            Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_LONG).show();

        } else {
            ContentValues registro=new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);
            registro.put("color", color);
            //Insertamos el registro en la tabla articulos de la base de datos
            ((SQLiteDatabase) baseDeDatos).insert("articulos", null, registro);

            //Cerramos la base de datos

            baseDeDatos.close();

            //Limpiar los campos de texto
            etCodm.setText("");
            etDescipm.setText("");
            etPreciom.setText("");
            etColor.setText("");
        }
    }

    public void BuscarClick(View view){

        //Crear la clase para poder administrar SQLite heredando de SQLiteOpenHelper
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //Abrir la base de datos
        //La base de datos debe ser de tipo SQLiteDatabase
        SQLiteDatabase baseDeDatos=admin.getWritableDatabase();
        String codigo=etCodm.getText().toString();

        //Comprobar que no esté vacio el código introducido por el usuario
        if(!codigo.isEmpty()){
            //Creamos un cursor que es el que me referencia a los datos devueltos por la consulta sql
            Cursor fila=baseDeDatos.rawQuery("select descripcion, precio, color from articulos where codigo="+codigo, null);
            //mover el cursor al principio de los datos, si se puede mover correctamente es que hay datos.
            if(fila.moveToFirst()){
            //La columna 0 es la primera del conjunto de datos que devuelve la consulta a través del cursor
                etDescipm.setText(fila.getString(0));
             //La columna 0 contiene la descripcion y la 1 contiene el precio
                etPreciom.setText(fila.getString(1));

                etColor.setText(fila.getString(2));

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
        String color=etColor.getText().toString();
        //Comprobar que los campos estan completos

        if(codigo.isEmpty()||descripcion.isEmpty()||precio.isEmpty()){
            Toast.makeText(this, "Rellena todos los datos", Toast.LENGTH_LONG).show();
        }else{
            //Creo el registro fila
            ContentValues registro=new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);
            registro.put("color", color);
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

    public void siguienteActivity(View view){

        //Para comunicar activitus necesitamos hacerlo a traves de la clase Intent(como intento)

        //Creamos el objeto Intent
        Intent siguiente=new Intent(this, Main2Activity.class);

        //Iniciamos el activity que queremos
        startActivity(siguiente);

        //No olvidar asociar evento onClick
    }

}

//Vais a poner tambien eeeehhhhmmmm un campo que se va a llamar color, el color va a ser de tipo texto plano (Plain text) se va a llamar etColor-----hecho
//Creamos un string "Color del producto"-------hecho
//Listar todos abre un nuevo activity con un control de multiline que muestre la tabla (1, abono, 20, Negra)-------hecho
//En esa activity un boton de volver-------hecho

//P11Repaso2 esto mismo pero con la base de datos que elijamos (5 campos)
//Un boton buscar por otro campo que no sea el codigo y devolver los resultados en el segundo activity