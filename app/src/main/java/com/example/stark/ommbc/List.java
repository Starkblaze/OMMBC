package com.example.stark.ommbc;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Problemas");
        setSupportActionBar(myToolbar);
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0; i < 25 ; i++){
            list.add("Problema "+(i+1));
        }
        ListView prob = (ListView)findViewById(R.id.problemas);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,R.layout.mylist, list);
        prob.setAdapter(adapter);
        prob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent pro = new Intent(List.this,Problema_General.class);
                pro.putExtra("Numero", (i+1));
                String test = "Test ";
                for(int j = 0 ; i < 450 ; i++) {
                    test=test+"test ";
                }
                pro.putExtra("Texto", test);
                startActivity(pro);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


}
