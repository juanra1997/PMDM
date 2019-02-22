package com.example.juanra.p29podometrojuanraulmelladogarcia;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class P29PodometroJuanRaulMelladoGarcia extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensor;

    EditText etPasosADar;
    Button bEmpezar, bRendirse;
    TextView tvPasos, tvObjetivo;
    ImageView ivPersona;
    SensorEventListener andar;
    int pasosActuales=0, objetivo=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p29_podometro_juan_raul_mellado_garcia);

        //Variables
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        etPasosADar = (EditText) findViewById(R.id.etPasosADar);
        bEmpezar = (Button) findViewById(R.id.bEmpezar);
        bRendirse= (Button) findViewById(R.id.bRendirse);
        tvPasos = (TextView) findViewById(R.id.tvNumPasos);
        tvObjetivo = (TextView) findViewById(R.id.tvNumObjetivo);
        ivPersona = (ImageView) findViewById(R.id.ivPersona);

        if(sensor==null){
            Toast.makeText(this, "Este dispositivo no cuenta con podometro", Toast.LENGTH_SHORT).show();
            bEmpezar.setEnabled(false);
        }

        andar = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                //Si hemos llegado nos termina y si no suma y sigue
                if(pasosActuales == objetivo){
                    terminar();
                }
                pasosActuales++;
                tvPasos.setText(pasosActuales+"");
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        };

    }

    public void terminar(){
        //Este metodo se da cuando el numero de pasos es igual al bojetivo
        sensorManager.unregisterListener(andar);
        Toast.makeText(this, "FELICICADES POR LA CAMINATA", Toast.LENGTH_LONG).show();

        bEmpezar.setEnabled(true);
        bRendirse.setEnabled(false);
    }

    public void rendirse(View v){
        //reiniciar
        sensorManager.unregisterListener(andar);
        Toast.makeText(this, "¿Ya te has cansado?", Toast.LENGTH_LONG).show();
        bRendirse.setEnabled(false);
        bEmpezar.setEnabled(true);
        tvObjetivo.setTextColor(Color.RED);
    }

    public void empezar(View v){
        //Reiniciamos los datos
        pasosActuales = 0;
        tvPasos.setText(0+"");
        bEmpezar.setEnabled(false);
        bRendirse.setEnabled(true);
        tvObjetivo.setTextColor(Color.BLACK);

        //Controlamos la excepción de que si no hay nada no lo pone
        if(etPasosADar.getText().toString().length() != 0){
            //Cogemos los pasos que hemos puesto arriba, los pasamos al objetivo y limpiamos arriba
            objetivo = Integer.parseInt(etPasosADar.getText().toString());
            tvObjetivo.setText(objetivo+"");
            etPasosADar.setText("");

            //Activamos el sensor y limitamos los pasos al objetivo
            //Lo segundo se hace dentro del EventListener
            sensorManager.registerListener(andar, sensor, SensorManager.SENSOR_DELAY_GAME);
        } else {
            Toast.makeText(this, "Introduce un objetivo arriba", Toast.LENGTH_LONG).show();
            bEmpezar.setEnabled(true);
            bRendirse.setEnabled(false);
        }

    }

}
