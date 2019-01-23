package com.example.juanra.juegov12;

import android.content.Context;

//Clase papra personalizar las celdas o botones del juego

public class Celdas extends android.support.v7.widget.AppCompatButton {

    int idBoton, numeroElementos, contenidoCelda, fondo, fila, columna;

    public Celdas(Context context, int iB, int nE, int cC, int f, int fil, int col){

        super(context);

        idBoton=iB;
        numeroElementos=nE;
        contenidoCelda=cC;
        fondo=f;
        fila=fil;
        columna=col;
        setBackgroundResource(fondo);
    }
    public int getNuevoFondo(){
        contenidoCelda++;
        if(contenidoCelda==numeroElementos){
            contenidoCelda=1;
        }
        return contenidoCelda;
    }
}
