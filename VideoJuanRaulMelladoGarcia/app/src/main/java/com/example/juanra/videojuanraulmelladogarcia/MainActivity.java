package com.example.juanra.videojuanraulmelladogarcia;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    MediaRecorder mr=null;
    MediaPlayer mp=null;
    String nombre=null;
    boolean grabando=false;
    Button grabar, parar, reproducir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grabar=(Button) findViewById(R.id.Grabar);
        parar=(Button) findViewById(R.id.Parar);
        reproducir=(Button) findViewById(R.id.Reproducir);
        nombre=Environment.getExternalStorageDirectory()+"/test.mp4";
        parar.setEnabled(false);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        if(mr==null){
            mr=new MediaRecorder();
            mr.setPreviewDisplay(holder.getSurface());
        }
        if(mp==null){
            mp=new MediaPlayer();
            mp.setDisplay(holder);

        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        mr.release();
        mp.release();
    }

    public void prepareRecorder(){

        mr.setAudioSource(MediaRecorder.AudioSource.MIC);
        mr.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mr.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mr.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mr.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
    }

    public void onClickGrabar(View view){

        reproducir.setEnabled(false);
        grabar.setEnabled(false);
        parar.setEnabled(true);
        prepareRecorder();
        mr.setOutputFile(nombre);
        try {
            mr.prepare();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        mr.start();
        grabando=true;
    }

    public void onClickParar(View view){

        parar.setEnabled(false);
        reproducir.setEnabled(true);
        grabar.setEnabled(true);
        if(grabando){
            grabando=false;
            mr.stop();
            mr.reset();
        }else if(mp.isPlaying()){
            mp.stop();
            mp.reset();
        }
    }

    public void onClickReproducir(View view){

        grabar.setEnabled(false);
        reproducir.setEnabled(false);
        parar.setEnabled(true);
        /*mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp) {
                grabar.setEnabled(true);
                reproducir.setEnabled(true);
                parar.setEnabled(false);
            }
        });*/
        try {
            mp.setDataSource(nombre);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
    }
}
