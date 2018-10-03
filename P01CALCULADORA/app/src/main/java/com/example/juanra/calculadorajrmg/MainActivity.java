package com.example.juanra.calculadorajrmg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvres;
    private EditText etop1, etop2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Capturo los objetos del activity
        etop1=(EditText) findViewById(R.id.etOperando1);
        etop2=(EditText) findViewById(R.id.etOperando2);
        tvres=(TextView) findViewById(R.id.tvResultado);
    }

    public void sumar(View view){

        //Sumar los valores de los operandos
        int num1, num2, suma;
        String valor1, valor2, cadRes;

        valor1=etop1.getText().toString();
        valor2=etop2.getText().toString();

        num1=Integer.parseInt(valor1);
        num2=Integer.parseInt(valor2);

        suma=num1+num2;
        //cadRes=String.valueOf(suma);
        //tvres.setText(cadRes);
        tvres.setText(suma);
    }
}
