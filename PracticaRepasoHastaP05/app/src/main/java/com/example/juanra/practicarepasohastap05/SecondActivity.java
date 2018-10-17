package com.example.juanra.practicarepasohastap05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private ListView lvLista;
    private TextView tvInfo;
    private String semana[]={"Lunes","Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
    private RadioButton rb1, rb2, rb3, rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lvLista=(ListView) findViewById(R.id.lvlista);
        tvInfo=(TextView) findViewById(R.id.tvInfo);
        rb1=(RadioButton) findViewById(R.id.radioButton2);
        rb2=(RadioButton) findViewById(R.id.radioButton3);
        rb3=(RadioButton) findViewById(R.id.radioButton4);
        rb4=(RadioButton) findViewById(R.id.radioButton);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.coloreslistview, semana);
        lvLista.setAdapter(adapter);

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Programamos el evento click de los items de la lista
                //position es la posicion que ocupa el item seleccionado
                if(rb1.isChecked()) {
                    tvInfo.setText("Hoy es " + semana[position] + " de Enero");
                }else if(rb2.isChecked()){
                    tvInfo.setText("Hoy es " + semana[position] + " de Febrero");
                }else if(rb3.isChecked()){
                    tvInfo.setText("Hoy es " + semana[position] + " de Marzo");
                }else if(rb4.isChecked()){
                    tvInfo.setText("Hoy es " + semana[position] + " de Abril");
                }
            }
        });
    }

    public void anterior(View view){

        Intent anterior=new Intent(this, MainActivity.class);
        startActivity(anterior);
    }
}
