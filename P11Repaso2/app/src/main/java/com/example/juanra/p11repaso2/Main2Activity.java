package com.example.juanra.p11repaso2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText texto;
    String codigo, nombre, precio, Color;
    int n, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        texto = (EditText) findViewById(R.id.texto);

        //texto.setText("");
        recuperar();
    }

    public void cerrar(View view){

        finish();
    }

    public void recuperar(){

        //Crear la clase para poder administrar SQLite heredando de SQLiteOpenHelper
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //Abrir la base de datos
        //La base de datos debe ser de tipo SQLiteDatabase
        SQLiteDatabase baseDeDatos=admin.getWritableDatabase();

        Cursor fila=baseDeDatos.rawQuery("select * from juegos", null);
        //mover el cursor al principio de los datos, si se puede mover correctamente es que hay datos.
        //texto.setText(fila.getColumnCount());
        n=fila.getCount();
        c=0;
        //texto.setText(String.valueOf(n));
        if(fila.moveToFirst()){
            //Toast.makeText(this, fila.getColumnCount(), Toast.LENGTH_LONG).show();
            while(c<n) {
                //try {
                if(c!=0){
                    texto.setText(texto.getText()+"\n\n");
                }
                texto.setText(texto.getText()+"Juego "+String.valueOf(c+1)+"\n\fNombre: "+fila.getString(0)+"\n\fopinion: "+fila.getString(1)+"\n\fprecio: "+fila.getString(2)+"\n\fnota: "+fila.getString(3)+"\n\frecomendable: "+fila.getString(4));
                fila.moveToNext();
                c++;
                /*} catch (Exception e) {

                }*/
            }


        } else {
            texto.setText("No existen registros");
        }

    }
}
