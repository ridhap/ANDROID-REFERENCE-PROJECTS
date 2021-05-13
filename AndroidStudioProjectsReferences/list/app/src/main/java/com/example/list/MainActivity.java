package com.example.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> arrayList;
    ListView listView;
    String message;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list_item);
        arrayList=new ArrayList();
        arrayList.add("banglore");
        arrayList.add("chandighar");
        arrayList.add("manglore");
        arrayList.add("chennai");
        arrayList.add("kolkata");
        arrayList.add("mumbai");
        arrayList.add("delhi");
        final ArrayAdapter string = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(string);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this,arrayList.get(position), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void clickme(View view) {
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        Toast.makeText(this, "hellooo", Toast.LENGTH_SHORT).show();
        intent.putExtra("parcel_name",message);
        startActivity(intent);
        finish();
    }
}
