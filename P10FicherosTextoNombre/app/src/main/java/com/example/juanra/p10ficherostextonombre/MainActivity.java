package com.example.juanra.p10ficherostextonombre;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    //Cambiar gravity del etcontenido para que escriba por arriba

    //soliciar permiso de escritura en la tarjeta externa en androidmanifest

    EditText etGrande, etNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etGrande=(EditText) findViewById(R.id.etgrande);
        etNombre=(EditText) findViewById(R.id.etFichero);
    }

    public void GuardarClick(View view){

        String nombre=etNombre.getText().toString(), contenido=etGrande.getText().toString();



        try{

            //Recuperamos la ruta de la tarjeta SD con la clase Enviroment, para ello creamos un fichero auxiliar

            File tarjetaSD=Environment.getExternalStorageDirectory();
            Toast.makeText(this, "Ruta valida "+tarjetaSD.getPath(), Toast.LENGTH_LONG).show();

            //Creamos el fichero con el nombre que el usuario ha elegido

            File ArchivodelUsuario=new File(tarjetaSD.getPath(), nombre);

            //Abrimos el fichero para escribir

            OutputStreamWriter miArchivo=new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));

            //Pasarle el texto del objeto edittext contenido a mi archivo

            miArchivo.write(contenido);

            //Limpiamos el buffer y despues lo cerramos

            miArchivo.flush();
            miArchivo.close();

            //Limpiar los controles del activity

            etNombre.setText("");
            etGrande.setText("");

        } catch(Exception e){

        }
    }

    public void Leerclick(View view){

        String nombre=etNombre.getText().toString();
        try{

            File tarjetaSD=Environment.getExternalStorageDirectory();

            File ArchivodelUsuario=new File(tarjetaSD.getPath(), nombre);
            //Abrir el fichero para lectura en modo privado
            InputStreamReader abriArchivo=new InputStreamReader(openFileInput(nombre/*, Activity.MODE_PRIVATE*/));
            //Crear el buffer
            BufferedReader contenedor=new BufferedReader(abriArchivo);
            //Para leer de un fichero de texto vamos a hacerlo linea a linea
            String linea=contenedor.readLine();
            //Creo una variable en la que voy a almacenar cada linea
            String contenidoCompleto="";
            //Bucle para recorrer el archivo leyendo cada linea hasta leer null
            while(linea!=null){

                contenidoCompleto=contenidoCompleto+linea+"\n";
                linea=contenedor.readLine();
            }
            contenedor.close();
            abriArchivo.close();
            //Asignar al control del activity el texto leido
            etGrande.setText(contenidoCompleto);
            Toast.makeText(this, "Archivo recuperado con exito", Toast.LENGTH_LONG).show();
        } catch (Exception e){

            Toast.makeText(this, "Error al obtener el archivo", Toast.LENGTH_LONG).show();
        }
    }
}
