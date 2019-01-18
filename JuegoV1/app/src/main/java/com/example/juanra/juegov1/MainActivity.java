package com.example.juanra.juegov1;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String FILAS="nFilas";
    public final static String COLUMNAS="nColumnas";
    public final static String ELEMENTOS="nElementos";
    public final static String VIBRAR="vibrar";
    public final static String SONAR="sonar";
    public final static String NUMEROS="numeros";
    public final static String TOTAL_CLICKS="totalClicks";
    public final static int REQUEST_CODE=-1;

    private SeekBar sbFilas, sbColumnas, sbElementos;
    private CheckBox ckSonar, ckVibrar;
    private RadioButton rbNumeros, rbColores;
    private TextView tvFilas, tvColumnas, tvElementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbFilas=(SeekBar) findViewById(R.id.sbFilas);
        sbColumnas=(SeekBar) findViewById(R.id.sbColumnas);
        sbElementos=(SeekBar) findViewById(R.id.sbNumEle);

        ckSonar=(CheckBox) findViewById(R.id.ckVibrar);
        ckVibrar=(CheckBox) findViewById(R.id.ckVibrar);

        rbNumeros=(RadioButton) findViewById(R.id.rbNumeros);
        rbColores=(RadioButton) findViewById(R.id.rbColores);

        tvColumnas=(TextView) findViewById(R.id.txtColumnas);
        tvFilas=(TextView) findViewById(R.id.txtFilas);
        tvElementos=(TextView) findViewById(R.id.txtElementos);

        sbFilas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                actualizaFilas(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbColumnas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                actualizaColumnas(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbElementos.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                actualizaElementos(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void actualizaFilas(int p){
        tvFilas.setText(""+(p+3));
    }

    public void actualizaColumnas(int p){
        tvColumnas.setText(""+(p+3));
    }

    public void actualizaElementos(int p){
        tvElementos.setText(""+(p+2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Item_ayuda:
                mostrarAyuda();
                break;
            case R.id.Item_about:
                mostrarAbout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void mostrarAyuda(){
        Intent i=new Intent(this, Ayuda.class);
        startActivity(i);
    }

    public void mostrarAbout(){
        Intent i=new Intent(this, About.class);
        startActivity(i);
    }

    public void jugar(View v){
        Intent i=new Intent(this, JuegoActivity.class);
        Bundle datos=new Bundle();
        int filas, columnas, elementos, vibrar, sonar, numeros;
        filas=sbFilas.getProgress()+3;
        columnas=sbColumnas.getProgress()+3;
        elementos=sbElementos.getProgress()+2;
        sonar=ckSonar.isChecked()?1:0;
        vibrar=ckVibrar.isChecked()?1:0;
        numeros=rbNumeros.isChecked()?1:0;

        datos.putInt(FILAS, filas);
        datos.putInt(COLUMNAS, columnas);
        datos.putInt(ELEMENTOS, elementos);
        datos.putInt(SONAR, sonar);
        datos.putInt(VIBRAR, vibrar);
        datos.putInt(NUMEROS, numeros);

        i.putExtras(datos);

        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK){
            int totalCliks=data.getIntExtra(TOTAL_CLICKS, 0);
            Toast.makeText(this, String.format("Finalizaste el juego con un total de %d pulsaciones", totalCliks), Toast.LENGTH_SHORT).show();
        }
        if(requestCode==REQUEST_CODE && resultCode==RESULT_CANCELED){
            Toast.makeText(this, "El juego se canceló", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
