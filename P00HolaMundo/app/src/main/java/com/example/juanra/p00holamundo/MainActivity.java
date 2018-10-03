package com.example.juanra.p00holamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "evento OnCreate", Toast.LENGTH_SHORT).show();

        int quimica=6;
        int fisica=6;
        int matematicas=6;
        int media=0;
        media=(quimica+fisica+matematicas)/3;

        if(media>=6){
            Toast.makeText(this, "Aprobado", Toast.LENGTH_LONG).show();
        } else if(media<5){
            Toast.makeText(this, "Suspenso", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this, "Cinco por los pelos", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(this, "evento OnStart", Toast.LENGTH_SHORT).show();

        int quimica=6;
        int fisica=6;
        int matematicas=6;
        int media=0;
        media=(quimica+fisica+matematicas)/3;

        if(media>=6){
            Toast.makeText(this, "Aprobado", Toast.LENGTH_LONG).show();
        } else if(media<5){
            Toast.makeText(this, "Suspenso", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this, "Cinco por los pelos", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(this, "evento onResume", Toast.LENGTH_SHORT).show();

        int quimica=5;
        int fisica=5;
        int matematicas=5;
        int media=0;
        media=(quimica+fisica+matematicas)/3;

        if(media>=6){
            Toast.makeText(this, "Aprobado", Toast.LENGTH_LONG).show();
        } else if(media<5){
            Toast.makeText(this, "Suspenso", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this, "Cinco por los pelos", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(this, "evento onPause", Toast.LENGTH_SHORT).show();
    }

     @Override
    protected void onStop(){
        super.onStop();
        Toast.makeText(this, "evento onStop", Toast.LENGTH_SHORT).show();
     }

     @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "evento onDestroy", Toast.LENGTH_SHORT).show();
     }
}
