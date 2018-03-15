package com.example.stark.ommbc;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Problema_General extends AppCompatActivity {

    int fav = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problema__general);
        final int numero = getIntent().getIntExtra("Numero", 0);
        final String text = getIntent().getStringExtra("Texto");
        TextView p = (TextView)findViewById(R.id.problema);
        TextView espacio_texto = (TextView)findViewById(R.id.espacio_texto);
        espacio_texto.setText(text);
        FloatingActionButton help = (FloatingActionButton)findViewById(R.id.help);
        FloatingActionButton send = (FloatingActionButton)findViewById(R.id.send);
        final FloatingActionButton favorite = (FloatingActionButton)findViewById(R.id.favorite);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fav==0) {
                    favorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite1));
                    fav=1;
                }
                else {
                    favorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite0));
                    fav=0;
                }
            }
        });
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
