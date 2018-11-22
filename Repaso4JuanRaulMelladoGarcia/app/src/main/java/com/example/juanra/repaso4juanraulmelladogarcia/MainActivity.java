package com.example.juanra.repaso4juanraulmelladogarcia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageButton ibGrabarm, ibReproducirm, unomas;
    MediaRecorder mr;
    String nombreGrabacion=null;
    LinearLayout ly;
    boolean annadido=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibGrabarm=(ImageButton) findViewById(R.id.ibGrabar);
        ibReproducirm=(ImageButton) findViewById(R.id.ibReproducir);
        ly=(LinearLayout) findViewById(R.id.LinearLayout);
        unomas=new ImageButton(this);
        unomas.setBackgroundResource(R.drawable.grabacion);
        unomas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioMediaPlayerClick5(v);
            }
        });
        if(!(Environment.getExternalStorageDirectory().getAbsolutePath()+"/miGrabacion.mp3").isEmpty()){
            annadirBoton();
            annadido=true;
        }

        //pedir los permisos necesarios para poder usar microfono y guardar archivo
        //Modificar manifest

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO)!=PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }
    }

    public void annadirBoton(){

        ly.addView(unomas);

    }

    public void GrabarClick(View view){

        if(mr ==null){

            //Componemos el nombre completo ocn su ruta del archivo de salida
            nombreGrabacion=Environment.getExternalStorageDirectory().getAbsolutePath()+"/miGrabacion.mp3";

            //Instanciar el mr
            mr =new MediaRecorder();
            mr.setAudioSource(MediaRecorder.AudioSource.MIC);
            //Formato de salida
            mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            //Codificador de salida
            mr.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            //Asignamos el nombre del fichero de salida al objeto mr
            mr.setOutputFile(nombreGrabacion);
            //Empezar a grabar audio
            try{

                mr.prepare();
                //Comenzamos la grabacion
                mr.start();
            }catch (IOException e){

            }

            ibGrabarm.setBackgroundResource(R.drawable.rec);
            Toast.makeText(this, "Grabando", Toast.LENGTH_SHORT).show();
        }else{

            //Existe y esta configurado luego lo que quiere el usuario es parar la grabacion
            mr.stop();
            mr.release();
            mr=null;
            ibGrabarm.setBackgroundResource(R.drawable.stop_rec);
            Toast.makeText(this, "Grabacion finalizada", Toast.LENGTH_SHORT).show();
            if(!annadido) {
                annadirBoton();
            }
        }
    }

    public void playClick(View view){

        MediaPlayer mp=new MediaPlayer();

        try{

            mp.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/miGrabacion.mp3");
            mp.prepare();

            mp.start();
            Toast.makeText(this, "Reproduciendo", Toast.LENGTH_SHORT).show();
        } catch(IOException e){

        }

    }
    public void audioMediaPlayerClick1(View view){

        //crear un objeto
        //MediaPlayer(Contexto, sonico con R.raw)
        //llamar evento start

        MediaPlayer mp=MediaPlayer.create(this, R.raw.anenemy);
        mp.start();

    }
    public void audioMediaPlayerClick2(View view){

        //crear un objeto
        //MediaPlayer(Contexto, sonico con R.raw)
        //llamar evento start

        MediaPlayer mp=MediaPlayer.create(this, R.raw.doh);
        mp.start();

    }
    public void audioMediaPlayerClick3(View view){

        //crear un objeto
        //MediaPlayer(Contexto, sonico con R.raw)
        //llamar evento start

        MediaPlayer mp=MediaPlayer.create(this, R.raw.noseoye);
        mp.start();

    }
    public void audioMediaPlayerClick4(View view){

        //crear un objeto
        //MediaPlayer(Contexto, sonico con R.raw)
        //llamar evento start

        MediaPlayer mp=MediaPlayer.create(this, R.raw.votosafavor);
        mp.start();

    }
    public void audioMediaPlayerClick5(View view){

        //crear un objeto
        //MediaPlayer(Contexto, sonico con R.raw)
        //llamar evento start

        MediaPlayer mp=new MediaPlayer();

        try{

            mp.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/miGrabacion.mp3");
            mp.prepare();

        } catch(IOException e){

        }
        mp.start();
    }
}

