package com.example.juanra.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private ListView lvLista;
    private RadioButton rbMoreno, rbRubio, rbCastanno, rbPelirrojo, rbCalvo;
    RadioGroup grupo;
    private EditText nombre, telefono;
    private ImageButton volver, guardar;
    SharedPreferences preferences;
    private TextView vista;

    String dato;

    private String dias[]={"Lunes","Martes", "Miercoles", "Jueves", "Viernes"};

    //final String KEY_SAVED_RADIO_BUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dato=getIntent().getStringExtra("usuario");

        //String dato=getIntent().getStringExtra("usuario");
        //String dato1=dato+"nombre";

        preferences=getSharedPreferences("datos", Context.MODE_PRIVATE);

        lvLista=(ListView) findViewById(R.id.lvDias);
        /* private RadioButton rbMoreno, rbRubio, rbCastanno, rbPelirrojo, rbCalvo;
        private EditText nombre, telefono;*/
        rbMoreno=(RadioButton) findViewById(R.id.moreno);
        rbRubio=(RadioButton) findViewById(R.id.rubio);
        rbCastanno=(RadioButton) findViewById(R.id.castanno);
        rbPelirrojo=(RadioButton) findViewById(R.id.pelirrojo);
        rbCalvo=(RadioButton) findViewById(R.id.calvo);
        grupo=(RadioGroup) findViewById(R.id.GPelos);
        vista=(TextView) findViewById(R.id.tvvista);


        //SharedPreferences.Editor ObjetoEditor=preferences.edit();


        nombre=(EditText) findViewById(R.id.etNombre);
        telefono=(EditText) findViewById(R.id.etTelefono);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.my_list_view, dias);
        lvLista.setAdapter(adapter);

        //int savedRadioIndex = preferences.getInt(KEY_SAVED_RADIO_BUTTON_INDEX, 0);
        /*RadioButton savedCheckedRadioButton = (RadioButton)grupo.getChildAt(savedRadioIndex);
        if(!savedCheckedRadioButton.isChecked())
            savedCheckedRadioButton.setChecked(true);*/

        nombre.setText(preferences.getString(dato+"nombre", ""));
        telefono.setText(preferences.getString(dato+"telefono", ""));
        /*rbMoreno.setChecked(preferences.getBoolean(dato+"moreno", true));
        rbRubio.setChecked(preferences.getBoolean(dato+"rubio", false));
        rbCastanno.setChecked(preferences.getBoolean(dato+"castanno", false));
        rbPelirrojo.setChecked(preferences.getBoolean(dato+"pelirrojo", false));
        rbCalvo.setChecked(preferences.getBoolean(dato+"calvo", false));*/
        /*if(rbMoreno.getId()==preferences.getInt(dato+"rb", 0)){
            rbMoreno.setChecked(true);
        }
        if(rbRubio.getId()==preferences.getInt(dato+"rb", 0)){
            rbRubio.setChecked(true);
        }
        if(rbPelirrojo.getId()==preferences.getInt(dato+"rb", 0)){
            rbPelirrojo.setChecked(true);
        }

        if(rbCastanno.getId()==preferences.getInt(dato+"rb", 0)){
            rbCastanno.setChecked(true);
        }
        if(rbCalvo.getId()==preferences.getInt(dato+"rb", 0)){
            rbCalvo.setChecked(true);
        }*/
        //rbMoreno.setChecked(preferences.getBoolean(dato+"moreno", true));
        grupo.check(preferences.getInt(dato+"rb", rbMoreno.getId()));
        //grupo.set
        //grupo.check(preferences.getInt(dato+"rb", 0));
        //lvLista.setSelection(preferences.getInt(dato+"lista", 0));
        //vista.setText(preferences.getString(dato+"lista", "Lunes"));
        vista.setText(lvLista.getItemAtPosition(preferences.getInt(dato+"lista", 0)).toString());
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Programamos el evento click de los items de la lista
                //position es la posicion que ocupa el item seleccionado
                vista.setText(lvLista.getItemAtPosition(position).toString());
                SharedPreferences.Editor ObjetoEditor=preferences.edit();
                ObjetoEditor.putInt(dato+"lista", position);
                ObjetoEditor.commit();
            }
        });
        //vista.setText(lvLista.getSelectedItem().toString());
        //, rbRubio, rbCastanno, rbPelirrojo, rbCalvo;


        //Toast.makeText(this, dato1, Toast.LENGTH_LONG).show();



    }

    public void guardar(View view){

        SharedPreferences preferences=getSharedPreferences("datos", Context.MODE_PRIVATE);
        //Como vamos a editar las SharedPreferences creamos un objeto editor
        SharedPreferences.Editor ObjetoEditor=preferences.edit();

        ObjetoEditor.putString(dato+"nombre", nombre.getText().toString());
        ObjetoEditor.putString(dato+"telefono", telefono.getText().toString());
        /*ObjetoEditor.putBoolean(dato+"moreno", rbMoreno.isChecked());
        ObjetoEditor.putBoolean(dato+"rubio", rbMoreno.isChecked());
        ObjetoEditor.putBoolean(dato+"castanno", rbMoreno.isChecked());
        ObjetoEditor.putBoolean(dato+"pelirrojo", rbMoreno.isChecked());
        ObjetoEditor.putBoolean(dato+"calvo", rbMoreno.isChecked());*/
        ObjetoEditor.putInt(dato+"rb", grupo.getCheckedRadioButtonId());
        //ObjetoEditor.putString(dato+"lista", lvLista.getItemAtPosition(lvLista.getSelectedItemPosition()).toString());
        //ObjetoEditor.putInt(dato+"")
        //Confirmar que hemos guardado los cambios
        ObjetoEditor.commit();
        Toast.makeText(this, "Guardado", Toast.LENGTH_LONG).show();
    }

    public void volver(View view){

        finish();
    }

    public void actvty3(View view){

        Intent i = new Intent(this, Main3Activity.class);
        i.putExtra("usuario", dato);
        startActivity(i);
    }
}