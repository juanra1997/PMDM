package com.example.juegopreguntas4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

//Esta clase nos va a permitir realizar el juego

public class JuegoActivity extends AppCompatActivity {

    //Creamos los objetos

    String grupo, pass, contra;
    Button resp1, resp2, resp3, resp4;
    TextView pregunta, cantidad;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference fpregunta, contrasenna;
    ArrayList<Pregunta> array;
    boolean normal;
    int contador, correctas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        //Capturamos los objetos

        contador=0;
        correctas=0;
        normal=true;
        array=new ArrayList<>();
        grupo=getIntent().getExtras().getString("elgrupo");
        pass=getIntent().getExtras().getString("contra");
        resp1=(Button) findViewById(R.id.bresp1);
        resp2=(Button) findViewById(R.id.bresp2);
        resp3=(Button) findViewById(R.id.bresp3);
        resp4=(Button) findViewById(R.id.bresp4);
        pregunta=(TextView) findViewById(R.id.txtPregunta);
        cantidad=(TextView) findViewById(R.id.numPregunta);

        //Obtenemos la referencia a firebase

        fpregunta = database.getReference(grupo).child("Preguntas");
        fpregunta.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Pregunta p = postSnapshot.getValue(Pregunta.class);

                    //Obtenemos las preguntas

                    array.add(p);
                    cantidad.setText(contador+" de "+array.size());
                    pregunta.setText("Pulsa un boton para empezar");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        contrasenna = database.getReference(grupo).child("contra");
        contrasenna.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contra=dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        cantidad.setText(String.valueOf(array.size()));

    }

    public void cargarPregunta(View v) {

        if (contra == null || !contra.equals(pass)) {
            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();

        }else {
            if (normal) {

                //Desordenamos las preguntas

                Collections.shuffle(array);
                normal = false;

            } else {

                //Si el boton que hemos presionado corresponde a la respuesta correcta, suma uno al contador de respuestas correctas

                if (((Button) v).getText().toString().equals(array.get(contador - 1).getRespCorrecta())) {

                    correctas++;
                }
            }
            if (contador < array.size()) {

                //Cargamos la siguiente pregunta

                pregunta.setText(array.get(contador).getPregunta());
                resp1.setText(array.get(contador).getResp1());
                resp2.setText(array.get(contador).getResp2());
                resp3.setText(array.get(contador).getResp3());
                resp4.setText(array.get(contador).getResp4());
                contador++;
                cantidad.setText(contador + " de " + array.size());
            } else {

                //Avisamos que hemos terminado de responder las preguntas y cargamos el ultimo activity

                Toast.makeText(this, "Has respondido a todas las preguntas", Toast.LENGTH_SHORT).show();
                //cantidad.setText("Tu cantidad de respuestas correctas es de: " + correctas);
                resp1.setEnabled(false);
                resp2.setEnabled(false);
                resp3.setEnabled(false);
                resp4.setEnabled(false);
                Bundle dato = new Bundle();
                dato.putString("resultado", String.valueOf(correctas));
                Intent i = new Intent(this, ResultadoActivity.class);
                i.putExtras(dato);
                startActivity(i);
                finish();
            }
        }
    }
}
