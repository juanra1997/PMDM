package com.example.juegoasync;

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
import android.widget.Toast;

public class JuegoActivity extends BaseActivity implements DialogoNombreNivel.onDialogoNombreNivel {

    private int velocidad, progreso, numFase;
    protected boolean partidaAcabada;
    protected TextView tvNombre, tvFase;
    protected ProgressBar proBar;
    protected Button boton1, boton2, boton3, boton4, boton5, boton6, boton7, boton8, boton9;
    private ControlProgresoTask contProgreso;
    protected Button[] array = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        setModoInmersivo();
        mostrarDialogo();

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvFase = (TextView) findViewById(R.id.tvFase);
        boton1 = (Button) findViewById(R.id.boton1);
        array[0] = boton1;
        boton2 = (Button) findViewById(R.id.boton2);
        array[1] = boton2;
        boton3 = (Button) findViewById(R.id.boton3);
        array[2] = boton3;
        boton4 = (Button) findViewById(R.id.boton4);
        array[3] = boton4;
        boton5 = (Button) findViewById(R.id.boton5);
        array[4] = boton5;
        boton6 = (Button) findViewById(R.id.boton6);
        array[5] = boton6;
        boton7 = (Button) findViewById(R.id.boton7);
        array[6] = boton7;
        boton8 = (Button) findViewById(R.id.boton8);
        array[7] = boton8;
        boton9 = (Button) findViewById(R.id.boton9);
        array[8] = boton9;
        proBar = (ProgressBar) findViewById(R.id.barraProgreso);

        pintarBoton();
    }

    public void pintarBoton() {

        try {
            int boton = (int) (Math.random() * 10 + 0);
            array[boton].setBackgroundTintList(getResources().getColorStateList(R.color.colorAccent));

        } catch (Exception e) {
            pintarBoton();
        }
    }

    public void revisar(View v) {
        if (v.getBackgroundTintList() == getResources().getColorStateList(R.color.colorAccent)) {
            v.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
            pintarBoton();

            contProgreso.cancel(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setModoInmersivo();
    }

    public void mostrarDialogo() {
        DialogoNombreNivel miDialogo = DialogoNombreNivel.newInstance();
        miDialogo.setCancelable(false);
        miDialogo.show(getSupportFragmentManager(), "Dialogo Nombre Nivel");
    }

    @Override
    public void onAceptarDialogo(String nombre, int velocidad) {
        partidaAcabada = false;
        this.velocidad = velocidad;
        progreso = 0;
        //contadorBotones=1;
        numFase = 1;
        //Modo inversivo
        setModoInmersivo();
        //Si hemos mandad un nommbre lo ponemos
        if (!nombre.trim().isEmpty()) {
            tvNombre.setText(nombre);
        }
        tvFase.setText(getString(R.string.fase, String.valueOf(numFase)));
        //Iniciamos la tarea asincrona y la ejecutamos
        contProgreso = new ControlProgresoTask();
        contProgreso.execute(this.velocidad, progreso);
    }

    private class ControlProgresoTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... integers) {//entran param salen resultado
            while (progreso <= 100) {
                SystemClock.sleep(integers[0]);
                contProgreso.publishProgress(progreso);
                progreso++;
                if (isCancelled()) break;
            }
            return progreso;
        }

        @Override
        protected void onProgressUpdate(Integer... integers) {//Progreso
            super.onProgressUpdate(integers);
            proBar.setProgress(integers[0]);

        }

        @Override
        protected void onPostExecute(Integer integer) {//resultado
            super.onPostExecute(integer);
            partidaAcabada = true;
            new AlertDialog.Builder(JuegoActivity.this)
                    .setTitle(R.string.fin)
                    .setMessage(R.string.perder)
                    .setPositiveButton(R.string.continuar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            mostrarDialogo();

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
        protected void onCancelled(Integer integer) {
            super.onCancelled();

            contProgreso = new ControlProgresoTask();
            //numerarBotones(desordenarBotones());
            tvFase.setText(getString(R.string.fase, String.valueOf(++numFase)));
            //contadorBotones = 1;
            progreso = 0;
            proBar.setProgress(0);
            velocidad = velocidad - 5 <= 0 ? 1 : velocidad - 5;
            contProgreso.execute(velocidad, progreso);

        }
    }
}
