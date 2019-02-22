package com.example.juegoasync;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RadioGroup;

public class DialogoNombreNivel extends DialogFragment implements DialogInterface.OnClickListener {

    protected EditText etNombre;
    protected RadioGroup radioG;
    protected String nombre;
    protected onDialogoNombreNivel miListener;

    public static DialogoNombreNivel newInstance() {
        return new DialogoNombreNivel();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialogo_nombre_nivel, null))
                .setTitle(R.string.nueva_partida)
                .setPositiveButton(R.string.jugar, this)
                .setNegativeButton(R.string.volver, this)
                .setCancelable(false);
        return builder.create();
    }

    public interface onDialogoNombreNivel {
        void onAceptarDialogo(String nombre, int velocidad);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        etNombre = (EditText) ((Dialog) dialog).findViewById(R.id.tiNombre);
        nombre = etNombre.getText().toString();
        radioG = (RadioGroup) ((Dialog) dialog).findViewById(R.id.rg);
        int velocidad = 0;
        int nivel = radioG.getCheckedRadioButtonId();
        switch (nivel) {
            case R.id.rbFacil:
                velocidad = 60;
                break;
            case R.id.rbMedio:
                velocidad = 40;
                break;
            case R.id.rbDificil:
                velocidad = 20;
                break;
        }
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                miListener.onAceptarDialogo(nombre, velocidad);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                getActivity().finish();
                break;
        }
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
            miListener = (onDialogoNombreNivel) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onDialogoNombreNivel");
        }
    }


}
