package com.example.juanra.praciticaandroid01;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static int REQUEST_CODE=-1;
    public RadioButton peloMoreno, peloRubio, peloRojo, pielBlanca, pielMorena, pielNegra, ojosMarrones, ojosVerdes, ojosAzules, corbataMarron, corbataLila, corbataAzul;
    public RadioGroup rgPelo, rgPiel, rgOjos, rgCorbata;
    public String PELO, PIEL, OJOS, CORBATA;
    public static String DPELO, DPIEL, DOJOS, DCORBATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peloMoreno=(RadioButton)findViewById(R.id.peloMoreno);
        peloRubio=(RadioButton)findViewById(R.id.peloRubio);
        peloRojo=(RadioButton)findViewById(R.id.peloRojo);
        pielBlanca=(RadioButton)findViewById(R.id.pielBlanca);
        pielMorena=(RadioButton)findViewById(R.id.pielMorena);
        pielNegra=(RadioButton)findViewById(R.id.pielNegra);
        ojosMarrones=(RadioButton)findViewById(R.id.ojosMarrones);
        ojosVerdes=(RadioButton)findViewById(R.id.ojosVerdes);
        ojosAzules=(RadioButton)findViewById(R.id.ojosAzules);
        corbataMarron=(RadioButton)findViewById(R.id.corbataMarron);
        corbataLila=(RadioButton)findViewById(R.id.corbataLila);
        corbataAzul=(RadioButton)findViewById(R.id.corbataAzul);

        rgPelo=(RadioGroup)findViewById(R.id.rgpelo);
        rgPiel=(RadioGroup)findViewById(R.id.rgPiel);
        rgCorbata=(RadioGroup)findViewById(R.id.rgCorbata);
        rgOjos=(RadioGroup)findViewById(R.id.rgOjos);

        DPELO="PELO";
        DPIEL="PIEL";
        DOJOS="OJOS";
        DCORBATA="CORBATA";
    }

    public void monigote(View v){

        switch (rgPelo.getCheckedRadioButtonId()){
            case R.id.peloMoreno:
                PELO="MORENO";
                break;
            case R.id.peloRubio:
                PELO="RUBIO";
                break;
            case R.id.peloRojo:
                PELO="ROJO";
                break;
        }

        switch (rgPiel.getCheckedRadioButtonId()){
            case R.id.pielBlanca:
                PIEL="BLANCA";
                break;
            case R.id.pielMorena:
                PIEL="MORENA";
                break;
            case R.id.pielNegra:
                PIEL="NEGRA";
                break;
        }

        switch (rgOjos.getCheckedRadioButtonId()){
            case R.id.ojosAzules:
                OJOS="AZULES";
                break;
            case R.id.ojosMarrones:
                OJOS="MARRONES";
                break;
            case R.id.ojosVerdes:
                OJOS="VERDES";
                break;
        }

        switch (rgCorbata.getCheckedRadioButtonId()){
            case R.id.corbataAzul:
                CORBATA="AZUL";
                break;
            case R.id.corbataLila:
                CORBATA="LILA";
                break;
            case R.id.corbataMarron:
                CORBATA="MARRON";
                break;
        }



        Intent i=new Intent(this, persona.class);
        Bundle datos=new Bundle();

        datos.putString(DPELO, PELO);
        datos.putString(DCORBATA, CORBATA);
        datos.putString(DOJOS, OJOS);
        datos.putString(DPIEL, PIEL);

        i.putExtras(datos);

        startActivityForResult(i, REQUEST_CODE);


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        //No se por qué no entra aqui
        //Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show();
        if(requestCode==REQUEST_CODE&&resultCode==RESULT_OK){
            Toast.makeText(this, "Gracias por usar esta app", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
