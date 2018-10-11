package com.example.juanra.p02listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class P02_ListView_Juan_Raul_Mellado_Garcia extends AppCompatActivity {

    private TextView tvInfo;
    private ListView lvLista;

    private String nombres[]={"Miguel","Julian", "Javier", "Alicia", "Angel", "Sergio", "Juan"};
    private String edades[]={"22", "20", "20", "33", "19", "18", "20"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p02__list_view__juan__raul__mellado__garcia);

        tvInfo=(TextView) findViewById(R.id.tvinfo);
        lvLista=(ListView) findViewById(R.id.lvlista);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.list_item_nombres, nombres);
        lvLista.setAdapter(adapter);

        //Hacer lo mismo en el spinner

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Programamos el evento click de los items de la lista
                //position es la posicion que ocupa el item seleccionado
                tvInfo.setText("La edad de "+lvLista.getItemAtPosition(position)+" es "+edades[position]+" años");
            }
        });

    }
}
