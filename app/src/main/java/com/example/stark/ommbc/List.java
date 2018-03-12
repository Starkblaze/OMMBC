package com.example.stark.ommbc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0; i < 25 ; i++){
            list.add("Problema "+(i+1));
        }
        ListView prob = (ListView)findViewById(R.id.problemas);
        //TextView nprob = (TextView)findViewById(R.id.nproblemas);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, list);
        prob.setAdapter(adapter);
        prob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast = Toast.makeText(getApplicationContext(), "Problema "+(i+1), Toast.LENGTH_SHORT);
                toast.show();
                Intent pro = new Intent(List.this,Problema_General.class);
                pro.putExtra("Nombre","Texto del problema "+(i+1));
                startActivity(pro);
            }
        });
       // nprob.setText(list.size()+" problemas");
    }
}
