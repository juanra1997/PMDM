package com.example.juanra.p13scrollviewjuanraulmelladogarcia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Procedimiento que consulta el id del objeto qu eha generado el evento  y segun sea mustra un mensaje

    public void SeleccionFrutaClick(View view){

        //dentro del objeto view tenemos la propiedad id que identifica de forma unequivoca cada objeto de nuestro activity

        switch (view.getId()){
            case R.id.cereza:
                Toast.makeText(this, "Cereza", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fresa:
                Toast.makeText(this, "Fresa", Toast.LENGTH_SHORT).show();
                break;
            case R.id.kiwi:
                Toast.makeText(this, "Kiwi", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pera:
                Toast.makeText(this, "Pera", Toast.LENGTH_SHORT).show();
                break;
            case R.id.platano:
                Toast.makeText(this, "Platano", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remolacha:
                Toast.makeText(this, "Remolacha", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sandia:
                Toast.makeText(this, "Sandia", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tomate:
                Toast.makeText(this, "Tomate", Toast.LENGTH_SHORT).show();
                break;
            case R.id.uva:
                Toast.makeText(this, "Uva", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pinna:
                Toast.makeText(this, "Piña", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
