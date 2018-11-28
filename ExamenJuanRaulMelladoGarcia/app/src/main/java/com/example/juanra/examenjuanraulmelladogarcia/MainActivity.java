package com.example.juanra.examenjuanraulmelladogarcia;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText etContra, etUser;
    //File contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etContra = (EditText) findViewById(R.id.etContra);
        etUser = (EditText) findViewById(R.id.etUser);

    }

    public void iniciarSesion(View view){

        if(etUser.getText().toString().equals("usu")&&etContra.getText().toString().equals("123")){

            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this, exa2juanraulmellado.class);
            i.putExtra("usuario", etUser.getText().toString());
            startActivity(i);

        }else{
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
        }
    }
}
