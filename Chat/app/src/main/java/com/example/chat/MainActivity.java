package com.example.chat;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static java.util.Arrays.copyOf;

public class MainActivity extends AppCompatActivity implements Dialogo.onDialogo{

    //InetAddress ip;
    //int puerto;
    Socket socket;
    EditText escribir, area;
    PrintWriter salida;
    String pass;
    byte[] passbyteprov;
    byte[] passbytebuena;
    SecretKey clave;
    Cipher cifrador;
    String salir;
    Recibir recibir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        escribir=(EditText)findViewById(R.id.escribir);
        escribir.requestFocus();
        area=(EditText)findViewById(R.id.area);

        pass = "Secreto";
        salir="exit";

        mostrarDialogo();


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        try {
            passbyteprov = pass.getBytes("UTF8");
            passbytebuena = copyOf(passbyteprov, 32);
            clave = new SecretKeySpec(passbytebuena, "AES");
            cifrador = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cifrador.init(Cipher.ENCRYPT_MODE, clave);
        } catch (UnsupportedEncodingException ex) {

        } catch (NoSuchAlgorithmException ex) {
            ;
        } catch (NoSuchPaddingException ex) {

        } catch (InvalidKeyException ex) {

        }
    }

    public void enviarMensaje(View v){

        if (!escribir.getText().toString().equals("")) {

            try {
                salida.println(codBase64(cifrador.doFinal(escribir.getText().toString().getBytes("UTF8"))));
            } catch (UnsupportedEncodingException ex) {
                //Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                //Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                //Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }

            area.setText(area.getText().toString() + "\n[Cliente]>" + escribir.getText());

            escribir.setText("");

            area.setSelection(area.length());
        }

    }

    public void mostrarDialogo(){
        Dialogo miDialogo = Dialogo.newInstance();
        miDialogo.setCancelable(false);
        miDialogo.show(getSupportFragmentManager(), "Dialogo");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            salida.println(codBase64(cifrador.doFinal(salir.getBytes("UTF8"))));
        } catch (BadPaddingException e) {
            //e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            //e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
        }
    }


    public  String codBase64(byte [] a) {
        return Base64.encodeToString(a, Base64.DEFAULT);

    }

    @Override
    public void onAceptarDialogo(InetAddress ip, int puerto) {

        if (puerto == -1) {

            mostrarDialogo();
        }else{

            try {
                socket=new Socket(ip, puerto);
                area.setText("Conectado a " + socket.getInetAddress() + " en el puerto " + socket.getPort());
                try {
                    salida = new PrintWriter(socket.getOutputStream(), true);
                } catch (IOException ex) {
                    //Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                }
                recibir=new Recibir();
                recibir.execute(socket);
            } catch (IOException e) {
                Toast.makeText(this, "No se ha encontrado el servidor", Toast.LENGTH_SHORT).show();
                mostrarDialogo();
                //e.printStackTrace();
            }
        }
    }

    private class Recibir extends AsyncTask<Socket, Void, Void> {

        protected Socket socket;
        EditText area;//=findViewById(R.id.area);
        byte[] passbyteprov;
        byte[] passbytebuena;
        SecretKey clave;
        Cipher cifrador;
        String pass;// = "Secreto";
        BufferedReader entrada;
        String cadI;

        @Override
        protected Void doInBackground(Socket... sockets) {

            socket=sockets[0];
            try {
                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException ex) {
                //Logger.getLogger(recibir.class.getName()).log(Level.SEVERE, null, ex);
            }
            pass = "Secreto";
            cadI="";
            area=findViewById(R.id.area);
            try {
                passbyteprov = pass.getBytes("UTF8");
                passbytebuena = copyOf(passbyteprov, 32);
                clave = new SecretKeySpec(passbytebuena, "AES");
                cifrador = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                cifrador.init(Cipher.DECRYPT_MODE, clave);
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
            while (!cadI.equalsIgnoreCase("exit")) {
                try {
                    cadI = entrada.readLine();
                } catch (IOException ex) {
                    //Logger.getLogger(recibir.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(cadI!=null&&!cadI.equalsIgnoreCase("exit")){
                    try {
                        area.setText(area.getText()+"\n"+new String(cifrador.doFinal(Base64.decode(cadI.getBytes("UTF8"), Base64.DEFAULT))));
                    } catch (UnsupportedEncodingException ex) {
                        //Logger.getLogger(Recibir.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalBlockSizeException ex) {
                        //Logger.getLogger(Recibir.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (BadPaddingException ex) {
                        //Logger.getLogger(Recibir.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //area.setText(area.getText().toString()+"\n"+cadI);
                    area.setSelection(area.length());
                }
            }
            return null;
        }
    }
}
