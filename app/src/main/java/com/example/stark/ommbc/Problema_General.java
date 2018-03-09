package com.example.stark.ommbc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Problema_General extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problema__general);
        String nombre = getIntent().getStringExtra("Nombre");
        TextView p = (TextView)findViewById(R.id.problema);
        p.setText(nombre);
    }
}
