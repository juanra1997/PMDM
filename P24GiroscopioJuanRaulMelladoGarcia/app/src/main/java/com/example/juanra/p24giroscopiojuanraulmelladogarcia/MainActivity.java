package com.example.juanra.p24giroscopiojuanraulmelladogarcia;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor giroscopio;
    SensorEventListener escucharGiroscopio;
    ImageView ivImagen;
    TextView tvGrados;
    Switch swInterruptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivImagen=(ImageView) findViewById(R.id.ivImagen);
        tvGrados=(TextView) findViewById(R.id.tvGrados);
        swInterruptor=(Switch) findViewById(R.id.swInterruptor);

        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        giroscopio=sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        if(giroscopio==null){
            Toast.makeText(this, "Este dispositivo no dispone de giroscopio", Toast.LENGTH_SHORT).show();
            swInterruptor.setEnabled(false);
        }
        escucharGiroscopio=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                //Obtenemos inclinacion del dispositivo en radianes
                //proporcionados directamente por el giroscopio
                float[] rotationMatrix=new float[16];
                SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);

                float[] remapped=new float[16];
                SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_X, SensorManager.AXIS_Z, remapped);

                float[] orientations=new float[3];
                SensorManager.getOrientation(remapped, orientations);
                //Convertimos la inclinacion en radianes a grados
                for(int i=0; i<3; i++){
                    orientations[i]=(float)(Math.toDegrees(orientations[i]));
                }

                //Rotamos imagen en sentido opuesto al numero de grados
                ivImagen.setRotation(orientations[2]*-1);
                tvGrados.setText((int)orientations[2]+"º");

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

    }

    public void interruptorClick(View v){
        if(swInterruptor.isChecked()){
            sensorManager.registerListener(escucharGiroscopio, giroscopio, SensorManager.SENSOR_DELAY_GAME);
        }else{
            sensorManager.unregisterListener(escucharGiroscopio);
            ivImagen.setRotation(0);
            tvGrados.setText("0º");
        }
    }
}
