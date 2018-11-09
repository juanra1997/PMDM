package com.example.juanra.p15reproductordemusicajuanraulmelladogarcia;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton play, repetir;
    MediaPlayer mp;
    ImageView portada;

    int erepetir=2, posicion=0;

    //Crear un array para contener las canciones

    MediaPlayer vectormp[]=new MediaPlayer[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play=(ImageButton) findViewById(R.id.ibPlay);
        repetir=(ImageButton) findViewById(R.id.ibRepetir);
        portada=(ImageView) findViewById(R.id.imageView);
        vectormp[0]=MediaPlayer.create(this, R.raw.race);
        vectormp[1]=MediaPlayer.create(this, R.raw.race);
        vectormp[2]=MediaPlayer.create(this, R.raw.tea);

    }

    //metodo del boton play pause

    public void PlayPauseClick(View view){

        if(vectormp[posicion].isPlaying()){

            vectormp[posicion].pause();
            play.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();

        } else {

            vectormp[posicion].start();
            play.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this, "Reproduciendo", Toast.LENGTH_SHORT).show();
        }
    }

    //metodo del boton stop

    public void StopClick(View view){

        vectormp[posicion].stop();
        play.setBackgroundResource(R.drawable.reproducir);
        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
    }

    public void SiguienteClick(View view){

        if(posicion<(vectormp.length-1)){
            if(vectormp[posicion].isPlaying()){
                vectormp[posicion].stop();
                posicion++;
                vectormp[posicion].start();

            }else {

                posicion++;
            }

            if(posicion==0){
                portada.setImageResource(R.drawable.portada1);
            } else if(posicion==1){
                portada.setImageResource(R.drawable.portada2);
            } if(posicion==2){
                portada.setImageResource(R.drawable.portada3);
            }
        } else {
            Toast.makeText(this, "No hay mas pistas de audio", Toast.LENGTH_SHORT).show();
        }
    }

    public void AnteriorClick(View view){

        if(posicion>(vectormp.length-1)){
            if(vectormp[posicion].isPlaying()){
                vectormp[posicion].stop();
                posicion--;
                vectormp[posicion].start();

            }else {

                posicion--;
            }

            if(posicion==0){
                portada.setImageResource(R.drawable.portada1);
            } else if(posicion==1){
                portada.setImageResource(R.drawable.portada2);
            } if(posicion==2){
                portada.setImageResource(R.drawable.portada3);
            }
        } else {
            Toast.makeText(this, "No hay mas pistas de audio", Toast.LENGTH_SHORT).show();
        }
    }

    public void RepetirClick(View view){

        if(erepetir==1){//No repetir

        }
    }
}
