package com.example.clientechatangelsalas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class DialogoConexion extends DialogFragment implements Button.OnClickListener {

    onDialogoConectar miListener;
    int puerto = 0;
    InetAddress ip = null;
    Button bConectar;
    EditText etIpServidor, etPuerto;
    TextView tvError;
    Socket conex;

    public static DialogoConexion newInstance(){
        return new DialogoConexion();
    }



    public Dialog onCreateDialog(Bundle saveInstanceState){
        //Declarar el cuadro de dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Obtener inflador del layout
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //Asignar layout
        View view = inflater.inflate(R.layout.layout_conexion, null);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Referencias
        etIpServidor = (EditText) view.findViewById(R.id.etIpServidor);
        etPuerto = (EditText) view.findViewById(R.id.etPuerto);
        tvError = (TextView) view.findViewById(R.id.tvError);
        bConectar = (Button) view.findViewById(R.id.button);

        //Instanciar cuadro dialogo
        builder.setView(view);
        bConectar.setOnClickListener(this);



        return builder.create();
    }

    public interface onDialogoConectar{
        public void onConectar(Socket conexion);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity=null;
        if(context instanceof Activity) {
            activity =(Activity)context;
        }
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            miListener = (onDialogoConectar) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement onDialogoConectar");
        }
    }

    @Override
    public void onClick(View v) {
        //Obtener datos de conexión
        try {
            ip = InetAddress.getByName(etIpServidor.getText().toString());
            puerto = Integer.parseInt(etPuerto.getText().toString());

            //Comprobar si se puede establecer la conexión
            if(probarConexion()){
                //Si se establece la conexión lanzamos el metodo para el oyente
                miListener.onConectar(conex);
                dismiss(); //Cerrar ventana
            }else{
                //Si no se establece lo indicamos
                tvError.setText("No se ha podido establecer la conexión. Revisa los datos.");
            }
        }catch (UnknownHostException ex) {
            //Si no se puede crear la ip con el texto introducido, se notificará
            tvError.setText("Formato de dirección IP introducida erroneo.");

        }catch (NetworkOnMainThreadException nme){
            //Si no se puede crear la ip con el texto introducido, se notificará
            tvError.setText("Formato de dirección IP introducida erroneo.");

        }catch (NumberFormatException ne){
            //Si no se puede crear la ip con el texto introducido, se notificará
            tvError.setText("Puerto de conexión introducido erroneo.");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * METODO PARA COMPROBAR SI SE PUEDE ESTABLECER LA CONEXIÓN
     * @return
     */
    public boolean probarConexion() {
        //Crear conexión
        try {
            conex = new Socket(ip, puerto);
            return true;
        } catch (ConnectException ce) {
            return false;
        } catch (IOException ex) {
            return false;
        }
    }
}
