package com.example.juanra.p30bluetoothjuanraulmelladogarcia;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    BluetoothAdapter bluetooth;
    ////////////////////////////////////////////////////////////////////////////////////////////////
     //////////////Variable estatica que devuelve el codigo de la Activity principal///////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private static final int REQUEST_ENABLE_BT=1;
    ArrayList array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista=(ListView)findViewById(R.id.lista);

        //Se obtiene la instancia de la clase BluetoothAdapter
        bluetooth=BluetoothAdapter.getDefaultAdapter();
        if(bluetooth==null){
            Toast.makeText(this, "Este dispositivo no tiene Bluetooth", Toast.LENGTH_SHORT).show();
        }else if(!bluetooth.isEnabled()){
            Intent i=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(i, REQUEST_ENABLE_BT);
        }
        //Se registra el broadcast receiver
        IntentFilter filter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(br, filter);


    }
    public void onClickEscanear(View v){

        //Comprobamos que el dispositivo no este escaneando
        if(bluetooth.isDiscovering()){
            bluetooth.cancelDiscovery();
        }
        //Ejecutamos la accion de escaneo
        bluetooth.startDiscovery();
        //Usando el layout lista adaptamos la informacion que nos devuelve el array, el cual contiene la lista de los dispositivos escaneados
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.lista);
        lista.setAdapter(adapter);
    }

    private final BroadcastReceiver br= new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                //Se ha encontrado un dispositivo
                //Obtenemos la información
                BluetoothDevice divice=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                array.add(divice.getName()+" - "+divice.getAddress());
            }
        }
    };
}
