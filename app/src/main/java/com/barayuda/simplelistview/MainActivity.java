package com.barayuda.simplelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ListView lvItem;
    ArrayList<String> footballList;
    ArrayAdapter<String> adapter;
    private String[] footballClubs = new String[] {
            "Juventus", "Manchester United", "Liverpool",
            "Bayer Munchen", "Real Madrid", "Ajax Amsterdam",
            "Barcelona", "Manchester City", "Arsenal", "Chelsea"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Simple ListView");
        getSupportActionBar().setSubtitle("ArrayAdapter dan ArrayList");

        lvItem = (ListView)findViewById(R.id.lv_item);
        // simpan data array pada arraylist untuk mempermudah memanipulasi
        footballList = new ArrayList<String>(Arrays.asList(footballClubs));
        // simpan data arraylist pada adapter
        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, footballList);
        // tampilkan data pada adapter
        lvItem.setAdapter(adapter);

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Anda memilih: "+ footballList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_asc:
                footballList = new ArrayList<String>(Arrays.asList(footballClubs));
                Collections.sort(footballList);
                for(int i=0; i < footballList.size(); i++){
                    System.out.println(footballList.get(i));
                }
                adapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1, footballList);
                lvItem.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Data diurutkan ASC", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
