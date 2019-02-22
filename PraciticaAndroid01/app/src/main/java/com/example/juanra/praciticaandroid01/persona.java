package com.example.juanra.praciticaandroid01;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class persona extends AppCompatActivity {

    ImageView pelo, ojos, corbata, piel;
    Intent i;
    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);

        datos=getIntent().getExtras();

        pelo=(ImageView) findViewById(R.id.pelo);
        ojos=(ImageView) findViewById(R.id.ojos);
        corbata=(ImageView) findViewById(R.id.corbata);
        piel=(ImageView) findViewById(R.id.piel);

        i=new Intent();


        switch (datos.getString(MainActivity.DPELO)){
            case "MORENO":
                pelo.setImageResource(R.drawable.pelomoreno);
                break;
            case "RUBIO":
                pelo.setImageResource(R.drawable.rubio);
                break;
            case "ROJO":
                pelo.setImageResource(R.drawable.pelirrojo);
                break;
        }

        switch (datos.getString(MainActivity.DOJOS)){
            case "AZULES":
                ojos.setImageResource(R.drawable.ojosazules);
                break;
            case "MARRONES":
                ojos.setImageResource(R.drawable.ojosmarrones);
                break;
            case "VERDES":
                ojos.setImageResource(R.drawable.ojosverdes);
                break;
        }

        switch (datos.getString(MainActivity.DPIEL)){
            case "BLANCA":
                piel.setImageResource(R.drawable.caucasico);
                break;
            case "MORENA":
                piel.setImageResource(R.drawable.moreno);
                break;
            case "NEGRA":
                piel.setImageResource(R.drawable.negro);
                break;
        }

        switch (datos.getString(MainActivity.DCORBATA)){
            case "AZUL":
                corbata.setImageResource(R.drawable.corbataazul);
                break;
            case "LILA":
                corbata.setImageResource(R.drawable.corbatalila);
                break;
            case "MARRON":
                corbata.setImageResource(R.drawable.corbatamarron);
                break;
        }

        //Toast.makeText(this, datos.getString(MainActivity.DCORBATA), Toast.LENGTH_SHORT).show();
        //finish();
    }





    @Override
    public void onBackPressed() {

        setResult(RESULT_OK, i);

        i.putExtra(MainActivity.DOJOS, datos.get(MainActivity.DOJOS).toString());

        finish();
        //super.onBackPressed();
    }

}
