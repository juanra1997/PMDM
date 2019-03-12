package com.example.juegopreguntas4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    //Esta clase carga una web y cuando vuelves a la activity, se cierra y vuelve a la principal

    WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        wb=(WebView) findViewById(R.id.mitwitter);

        wb.loadUrl("https://twitter.com/juanraul1997");

        finish();
    }
}
