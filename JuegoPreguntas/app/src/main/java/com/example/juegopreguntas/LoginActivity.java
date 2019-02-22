package com.example.juegopreguntas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText e1, e2, e3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1 = (EditText) findViewById(R.id.p1);
        e2 = (EditText) findViewById(R.id.p2);
        e3 = (EditText) findViewById(R.id.p3);


    }

    public void entrar(View v) {

        if (!e1.getText().toString().equals("juanra3") && !e2.getText().toString().equals("23334373E") && !e3.getText().toString().equals("jrmg010203tmg")) {
            finish();
        } else {
            Intent i = new Intent(this, VisualizarActivity.class);
            startActivity(i);
        }
    }
}
