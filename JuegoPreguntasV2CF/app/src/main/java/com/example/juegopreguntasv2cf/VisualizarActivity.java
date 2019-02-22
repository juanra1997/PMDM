package com.example.juegopreguntasv2cf;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;*/

public class VisualizarActivity extends AppCompatActivity {

    static long preguntas = 0;
    long contador;
    static TextView cantidad, identificador,  numero, autor;
    static EditText preg, res1, res2, res3, res4, resc;
    /*FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cont = database.getReference("cont");
    DatabaseReference pregunta = database.getReference("Preguntas");*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        contador = 1;

        numero = (TextView) findViewById(R.id.numero);
        cantidad = (TextView) findViewById(R.id.cantidad);
        identificador = (TextView) findViewById(R.id.identificador);
        preg = (EditText) findViewById(R.id.pregunta);
        res1 = (EditText) findViewById(R.id.respuesta1);
        res2 = (EditText) findViewById(R.id.respuesta2);
        res3 = (EditText) findViewById(R.id.respuesta3);
        res4 = (EditText) findViewById(R.id.respuesta4);
        resc = (EditText) findViewById(R.id.respuestacorrecta);
        autor = (TextView) findViewById(R.id.autor);

    }

    @Override
    protected void onStart() {
        super.onStart();
        /*cont.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long value = dataSnapshot.getValue(Long.class);

                preguntas = value;

                cantidad.setText(String.valueOf(preguntas));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });*/
    }

    public void modificarPregunta(View v){

        if (preg.getText().toString().isEmpty() || autor.getText().toString().isEmpty() || res1.getText().toString().isEmpty() || res2.getText().toString().isEmpty() || res3.getText().toString().isEmpty() || res4.getText().toString().isEmpty() || resc.getText().toString().isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }else{

            String p = preg.getText().toString();
            String resp1 = res1.getText().toString();
            String resp2 = res2.getText().toString();
            String resp3 = res3.getText().toString();
            String resp4 = res4.getText().toString();
            String respc = resc.getText().toString();
            String a = autor.getText().toString();
            String ref = identificador.getText().toString();

            Pregunta preguntamodificar=new Pregunta(p, resp1, resp2, resp3, resp4, respc, a);

            //pregunta.child(ref).setValue(preguntamodificar);

            if (isOnlineNet()) {
                Toast.makeText(this, "Se ha guardado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Se ha producido un error con la conexion", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void cargarPregunta(View v) {

        /*FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference cont = database.getReference("cont");*/

        /*cont.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long value = dataSnapshot.getValue(Long.class);

                preguntas = value;

                cantidad.setText(String.valueOf(preguntas));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });*/

        //final DatabaseReference pregunta = database.getReference("Preguntas").child("Pregunta" + contador);

        /*if (preguntas != 0 && preguntas != -1) {

            pregunta.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Pregunta value = dataSnapshot.getValue(Pregunta.class);
                    identificador.setText(pregunta.getKey());
                    preg.setText(value.getPregunta());
                    res1.setText(value.getResp1());
                    res2.setText(value.getResp2());
                    res3.setText(value.getResp3());
                    res4.setText(value.getResp4());
                    resc.setText(value.getRespCorrecta());
                    autor.setText(value.getAutor());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            numero.setText(String.valueOf(contador));

            if (contador < preguntas) {
                contador++;
            } else {
                contador = 1;
            }

        } else {
            Toast.makeText(this, "Se ha recuperado el numero de preguntas", Toast.LENGTH_SHORT).show();
        }*/
    }

    public Boolean isOnlineNet() {

        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
