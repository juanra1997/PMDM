package com.example.juanra.examenjuanraulmelladogarcia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class exa2juanraulmellado extends AppCompatActivity {

    TextView etUser;
    MediaPlayer mp;
    WebView wb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exa2juanraulmellado);

        etUser=(TextView) findViewById(R.id.tvUsuario);
        wb=(WebView) findViewById(R.id.wb);

        Intent i=this.getIntent();

        etUser.setText(i.getStringExtra("usuario"));
    }

    public void fin(View view){

        try{
            if(mp.isPlaying()){
                mp.stop();
            }
        }catch (Exception e){

        }
        //Lo hago con finish ya que si lo hago con intent, al pulsar atras desde la activity principal,
        //volveria otra vez a la que supuestamente se ha cerrado
        finish();
    }

    public void rep1(View view){

        try{
            if(mp.isPlaying()){
                mp.stop();
            }
        }catch (Exception e){

        }


        mp=MediaPlayer.create(this, R.raw.canciondisco1);

        mp.start();
        wb.loadUrl("http://www.musicarelajante.es");
    }
    public void rep2(View view){

        try{
            if(mp.isPlaying()){
                mp.stop();
            }
        }catch (Exception e){

        }

        mp=MediaPlayer.create(this, R.raw.canciondisco2);

        mp.start();
        wb.loadUrl("https://www.freeaudiolibrary.com/es/");
    }
    public void rep3(View view){

        try{
            if(mp.isPlaying()){
                mp.stop();
            }
        }catch (Exception e){

        }
        mp=MediaPlayer.create(this, R.raw.canciondisco3);

        mp.start();
        wb.loadUrl("https://www.freemusicprojects.com/es/");
    }
}
