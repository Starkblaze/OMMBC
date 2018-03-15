package com.example.stark.ommbc;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
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
        final int numero = getIntent().getIntExtra("Numero", 0);
        final String text = getIntent().getStringExtra("Texto");
        TextView p = (TextView)findViewById(R.id.problema);
        FloatingActionButton help = (FloatingActionButton)findViewById(R.id.help);
        FloatingActionButton send = (FloatingActionButton)findViewById(R.id.send);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","correo@ejemplo.mx", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Ayuda problema "+numero);
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Necesito ayuda con el problema "+numero);
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Inviar Solucion", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        p.setText("Problema "+numero);
    }
}
