package com.example.blog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomePage extends AppCompatActivity {
    ListView listView;
    String title,content,h;
    ArrayList<String> arrayList;
   EditText contents;
   Date date;


    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        listView = findViewById(R.id.list_item);
        arrayList = new ArrayList();
        arrayList.add("Title 1");
        arrayList.add("Title 2");
        arrayList.add("Title 3");
        ArrayAdapter string = new ArrayAdapter(HomePage.this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(string);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intents=new Intent(HomePage.this,Blogg.class);
                startActivity(intents);
                Toast.makeText(HomePage.this, "your blog", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onAddClicked(View view)
    {
        Toast.makeText(HomePage.this, "New Title Created", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomePage.this, NewBlog.class);
        startActivity(intent);
        finish();
    }
}
