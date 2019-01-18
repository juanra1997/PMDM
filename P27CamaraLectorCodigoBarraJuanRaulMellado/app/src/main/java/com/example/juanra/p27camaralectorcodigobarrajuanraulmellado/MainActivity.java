package com.example.juanra.p27camaralectorcodigobarrajuanraulmellado;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1000);
        }
    }

    public void Escanear(View v){
        //Inicializar variable
        zXingScannerView=new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        //"Activa" el escaner de la aplicacion
        zXingScannerView.setResultHandler(this);
        //Inicia
        zXingScannerView.startCamera();
    }

    @Override
    //Se "ejecuta/activa" el escaneo
    public void handleResult(Result result) {
        MediaPlayer mp=MediaPlayer.create(this, R.raw.roadrunner);
        mp.start();
        //Creamos caja de dialogo
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //Titulo de la caja de texto
        builder.setTitle("Resultado");
        //Manejamos el resultado
        if(result.getText().equals("8480000725646")){//Si es pasion floral
            builder.setMessage("Enhorabuena, has escaneado la pasion floral");
            MediaPlayer mp2=MediaPlayer.create(this, R.raw.pasion);
            mp2.start();
        }else if(result.getText().contains("http")){//Si es una URL
            builder.setMessage("URL");
            //Prepara un intent para abrir el navegador con la URL
            Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(result.getText()));
            //Abre el navegador
            startActivity(i);
        }else{//Si otras cosas son escaneadas
            builder.setMessage(result.getText());
        }
        AlertDialog mensaje=builder.create();
        mensaje.show();
        //OBLIGATORIO
        //Para el escaner
        zXingScannerView.resumeCameraPreview(this);
        //Apaga la camara
        zXingScannerView.stopCamera();
        //Cambia el activity
        setContentView(R.layout.activity_main);
    }
}
