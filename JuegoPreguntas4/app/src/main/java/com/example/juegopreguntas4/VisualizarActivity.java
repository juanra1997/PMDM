package com.example.juegopreguntas4;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VisualizarActivity extends AppCompatActivity {

    //Creamos los objetos

    static long preguntas = 0;
    long contador;
    static TextView cantidad, identificador,  /*numero,*/ autor;
    static EditText preg, res1, res2, res3, res4/*, resc*/;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cont;// = database.getReference("cont");
    DatabaseReference pregunta, contrasenna;// = database.getReference("Preguntas");
    String grupo, pass, contra;
    RadioGroup rg;
    RadioButton c1, c2, c3, c4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        //Capturamos los objetos

        contador = 1;
        rg=(RadioGroup)findViewById(R.id.rg);
        grupo=getIntent().getExtras().getString("elgrupo");
        pass=getIntent().getExtras().getString("contra");
        cont = database.getReference(grupo).child("cont");
        pregunta = database.getReference(grupo).child("Preguntas");
        //numero = (TextView) findViewById(R.id.numero);
        cantidad = (TextView) findViewById(R.id.cantidad);
        identificador = (TextView) findViewById(R.id.identificador);
        preg = (EditText) findViewById(R.id.pregunta);
        res1 = (EditText) findViewById(R.id.r1);
        res2 = (EditText) findViewById(R.id.r2);
        res3 = (EditText) findViewById(R.id.r3);
        res4 = (EditText) findViewById(R.id.r4);
        //resc = (EditText) findViewById(R.id.rc);
        autor = (TextView) findViewById(R.id.autor);
        c1 = (RadioButton) findViewById(R.id.c1);
        c2 = (RadioButton) findViewById(R.id.c2);
        c3 = (RadioButton) findViewById(R.id.c3);
        c4 = (RadioButton) findViewById(R.id.c4);

    }

    @Override
    protected void onStart() {
        super.onStart();

        //Obtenemos la cantidad de preguntas que hay en la base de datos

        cont.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    long value = dataSnapshot.getValue(Long.class);

                    preguntas = value;

                    cantidad.setText(String.valueOf(preguntas));
                }catch (Exception e){
                    preguntas=0;
                    cantidad.setText("0");
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
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
    }

    //Este metodo te permite modificar una pregunta

    public void modificarPregunta(View v){

        if (contra == null || !contra.equals(pass)) {
            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();

        }else {

            if (preg.getText().toString().isEmpty() || autor.getText().toString().isEmpty() || res1.getText().toString().isEmpty() || res2.getText().toString().isEmpty() || res3.getText().toString().isEmpty() || res4.getText().toString().isEmpty())/* || resc.getText().toString().isEmpty())*/ {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            } else if (!c1.isChecked() && !c2.isChecked() && !c3.isChecked() && !c4.isChecked()) {
                Toast.makeText(this, "Indica la respuesta correcta", Toast.LENGTH_SHORT).show();
            } else {

                Pregunta preguntamodificar = new Pregunta();

                String p = preg.getText().toString();
                String resp1 = res1.getText().toString();
                String resp2 = res2.getText().toString();
                String resp3 = res3.getText().toString();
                String resp4 = res4.getText().toString();

                String a = autor.getText().toString();
                switch (rg.getCheckedRadioButtonId()) {
                    case R.id.c1:
                        preguntamodificar = new Pregunta(p, resp1, resp2, resp3, resp4, resp1, a);
                        break;
                    case R.id.c2:
                        preguntamodificar = new Pregunta(p, resp1, resp2, resp3, resp4, resp2, a);
                        break;
                    case R.id.c3:
                        preguntamodificar = new Pregunta(p, resp1, resp2, resp3, resp4, resp3, a);
                        break;
                    case R.id.c4:
                        preguntamodificar = new Pregunta(p, resp1, resp2, resp3, resp4, resp4, a);
                        break;
                }
                String ref = identificador.getText().toString();

                //Pregunta preguntamodificar=new Pregunta(p, resp1, resp2, resp3, resp4, respc, a);

                pregunta.child(ref).setValue(preguntamodificar);

                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


                if (networkInfo != null && networkInfo.isConnected()) {
                    Toast.makeText(this, "Se ha guardado correctamente", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Se ha producido un error con la conexion", Toast.LENGTH_SHORT).show();
                }

            }
        }

    }

    //Este metodo obtiene las preguntas una a una mediante el id de pregunta y las pone en los textviews

    public void cargarPregunta(View v) {

        if (contra == null || !contra.equals(pass)) {
            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();

        }else {

            cont.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {

                        long value = dataSnapshot.getValue(Long.class);

                        preguntas = value;

                        cantidad.setText(String.valueOf(preguntas));
                    } catch (Exception e) {
                        preguntas = 0;
                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                }
            });

            final DatabaseReference pregunta = database.getReference(grupo).child("Preguntas").child("Pregunta" + contador);

            if (preguntas != 0 && preguntas != -1) {

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
                        //resc.setText(value.getRespCorrecta());
                        if (value.getRespCorrecta().equals(value.getResp1())) {
                            rg.check(R.id.c1);
                        } else if (value.getRespCorrecta().equals(value.getResp2())) {
                            rg.check(R.id.c2);
                        } else if (value.getRespCorrecta().equals(value.getResp3())) {
                            rg.check(R.id.c3);
                        } else if (value.getRespCorrecta().equals(value.getResp4())) {
                            rg.check(R.id.c4);
                        }
                        autor.setText(value.getAutor());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //numero.setText(String.valueOf(contador));

                if (contador < preguntas) {
                    contador++;
                } else {
                    contador = 1;
                }

            } else {
                Toast.makeText(this, "Se ha recuperado el numero de preguntas", Toast.LENGTH_SHORT).show();
            }
        }
    }

}