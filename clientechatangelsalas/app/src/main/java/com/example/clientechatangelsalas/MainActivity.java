package com.example.clientechatangelsalas;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements DialogoConexion.onDialogoConectar{

    Socket conex;
    String clave;
    PrintWriter salida = null;
    BufferedReader entrada = null;
    EditText etMensaje, etConversacion;
    Escucha escucha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clave="cifradoseguro";

        //REFERENCIAS
        etMensaje = (EditText) findViewById(R.id.etMensaje);
        etConversacion = (EditText) findViewById(R.id.etConversacion);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Crear dialogo conexion
        DialogoConexion diaCon = DialogoConexion.newInstance();
        diaCon.show(getSupportFragmentManager(), "DialogoConexion");
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        enviarMensaje("/x");
    }

    /**
     * SOBRESCRITURA DEL METODO onConectar
     * OBTENEMOS LA CONEXIÓN CON EL SERVIDOR
     * @param conexion
     */
    @Override
    public void onConectar(Socket conexion) {

        try {
            conex = conexion;
            //Crear flujos de entrada y salida para la conexión
            salida = new PrintWriter(conex.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(conex.getInputStream()));

            escucha = new Escucha();
            escucha.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------------

    public void botonEnviar(View view){
        //Obtener el texto del campo de texto
        String texto = etMensaje.getText().toString();
        enviarMensaje(texto);
        //Limpiar campo texto
        etMensaje.setText("");
    }

    /**
     * METODO PARA ENVIAR UN MENSAJE AL SERVIDOR CIFRADO Y CODIFICADO EN BASE 64
     * @param txt
     */
    public void enviarMensaje(String txt){
        //Cifrar el mensaje
        String cifrado = Cifrado.cifrar(clave, txt);
        //Enviar el mensaje cifrado al servidor
        salida.println(cifrado);
    }

    //----------------------------------------------------------------------------------------------

    /**
     * METODO PARA CERRAR LA CONEXION Y LOS FLUJOS DE SALIDA
     */
    public void cerrarConexion(){
        finish();
    }


    //==============================================================================================

    /**
     * TAREA ASINCRONA PARA LA CUENTA ATRÁS INICIAL
     */
    //como parametros del asyncTask podemos pasar void si no queremos mandar nada
    private class Escucha extends AsyncTask<Void, String, Void> {

        /**
         * METODO QUE SE EJECUTARÁ EN SEGUNDO PLANO
         * @param voids
         * @return
         */
        @Override
        protected Void doInBackground(Void... voids) {
            String cifrado;
            String descifrado="";
            do{
                try {
                    Log.d("eeee","eeee");
                    cifrado = entrada.readLine().trim();
                    descifrado=Cifrado.descifrar(clave, cifrado);

                    //Si el mensaje recibido del servidor es exit finalizamos este hilo
                    if(descifrado.equalsIgnoreCase("/x")){
                        cerrarConexion();
                    }else{
                        escucha.publishProgress(descifrado);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(isCancelled())
                    break;
            }while (descifrado.equalsIgnoreCase("/x")==false);

            return null;
        }

        /**
         * METODO PARA PUBLICAR CAMBIOS DE PROCESO
         * @param strings
         */
        @Override
        protected void onProgressUpdate(String ... strings){
            //Añadimos el texto descifrado al cuadro de texto
            etConversacion.append(strings[0]+"\n");
            //Autoscroll en el texto multilinea
            etConversacion.setSelection(etConversacion.length());
        }

        /**
         * METODO QUE SE EJECUTA AL FINALIZAR EL PROCESO
         * @param voids
         */
        @Override
        protected  void onPostExecute(Void voids){

        }

        @Override
        protected void onCancelled(Void voids){

        }
    }
}
