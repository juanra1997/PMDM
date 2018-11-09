package com.example.juanra.p14reproductoraudiojuanraulmelladogarcia;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button play;

    SoundPool sp;
    int sonidoDeReproduccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play=(Button) findViewById(R.id.bSoundPool);
        sp=new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonidoDeReproduccion=sp.load(this, R.raw.sonidocorto, 1);
    }

    public void soundpoolclick(View view){

        sp.play(sonidoDeReproduccion, 1, 1, 1, 30, 0);
    }

    public void audioMediaPlayerClick(View view){

        //crear un objeto
        //MediaPlayer(Contexto, sonico con R.raw)
        //llamar evento start

        MediaPlayer mp=MediaPlayer.create(this, R.raw.sonidolargo);
        mp.start();

    }
}

//SoundCloud es para apis menores de la 21