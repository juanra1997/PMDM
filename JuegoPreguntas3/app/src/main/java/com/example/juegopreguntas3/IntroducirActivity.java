package com.example.juegopreguntas3;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Esta clase nos va a permitir subir preguntas

public class IntroducirActivity extends AppCompatActivity {

    //Creamos los objetos que vamos a utilizar

    Button boton;
    EditText autor, r1, r2, r3, r4, pregunta;
    String group;
    RadioButton c1, c2, c3, c4;
    RadioGroup rg;
    static long mivalor;
    SoundPool sp;
    int sonidoDeReproduccion;
    NfcAdapter nfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir);

        try {

            nfc = NfcAdapter.getDefaultAdapter(this);

        }catch (Exception e){

        }

        //Capturamos los objetos

        sp=new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonidoDeReproduccion=sp.load(this, R.raw.sonido, 1);
        group=getIntent().getExtras().getString("elgrupo");
        boton = (Button) findViewById(R.id.boton);
        pregunta = (EditText) findViewById(R.id.pregunta);
        autor = (EditText) findViewById(R.id.autor);
        r1 = (EditText) findViewById(R.id.r1);
        r2 = (EditText) findViewById(R.id.r2);
        r3 = (EditText) findViewById(R.id.r3);
        r4 = (EditText) findViewById(R.id.r4);
        rg = (RadioGroup) findViewById(R.id.rg);
        c1 = (RadioButton) findViewById(R.id.c1);
        c2 = (RadioButton) findViewById(R.id.c2);
        c3 = (RadioButton) findViewById(R.id.c3);
        c4 = (RadioButton) findViewById(R.id.c4);

        mivalor=0;

        //Creamos la referencia a firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference cont = database.getReference(group).child("cont");

        //Obtenemos de firebase la cantidad de preguntas que tenemos

        cont.addValueEventListener(/*ValueEventListener milistener=*/new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {

                    long value = dataSnapshot.getValue(Long.class);

                    mivalor = value;
                }catch (Exception e){
                    mivalor=0;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    //Metodo para guardar preguntas

    public void guardarPreguntas(View v) {

        //Comprobamos que no hay campos vacios

        if (pregunta.getText().toString().isEmpty() || autor.getText().toString().isEmpty() || r1.getText().toString().isEmpty() || r2.getText().toString().isEmpty() || r3.getText().toString().isEmpty() || r4.getText().toString().isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        } else if (!c1.isChecked() && !c2.isChecked() && !c3.isChecked() && !c4.isChecked()) {
            Toast.makeText(this, "Indica la respuesta correcta", Toast.LENGTH_SHORT).show();
        } else {

            //Creamos la pregunta

            String p = pregunta.getText().toString();
            String resp1 = r1.getText().toString();
            String resp2 = r2.getText().toString();
            String resp3 = r3.getText().toString();
            String resp4 = r4.getText().toString();
            String a = autor.getText().toString();
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

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference cont = database.getReference(group).child("cont");

            //Obtenemos la cantidad de preguntas que hay en la base de datos

            cont.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    try {
                        long value = dataSnapshot.getValue(Long.class);

                        //tv.setText(String.valueOf(value));


                        mivalor = value;
                    }catch (Exception e){
                        mivalor=0;
                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    //Log.w(TAG, "Failed to read value.", error.toException());
                }
            });

            mivalor++;

            DatabaseReference pregunta = database.getReference(group);

            //Guardamos la pregunta

            pregunta.child("Preguntas").child("Pregunta" + mivalor).setValue(preg);

            //Cambiamos la cantidad de preguntas de la base de datos

            cont.setValue(mivalor);


            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {


                Toast.makeText(this, "Se ha guardado correctamente", Toast.LENGTH_SHORT).show();
                sp.play(sonidoDeReproduccion, 1, 1, 1, 0, 0);

            } else {

                Toast.makeText(this, "Se ha producido un error con la conexion", Toast.LENGTH_SHORT).show();

            }

        }

    }

    //Para que nfc funcione

    @Override
    protected void onNewIntent(Intent intent) {
        guardarPreguntas(boton);
        super.onNewIntent(intent);
    }

    @Override
    protected void onPause() {
        try{
            nfc.disableForegroundDispatch(this);
        }catch (Exception e){

        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if(nfc!=null){
            Intent intent=new Intent(this, IntroducirActivity.class);
            intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
            PendingIntent pendingIntent=PendingIntent.getActivity(this, 0, intent, 0);
            IntentFilter[] intentfilter=new IntentFilter[]{};
            nfc.enableForegroundDispatch(this, pendingIntent, intentfilter, null);
        }
        super.onResume();
    }
}