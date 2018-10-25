package com.example.juanra.practicarepasopeluqeriajuanraulmelladogarcia;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private ListView lvLista;
    private RadioButton rbMoreno, rbRubio, rbCastanno, rbPelirrojo, rbCalvo;
    private EditText nombre, telefono;
    private ImageButton volver, guardar;
    SharedPreferences preferences;

    private String dias[]={"Lunes","Martes", "Miercoles", "Jueves", "Viernes"};

    String dato=getIntent().getStringExtra("usuario");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //String dato=getIntent().getStringExtra("usuario");
        //String dato1=dato+"nombre";

        //preferences=getSharedPreferences("datos", Context.MODE_PRIVATE);

        //lvLista=(ListView) findViewById(R.id.lvDias);
        /* private RadioButton rbMoreno, rbRubio, rbCastanno, rbPelirrojo, rbCalvo;
    private EditText nombre, telefono;*/
        /*rbMoreno=(RadioButton) findViewById(R.id.moreno);
        rbRubio=(RadioButton) findViewById(R.id.rubio);
        rbCastanno=(RadioButton) findViewById(R.id.castanno);
        rbPelirrojo=(RadioButton) findViewById(R.id.pelirrojo);
        rbCalvo=(RadioButton) findViewById(R.id.calvo);

        nombre=(EditText) findViewById(R.id.etNombre);
        telefono=(EditText) findViewById(R.id.etTelefono);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.mi_list_view, dias);
        lvLista.setAdapter(adapter);*/

        /*nombre.setText(preferences.getString(dato+"nombre", ""));
        telefono.setText(preferences.getString(dato+"telefono", ""));
        rbMoreno.setChecked(preferences.getBoolean(dato+"moreno", true));
        rbRubio.setChecked(preferences.getBoolean(dato+"rubio", true));
        rbCastanno.setChecked(preferences.getBoolean(dato+"castanno", true));
        rbPelirrojo.setChecked(preferences.getBoolean(dato+"pelirrojo", true));
        rbCalvo.setChecked(preferences.getBoolean(dato+"calvo", true));
        lvLista.setSelection(preferences.getInt(dato+"lista", 1));*/
        //, rbRubio, rbCastanno, rbPelirrojo, rbCalvo;


        //Toast.makeText(this, dato1, Toast.LENGTH_LONG).show();



    }

    /*public void guardar(View view){

        SharedPreferences preferences=getSharedPreferences("datos", Context.MODE_PRIVATE);
        //Como vamos a editar las SharedPreferences creamos un objeto editor
        SharedPreferences.Editor ObjetoEditor=preferences.edit();
        //Escribir en el campo email
        ObjetoEditor.putString(dato+"nombre", nombre.getText().toString());
        ObjetoEditor.putString(dato+"telefono", telefono.getText().toString());
        ObjetoEditor.putBoolean(dato+"moreno", rbMoreno.isChecked());
        ObjetoEditor.putBoolean(dato+"rubio", rbMoreno.isChecked());
        ObjetoEditor.putBoolean(dato+"castanno", rbMoreno.isChecked());
        ObjetoEditor.putBoolean(dato+"pelirrojo", rbMoreno.isChecked());
        ObjetoEditor.putBoolean(dato+"calvo", rbMoreno.isChecked());
        ObjetoEditor.putInt(dato+"lista", lvLista.getSelectedItemPosition());
        //Confirmar que hemos guardado los cambios
        ObjetoEditor.commit();
    }*/

    public void volver(View view){

        finish();
    }
}
