package com.example.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase basededatos=FirebaseDatabase.getInstance();
    DatabaseReference myRef=basededatos.getReference("TablaAgenda");

    EditText dni, nombre, ap1, ap2, edad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dni=(EditText) findViewById(R.id.dni);
        nombre=(EditText) findViewById(R.id.nombre);
        ap1=(EditText) findViewById(R.id.ap1);
        ap2=(EditText) findViewById(R.id.ap2);
        edad=(EditText) findViewById(R.id.edad);
    }

    public void bGuardarClick(View v){
//*************************************************************************************************/
        //GUARDAR DATOS

        /*FirebaseDatabase basededatos=FirebaseDatabase.getInstance();
        DatabaseReference myRef=basededatos.getReference("TablaAgenda");*/
        DatabaseReference rdni=myRef.child(dni.getText().toString());

        rdni.child("Nombre").setValue(nombre.getText().toString());
        rdni.child("Apellido1").setValue(ap1.getText().toString());
        rdni.child("Apellido2").setValue(ap2.getText().toString());
        rdni.child("Edad").setValue(edad.getText().toString());
//*************************************************************************************************/
        /*FirebaseDatabase basededatos=FirebaseDatabase.getInstance();
        DatabaseReference myRef=basededatos.getReference("cont");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int value=dataSnapshot.getValue(Integer.class);
                Log.d("Valor", value+"");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


    }

    public void bModificarClick(View v){


    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
//ESCRIBE EN TABÑA AGENDA LOS DATOS DE UNA PERSONA ORGANIZADOS POR DNI