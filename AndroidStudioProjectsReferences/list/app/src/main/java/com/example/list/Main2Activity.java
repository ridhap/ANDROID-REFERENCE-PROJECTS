package com.example.list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ArrayList<String> arrayList1;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        spinner=findViewById(R.id.spinner);
        arrayList1=new ArrayList();
        arrayList1.add("Banglore");
        arrayList1.add("chandighar");
        arrayList1.add("manglore");
        arrayList1.add("chennai");
        arrayList1.add("kolkata");
        arrayList1.add("mumbai");
        arrayList1.add("delhi");
        final ArrayAdapter string = new ArrayAdapter(Main2Activity.this,android.R.layout.simple_dropdown_item_1line,arrayList1);
        spinner.setAdapter(string);
    }
}
