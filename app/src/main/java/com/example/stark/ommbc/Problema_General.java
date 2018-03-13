package com.example.stark.ommbc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Problema_General extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problema__general);
        final String nombre = getIntent().getStringExtra("Nombre");
        TextView p = (TextView)findViewById(R.id.problema);
        Button ayuda = (Button)findViewById(R.id.button_ayuda);
        ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"correo@ejemplo.mx"});
                i.putExtra(Intent.EXTRA_SUBJECT, nombre);
                i.putExtra(Intent.EXTRA_TEXT   , "Necesito ayuda con el "+nombre);
                try {
                    startActivity(Intent.createChooser(i, "Enviando Correo"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Problema_General.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        p.setText(nombre);
    }
}
