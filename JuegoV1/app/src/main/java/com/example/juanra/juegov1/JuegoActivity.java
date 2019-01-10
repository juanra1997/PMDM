package com.example.juanra.juegov1;

import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        //Cogemos del layout
        crono1=(Chronometer) findViewById(R.id.crono1);
        tvClicks=(TextView) findViewById(R.id.tv_clicks);

        layoutTablero=(LinearLayout)findViewById(R.id.layout_tablero);
        layoutTablero.removeAllViews();
    }
}
