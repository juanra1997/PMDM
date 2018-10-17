package com.example.juanra.p06pasoinfdosactivitysjuanraulmelladogarcia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SegundoActivity extends AppCompatActivity {

    private TextView tvInfom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        tvInfom=(TextView) findViewById(R.id.tvInfo);

        //Recupero en una variable la informacion de la variable del primer activitu
        String dato=getIntent().getStringExtra("Nombre");
        tvInfom.setText("Hola "+dato);
    }

    public void retroceder(View view){
        //Intent i=new Intent(this, MainActivity.class);
        //startActivity(i);
        finish();
    }
}
