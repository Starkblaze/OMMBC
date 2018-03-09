package com.example.stark.ommbc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        final ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, list);
        prob.setAdapter(adapter);
    }
}
