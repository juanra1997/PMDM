package com.example.juanra.juegov1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

public class JuegoActivity extends AppCompatActivity {

    int[] numeros=new int[]{
            R.drawable.ic_1n,
            R.drawable.ic_2n,
            R.drawable.ic_3n,
            R.drawable.ic_4n,
            R.drawable.ic_5n,
            R.drawable.ic_6n
    };

    int[] colores=new int[]{
            R.drawable.ic_1c,
            R.drawable.ic_2c,
            R.drawable.ic_3c,
            R.drawable.ic_4c,
            R.drawable.ic_5c,
            R.drawable.ic_6c
    };

    protected int[] tablero;//seran igual a numeros o a colores
    protected int[][] valoresCeldas;//para los valores que valen los botones
    protected int[][] idCeldas;//para crear un id a cada celda que guardare aqui
    protected MatrizJuego miMatriz;
    protected LinearLayout layoutTablero;//el layout que dejamos sin rellenar
    protected int filas, columnas, nElementos, vibrar, sonar, esnumero, numClicks;
    protected Chronometer crono1;//el crono
    protected TextView tvClicks;
    protected Vibrator vibService;
    protected MediaPlayer mp;
    protected int altura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        //Cogemos del layout
        crono1=(Chronometer) findViewById(R.id.crono1);
        tvClicks=(TextView) findViewById(R.id.tv_clicks);

        layoutTablero=(LinearLayout)findViewById(R.id.layout_tablero);
        layoutTablero.removeAllViews();

        cogerDatosJuego();

        if(vibrar==1){
            //Damos los permisos en el manifest
            vibService=(Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
        }
        if(sonar==1){
            mp=MediaPlayer.create(this, R.raw.touch);
        }
        //Iniciamos la  matriz
        miMatriz=new MatrizJuego(filas, columnas, nElementos);
        valoresCeldas=miMatriz.getMatriz();

        //Elegimos colores o numeros en funcion de esnumeros

        if(esnumero==1){
            tablero=numeros;
        }else{
            tablero=colores;
        }

        //Ponemos la pantalla

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        altura=(dm.heightPixels-180)/filas;

        numClicks=0;
        iniciaJuego();
    }

    public void cogerDatosJuego(){

        Bundle datos=getIntent().getExtras();
        filas=datos.getInt(MainActivity.FILAS);
        columnas=datos.getInt(MainActivity.COLUMNAS);
        nElementos=datos.getInt(MainActivity.ELEMENTOS);
        esnumero=datos.getInt(MainActivity.NUMEROS);
        vibrar=datos.getInt(MainActivity.VIBRAR);
        sonar=datos.getInt(MainActivity.SONAR);
    }

    public void iniciaJuego(){
        int indiceBoton=0;
        int valor=0;
        for(int i=0; i<filas; i++){
            //Creamos un layout por fila
            LinearLayout lyFila=new LinearLayout(this);
            lyFila.setOrientation(LinearLayout.HORIZONTAL);
            for(int j=0; j<columnas; j++){
                valor=valoresCeldas[i][j];
                //Creo cada uno de los botones le paso el fondo y le pongo listener e id
                Celdas celda=new Celdas(this, ++indiceBoton, nElementos, valor, tablero[valor], i, j);
                celda.setId(indiceBoton);
                idCeldas[i][j]=indiceBoton;
                celda.setLayoutParams(new LinearLayout.LayoutParams(0, altura, 1.0f));
                //Listener a los botones
                celda.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Como celda es algo que heredamos hay que realizar el casting
                        pularCelda(((Celdas)v).fila,((Celdas)v).columna);
                    }
                });
                //Añadimos esto al layout que creamos al principio
                lyFila.addView(celda);
            }
            layoutTablero.addView(lyFila);
        }
    }
    public void pularCelda(int fila, int columna){
        crono1.start();
        if(sonar==1){
            mp.start();
        }
        if(vibrar==1){
            vibService.vibrate(80);
        }
        numClicks++;
        tvClicks.setText(""+numClicks);
        //Cambio filas
        for(int i=maximo(0, fila-1); i<=minimo(filas-1, fila+1); i++){
            cambiar(i, columna);
        }
        //Cambio columnas
        for(int j=maximo(0, columna-1); j<=minimo(columna+1, columnas-1); j++){
            if(j==columna){
                continue;
            }
            cambiar(fila, j);
        }
        checkGanar();
    }
    public void checkGanar(){
        int target=valoresCeldas[0][0];
        for(int i=0; i<filas; i++){
            for(int j=0; j<columnas; j++){
                if(valoresCeldas[i][j]!=target){
                    return;
                }
            }
        }
        //Si llegamos aqui hemos ganado, bieeennnnn
        Intent i=new Intent();
        //i.putExtra(MainActivity.TOTAL_CLICKS, numClicks);
        i.putExtra(MainActivity.TOTAL_CLICKS, numClicks);
        setResult(RESULT_OK, i);
        finish();
    }
    public void cambiar(int f, int c){
        //Cojo el id de la celda que quiero cambiar
        int idCelda=idCeldas[f][c];
        //Recupero la celda
        Celdas celda=(Celdas)findViewById(idCelda);
        int nuevoValor=celda.getNuevoFondo();
        //Actualiza el nuevo valor en el array de valores
        valoresCeldas[f][c]=nuevoValor;
        //Cambio el fondo de la celda
        celda.setBackgroundResource(tablero[nuevoValor]);
        //Para que lo pinte sin problemas
        celda.invalidate();
    }
    //----------------------------------------------------------------------------------------------
    public int maximo(int a, int b) {
        if (a>b) {
            return a;
        } else {
            return b;
        }
    }
    public int minimo(int a, int b){
        if(a<b){
            return a;
        }else{
            return b;
        }
    }
}
