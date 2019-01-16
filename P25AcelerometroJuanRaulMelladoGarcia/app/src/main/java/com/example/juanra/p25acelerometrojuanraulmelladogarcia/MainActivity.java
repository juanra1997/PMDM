package com.example.juanra.p25acelerometrojuanraulmelladogarcia;

import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager manager;
    private Sensor sensor;
    private TextView tvResultado;
    private Button btJugar;
    private static final float PUNTUACION = 1.123f;
    private SoundPool sp;
    private int rep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Elementos de la interfaz
        tvResultado=(TextView) findViewById(R.id.texto);
        btJugar=(Button) findViewById(R.id.boton);
        //sonido
        sp=new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        rep=sp.load(this, R.raw.grito, 1);
        //Sensores
        manager=(SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor==null){
            Toast.makeText(this, "Este dispositivo no tiene acelerometro", Toast.LENGTH_SHORT).show();
            btJugar.setEnabled(false);
        }
    }

    public void onJugarClick(View v){
        tvResultado.setText("DALE UN BUEN MENEO!!!! >:D");
        btJugar.setEnabled(false);
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //captura del valor
        //En el array event.values, values[0]-> ejex, values[1]-> ejey, values[2]-> ejez. Todos son valores de tipo float. Representan m/s
        int valor=Math.round(event.values[2]);
        //comprobacion del resultado
        if(valor>=15 || valor <=-15){
            //Formateo de la salida
            int puntuacion=(int)(valor*PUNTUACION);
            String resultado="";
            resultado+="la aceleración ha sido: "+valor+'\n';
            resultado+="tu puntuación es: "+puntuacion;

            tvResultado.setText(resultado);
            //Reproduccion del sonido
            sp.play(rep, 1, 1, 1, 0 , 0);

        }else if(valor>=13&&valor<15||valor<=-13&&valor>-15){

            tvResultado.setText("NO SEAS TIMIDO ¡¡¡MAS FUERTE!!!");

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
