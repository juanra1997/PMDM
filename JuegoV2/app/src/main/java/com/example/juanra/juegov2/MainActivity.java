package com.example.juanra.juegov2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

//heredamos de BaseActivity para modo inmersivo
public class MainActivity extends BaseActivity implements View.OnClickListener {


    private ImageView ivIns, ivJugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // recupero los imageview
        ivIns=(ImageView)findViewById(R.id.ivInstrucciones);
        ivJugar=(ImageView)findViewById(R.id.ivJugar);

        //Modoinmersivo pantalla completa (esta en BaseActivity)
        setModoInmersivo();
        //Ponemos los listeners
        ivIns.setOnClickListener(this);
        ivJugar.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v.getId()==ivIns.getId()){
            //hemos pulsado en instrucciones
            new AlertDialog.Builder(this)
                    .setTitle(R.string.como_jugar)
                    .setMessage(R.string.explicacion)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setModoInmersivo();
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
        if(v.getId()==ivJugar.getId()){
            //hemos pulsado en Jugar
            Intent i=new Intent(this, JuegoActivity.class);
            startActivity(i);
        }
    }

    /*@Override
    protected void onRestart() {
        super.onRestart();
        setModoInmersivo();
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        setModoInmersivo();
    }

}
