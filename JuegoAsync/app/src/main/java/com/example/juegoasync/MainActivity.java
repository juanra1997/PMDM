package com.example.juegoasync;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setModoInmersivo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setModoInmersivo();
    }

    public void instrucciones(View v) {
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

    public void jugar(View v) {
        Intent i = new Intent(this, JuegoActivity.class);
        startActivity(i);
    }
}
