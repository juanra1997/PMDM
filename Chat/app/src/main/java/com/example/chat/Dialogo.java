package com.example.chat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Dialogo extends DialogFragment implements DialogInterface.OnClickListener {


    protected onDialogo miListener;
    protected EditText ip, puerto;
    protected InetAddress miip;
    protected int mipuerto;

    public static Dialogo newInstance(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return new Dialogo();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.crearconexion, null))
                .setTitle("Creacion de la conexion")
                .setPositiveButton("Conectar", this)
                .setNegativeButton("Cancelar", this)
                .setCancelable(false);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {


        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                ip=(EditText)((Dialog) dialog).findViewById(R.id.ip);
                puerto=(EditText)((Dialog) dialog).findViewById(R.id.puerto);
                try {
                    miip=InetAddress.getByName(ip.getText().toString());
                } catch (UnknownHostException e) {

                    Toast.makeText(getActivity(), "Direccion erronea", Toast.LENGTH_SHORT).show();
                    /*new AlertDialog.Builder(getActivity())
                            .setTitle("Error")
                            .setMessage("Direccion erronea")
                            .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setCancelable(false)
                            .show();*/
                    try {
                        miListener.onAceptarDialogo(InetAddress.getByName("localhost"), -1);
                    } catch (UnknownHostException e1) {
                        //e1.printStackTrace();
                    }
                    break;

                }
                try {
                    mipuerto = Integer.parseInt(puerto.getText().toString());
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Puerto erroneo", Toast.LENGTH_SHORT).show();
                    try {
                        miListener.onAceptarDialogo(InetAddress.getByName("localhost"), -1);
                    } catch (UnknownHostException e1) {
                        //e1.printStackTrace();
                    }
                    break;
                }
                miListener.onAceptarDialogo(miip, mipuerto);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                getActivity().finish();
                break;
        }
    }

    public interface onDialogo{
        void onAceptarDialogo(InetAddress ip, int puerto);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        new Dialogo();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Activity activity=getActivity();
        Activity activity = null;
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
        try {
            miListener = (onDialogo) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onDialogoNombreNivel");
        }
    }
}
