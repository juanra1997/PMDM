package com.example.juanra.juegov2;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class JuegoActivity extends BaseActivity implements DialogoNombreNivel.onDialogoNombreNivel, View.OnClickListener {

    private static final String KEY_VELOCIDAD="velocidad";//Para el bundle
    private static final String KEY_PROGRESO="progreso";//Para el bundle

    private int velocidad, progreso, numFase, contadorBotones;

    protected TextView tvNombre, tvProgreso, tvFase;
    protected Button boton1, boton2, boton3, boton4;
    protected ProgressBar proBar;
    protected boolean partidaAcabada;//Partida acabada o no
    private ArrayList<Button> arrayBotones;//guardamos los botones
    private Bundle estado;//Para guardar el juego en onPause

    private ControlProgresoTask contProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        //1. Pantalla completa
        setModoInmersivo();

        //2. Cogemos todos los elementos
        setView();

        //3. Mostramos Dialogo de inicio
        mostrarDialogo();

        //4. Ponemos listener a los botones
        ponerListeners();

    }

    @Override
    public void onAceptarDialogo(String nombre, int velocidad){
        partidaAcabada=false;
        this.velocidad=velocidad;
        progreso=0;
        contadorBotones=1;
        numFase=1;
        //Modo inversivo
        setModoInmersivo();
        //Si hemos mandad un nommbre lo ponemos
        if(!nombre.trim().isEmpty()){
            tvNombre.setText(nombre);
        }
        tvFase.setText(getString(R.string.fase, String.valueOf(numFase)));
        //Iniciamos la tarea asincrona y la ejecutamos
        contProgreso=new ControlProgresoTask();
        contProgreso.execute(this.velocidad, progreso);
    }

    @Override
    public void onClick(View v){
        Button botonPulsado=(Button)v;
        int valorBoton=Integer.valueOf(botonPulsado.getText().toString());
        if(valorBoton==contadorBotones){
            botonPulsado.setEnabled(false);
            contadorBotones++;
            if(contadorBotones==5)contProgreso.cancel(true);
        }
    }
    public void ponerListeners(){
        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
        boton4.setOnClickListener(this);
    }
    public void mostrarDialogo(){
        DialogoNombreNivel miDialogo=DialogoNombreNivel.newInstance();
        miDialogo.setCancelable(false);
        miDialogo.show(getSupportFragmentManager(), "Dialogo Nombre Nivel");
    }
    public void setView(){
        tvNombre=(TextView)findViewById(R.id.tvNombre);
        tvFase=(TextView)findViewById(R.id.tvFase);
        tvProgreso=(TextView)findViewById(R.id.tvProgreso);

        boton1=(Button)findViewById(R.id.boton1);
        boton2=(Button)findViewById(R.id.boton2);
        boton3=(Button)findViewById(R.id.boton3);
        boton4=(Button)findViewById(R.id.boton4);

        proBar=(ProgressBar)findViewById(R.id.barraProgreso);

        //fijamos su tamaño para que se vea bien alto y ancho
        /*proBar.setScaleX(5f);
        proBar.setScaleY(8f);*/
        //proBar.setBackgroundColor(Color.BLACK);


        //metemos los botones en el arrayList
        arrayBotones=new ArrayList<Button>();
        arrayBotones.add(boton1);
        arrayBotones.add(boton2);
        arrayBotones.add(boton3);
        arrayBotones.add(boton4);

        numerarBotones(desordenarBotones());
    }

    public void numerarBotones(ArrayList<Integer> array){
        for(int i=0; i<array.size(); i++){
            arrayBotones.get(i).setEnabled(true);
            arrayBotones.get(i).setText(""+array.get(i));
        }
    }

    public static ArrayList<Integer> desordenarBotones(){
        ArrayList<Integer> array=new ArrayList<>();
        for(int i=1; i<5; i++){
            array.add(i);
        }
        Collections.shuffle(array);
        return array;
    }

    /*@Override
    public void onAceptarDialogo(String nombre, int velocidad) {

    }*/

    /*@Override
    public void onClick(View v) {

    }*/

    private class ControlProgresoTask extends AsyncTask<Integer, Integer, Integer>{

        @Override
        protected Integer doInBackground(Integer... integers) {//entran param salen resultado
            while (progreso<=100){
                SystemClock.sleep(integers[0]);
                contProgreso.publishProgress(progreso);
                progreso++;
                if(isCancelled()) break;
            }
            return progreso;
        }
        @Override
        protected void onProgressUpdate(Integer... integers){//Progreso
            super.onProgressUpdate(integers);
            proBar.setProgress(integers[0]);
            tvProgreso.setText(integers[0]+"/"+proBar.getMax());

        }
        @Override
        protected void onPostExecute(Integer integer){//resultado
            super.onPostExecute(integer);
            partidaAcabada=true;
            new AlertDialog.Builder(JuegoActivity.this)
                    .setTitle(R.string.fin)
                    .setMessage(R.string.perder)
                    .setPositiveButton(R.string.continuar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvNombre.setText(R.string.random_buttons);
                            mostrarDialogo();
                            numerarBotones(desordenarBotones());
                        }
                    })
                    .setNegativeButton(R.string.inicio, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
        @Override
        protected void onCancelled(Integer integer){
            super.onCancelled();
            if(contadorBotones==5){
                contProgreso=new ControlProgresoTask();
                numerarBotones(desordenarBotones());
                tvFase.setText(getString(R.string.fase, String.valueOf(++numFase)));
                contadorBotones=1;
                progreso=0;
                proBar.setProgress(0);
                velocidad=velocidad-5<=0?1:velocidad-5;
                contProgreso.execute(velocidad, progreso);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //guardamos el estado del juego
        if(contProgreso!=null){
            contProgreso.cancel(true);
            contProgreso=null;
            estado=new Bundle();
            estado.putInt(KEY_VELOCIDAD, velocidad);
            estado.putInt(KEY_PROGRESO, progreso);
        }
    }

    @Override
    protected void onResume() {
        setModoInmersivo();
        super.onResume();
        if(estado!=null&&!partidaAcabada){
            contProgreso=new ControlProgresoTask();
            contProgreso.execute(estado.getInt(KEY_VELOCIDAD), estado.getInt(KEY_PROGRESO));
        }
    }
}
