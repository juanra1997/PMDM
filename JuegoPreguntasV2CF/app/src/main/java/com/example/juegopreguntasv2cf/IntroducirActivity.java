package com.example.juegopreguntasv2cf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

/*import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;*/

public class IntroducirActivity extends AppCompatActivity {
    Button boton;
    EditText nombre, r1, r2, r3, r4, pregunta;
    RadioButton c1, c2, c3, c4;
    RadioGroup rg;
    static long mivalor;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir);

        db = FirebaseFirestore.getInstance();
        boton = (Button) findViewById(R.id.boton);
        pregunta = (EditText) findViewById(R.id.pregunta);
        nombre = (EditText) findViewById(R.id.nombre);
        r1 = (EditText) findViewById(R.id.r1);
        r2 = (EditText) findViewById(R.id.r2);
        r3 = (EditText) findViewById(R.id.r3);
        r4 = (EditText) findViewById(R.id.r4);
        rg = (RadioGroup) findViewById(R.id.rg);
        c1 = (RadioButton) findViewById(R.id.c1);
        c2 = (RadioButton) findViewById(R.id.c2);
        c3 = (RadioButton) findViewById(R.id.c3);
        c4 = (RadioButton) findViewById(R.id.c4);

        Log.d("Prueba:", String.valueOf(db.collection("Grupo").document("Contador").collection("Valor")));
        /*FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference cont = database.getReference("cont");

        cont.addValueEventListener(*//*ValueEventListener milistener=*//*new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long value = dataSnapshot.getValue(Long.class);

                mivalor = value;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/

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

    public void guardarPreguntas(View v) {


        if (pregunta.getText().toString().isEmpty() || nombre.getText().toString().isEmpty() || r1.getText().toString().isEmpty() || r2.getText().toString().isEmpty() || r3.getText().toString().isEmpty() || r4.getText().toString().isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        } else if (!c1.isChecked() && !c2.isChecked() && !c3.isChecked() && !c4.isChecked()) {
            Toast.makeText(this, "Indica la respuesta correcta", Toast.LENGTH_SHORT).show();
        } else {

            String p = pregunta.getText().toString();
            String resp1 = r1.getText().toString();
            String resp2 = r2.getText().toString();
            String resp3 = r3.getText().toString();
            String resp4 = r4.getText().toString();
            String a = nombre.getText().toString();

            Pregunta preg = null;

            switch (rg.getCheckedRadioButtonId()) {
                case R.id.c1:
                    preg = new Pregunta(p, resp1, resp2, resp3, resp4, resp1, a);
                    break;
                case R.id.c2:
                    preg = new Pregunta(p, resp1, resp2, resp3, resp4, resp2, a);
                    break;
                case R.id.c3:
                    preg = new Pregunta(p, resp1, resp2, resp3, resp4, resp3, a);
                    break;
                case R.id.c4:
                    preg = new Pregunta(p, resp1, resp2, resp3, resp4, resp4, a);
                    break;
            }

            /*FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference cont = database.getReference("cont");

            cont.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    long value = dataSnapshot.getValue(Long.class);

                    //tv.setText(String.valueOf(value));


                    mivalor = value;

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    //Log.w(TAG, "Failed to read value.", error.toException());
                }
            });*/

            //mivalor++;

            /*DatabaseReference pregunta = database.getReference("Preguntas");
            pregunta.child("Pregunta" + mivalor).setValue(preg);*/

            //cont.setValue(mivalor);

            if (isOnlineNet()) {
                Toast.makeText(this, "Se ha guardado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Se ha producido un error con la conexion", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
