package com.example.juanra.p001calculadora;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvres, tvTitulo;
    private EditText etop1, etop2;
    private RadioButton rbEspanol, rbAleman, rbKurdo, rbJapo;
    private Button /*bIdioma,*//* bSumar, bRestar*/ bCalcular;
    private Spinner spOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Capturo los objetos del activity
        etop1=(EditText) findViewById(R.id.etOperando1);
        etop2=(EditText) findViewById(R.id.etOperando2);
        tvres=(TextView) findViewById(R.id.tvResultado);
        rbEspanol=(RadioButton) findViewById(R.id.rbEspa);
        rbAleman=(RadioButton) findViewById(R.id.rbAle);
        rbKurdo=(RadioButton) findViewById(R.id.rbKurdo);
        rbJapo=(RadioButton) findViewById(R.id.rbJapo);
        tvTitulo=(TextView) findViewById(R.id.tvTitulo);
        //bIdioma=(Button) findViewById(R.id.bCambiarIdioma);
        //bSumar=(Button) findViewById(R.id.bSumar);
        //bRestar=(Button) findViewById(R.id.bRestar);
        spOpciones=(Spinner) findViewById(R.id.spOpciones);

        //------------------------------------------------------------------------------------------
        String[] opciones={"sumar","restar","multiplicar","dividir"};
        ArrayAdapter<String> adapter=new ArrayAdapter(this, R.layout.spinner_opciones, opciones);
        spOpciones.setAdapter(adapter);
        //------------------------------------------------------------------------------------------

        bCalcular=(Button) findViewById(R.id.bCalcular);
    }

    public void sumar(View view){

        if(etop1.length()>0&&etop2.length()>0) {

            //Sumar los valores de los operandos
            int num1, num2, suma;
            String valor1, valor2, cadRes;

            valor1 = etop1.getText().toString();
            valor2 = etop2.getText().toString();

            num1 = Integer.parseInt(valor1);
            num2 = Integer.parseInt(valor2);

            suma = num1 + num2;
            cadRes = String.valueOf(suma);
            tvres.setText(cadRes);
            //tvres.setText(suma);
        }
    }

    public void restar (View view) {

        if(etop1.length()>0&&etop2.length()>0) {
            //Sumar los valores de los operandos
            int num1, num2, suma;
            String valor1, valor2, cadRes;

            valor1 = etop1.getText().toString();
            valor2 = etop2.getText().toString();

            num1 = Integer.parseInt(valor1);
            num2 = Integer.parseInt(valor2);

            suma = num1 - num2;
            cadRes = String.valueOf(suma);
            tvres.setText(cadRes);
            //tvres.setText(suma);
        }
    }

    public void multiplicar (View view) {

        if(etop1.length()>0&&etop2.length()>0) {
            //Sumar los valores de los operandos
            int num1, num2, suma;
            String valor1, valor2, cadRes;

            valor1 = etop1.getText().toString();
            valor2 = etop2.getText().toString();

            num1 = Integer.parseInt(valor1);
            num2 = Integer.parseInt(valor2);

            suma = num1 * num2;
            cadRes = String.valueOf(suma);
            tvres.setText(cadRes);
            //tvres.setText(suma);
        }
    }

    public void dividir (View view) {

        if(etop1.length()>0&&etop2.length()>0) {
            //Sumar los valores de los operandos
            int num1, num2, suma;
            String valor1, valor2, cadRes;

            valor1 = etop1.getText().toString();
            valor2 = etop2.getText().toString();

            num1 = Integer.parseInt(valor1);
            num2 = Integer.parseInt(valor2);

            suma = num1 / num2;
            cadRes = String.valueOf(suma);
            tvres.setText(cadRes);
            //tvres.setText(suma);
        }
    }

    public void traducir(View view){

        if(rbEspanol.isChecked()==true){

            tvTitulo.setText(getResources().getString(R.string.sETitulo));
            //bIdioma.setText(getResources().getString(R.string.sEIdioma));
            etop1.setHint(getResources().getString(R.string.sEOperando1));
            etop2.setHint(getResources().getString(R.string.sEOperando2));
            tvres.setText(getResources().getString(R.string.sEResultado));
            //bIdioma.setText(getResources().getString(R.string.sEIdioma));
            //bSumar.setText(getResources().getString(R.string.sESumar));
            //bRestar.setText(getResources().getString(R.string.sERestar));
            bCalcular.setText(getResources().getString(R.string.sECalcular));
        } else if(rbAleman.isChecked()==true){

            tvTitulo.setText(getResources().getString(R.string.sATitulo));
            //bIdioma.setText(getResources().getString(R.string.sAIdioma));
            etop1.setHint(getResources().getString(R.string.sAOperando1));
            etop2.setHint(getResources().getString(R.string.sAOperando2));
            tvres.setText(getResources().getString(R.string.sAResultado));
            //bIdioma.setText(getResources().getString(R.string.sAIdioma));
            //bSumar.setText(getResources().getString(R.string.sASumar));
            //bRestar.setText(getResources().getString(R.string.sARestar));
            bCalcular.setText(getResources().getString(R.string.sACalcular));
        } else if(rbKurdo.isChecked()==true){

            tvTitulo.setText(getResources().getString(R.string.sKTitulo));
            etop1.setHint(getResources().getString(R.string.sKOperando1));
            etop2.setHint(getResources().getString(R.string.sKOperando2));
            tvres.setText(getResources().getString(R.string.sKResultado));
            //bSumar.setText(getResources().getString(R.string.sKSumar));
            //bRestar.setText(getResources().getString(R.string.sKRestar));
            bCalcular.setText(getResources().getString(R.string.sKCalcular));
        } else if(rbJapo.isChecked()==true){

            tvTitulo.setText(getResources().getString(R.string.sJTitulo));
            etop1.setHint(getResources().getString(R.string.sJOperando1));
            etop2.setHint(getResources().getString(R.string.sJOperando2));
            tvres.setText(getResources().getString(R.string.sJResultado));
            //bSumar.setText(getResources().getString(R.string.sJSumar));
            //bRestar.setText(getResources().getString(R.string.sJRestar));
            bCalcular.setText(getResources().getString(R.string.sJCalcular));
        }

    }

    public void CogerOpcion(View view){

        String seleccion=spOpciones.getSelectedItem().toString();

        if(seleccion.equals("sumar")){

            try {
                sumar(view);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        } else if(seleccion.equals("restar")){

            try {
                restar(view);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        } else if(seleccion.equals("dividir")){

            try {
                dividir(view);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }else if(seleccion.equals("multiplicar")){

            try {
                multiplicar(view);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
