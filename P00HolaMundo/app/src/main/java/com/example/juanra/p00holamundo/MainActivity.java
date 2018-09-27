package com.example.juanra.p00holamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "evento OnCreate", Toast.LENGTH_SHORT);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(this, "evento OnStart", Toast.LENGTH_SHORT);
    }
}
