package com.example.juanra.p16grabadorasonidosjuanraulmelladogarcia;

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
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageButton ibGrabarm, ibReproducirm;
    MediaRecorder mr;
    String nombreGrabacion=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibGrabarm=(ImageButton) findViewById(R.id.ibGrabar);
        ibReproducirm=(ImageButton) findViewById(R.id.ibReproducir);

        //pedir los permisos necesarios para poder usar microfono y guardar archivo
        //Modificar manifest

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO)!=PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }
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
        }
    }

    public void playClick(View view){

        MediaPlayer mp=new MediaPlayer();

        try{

            mp.setDataSource(nombreGrabacion);
            mp.prepare();

        } catch(IOException e){

        }
        mp.start();
        Toast.makeText(this, "Reproduciendo", Toast.LENGTH_SHORT).show();
    }
}
