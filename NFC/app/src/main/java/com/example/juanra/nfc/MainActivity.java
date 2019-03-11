package com.example.juanra.nfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    NfcAdapter nfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            nfc = NfcAdapter.getDefaultAdapter(this);

        }catch (Exception e){

        }

        if(nfc==null){
            Toast.makeText(this,"Esta aplicacion necesita NFC", Toast.LENGTH_SHORT).show();
        }
        if(!nfc.isEnabled()){
            Toast.makeText(this,"Activa el NFC para usar esta aplicacion", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Toast.makeText(this, "Dispositivo NFC encontrado", Toast.LENGTH_SHORT).show();
        super.onNewIntent(intent);
        //Toast.makeText(this, "Dispositivo NFC encontrado", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        Intent intent=new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent=PendingIntent.getActivity(this, 0, intent, 0);
        IntentFilter[] intentfilter=new IntentFilter[]{};
        nfc.enableForegroundDispatch(this, pendingIntent, intentfilter, null);
        super.onResume();
    }

    @Override
    protected void onPause() {

        nfc.disableForegroundDispatch(this);

        super.onPause();
    }
}
