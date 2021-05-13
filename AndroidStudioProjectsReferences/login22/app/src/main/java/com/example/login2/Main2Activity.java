package com.example.login2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ArrayList<String> arrayList1,arrayList;
    Spinner spinner;
    ListView vegtables,click;
    String message;
    Bundle message2;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        vegtables=findViewById(R.id.veg);
        spinner=findViewById(R.id.spinner);
        textView=findViewById(R.id.welcome);
        arrayList1=new ArrayList();
        arrayList1.add("Vegetable");
        arrayList1.add("Fruits");
        arrayList1.add("Animals");
        Bundle message2=new Bundle();
        final ArrayAdapter<String> string = new ArrayAdapter(Main2Activity.this,android.R.layout.simple_dropdown_item_1line,arrayList1);
        //string.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(string);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (arrayList1.get(position).equals("Vegetable")) {
                    arrayList = new ArrayList();
                    arrayList.add("Onion");
                    arrayList.add("Potato");
                    final ArrayAdapter adapter = new ArrayAdapter(Main2Activity.this, android.R.layout.simple_list_item_1, arrayList);
                    vegtables.setAdapter(adapter);
                }
                else if (arrayList1.get(position).equals("Fruits")) {
                    arrayList = new ArrayList();
                    arrayList.add("Banana");
                    arrayList.add("Apple");

                    final ArrayAdapter adapter = new ArrayAdapter(Main2Activity.this, android.R.layout.simple_list_item_1, arrayList);
                    vegtables.setAdapter(adapter);
                }else {  arrayList = new ArrayList();
                    arrayList.add("Dog");
                    arrayList.add("Cat");
                    final ArrayAdapter adapter = new ArrayAdapter(Main2Activity.this, android.R.layout.simple_list_item_1, arrayList);
                    vegtables.setAdapter(adapter);

                }
//
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Main2Activity.this, "select a item", Toast.LENGTH_SHORT).show();

            }
        });


//        arrayList=new ArrayList();
//        arrayList.add("onion");
//        arrayList.add("potato");
//        final ArrayAdapter adapter = new ArrayAdapter(Main2Activity.this,android.R.layout.simple_list_item_1,arrayList);
//        vegtables.setAdapter(adapter);
//        vegtables.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Main2Activity.this,arrayList.get(position), Toast.LENGTH_SHORT).show();
//
//            }

        message = (String) getIntent().getExtras().get("parcel_name");
        textView.setText("WELCOME  "+message);
//                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        vegtables.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(Main2Activity.this,arrayList.get(position), Toast.LENGTH_SHORT).show();
                if (arrayList.get(position).equals("Onion")) {

                    View v = getLayoutInflater().inflate(R.layout.onion, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                    builder.setView(v);
                    builder.create().show();
                }else if (arrayList.get(position).equals("Potato")) {

                    View v = getLayoutInflater().inflate(R.layout.potato, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                    builder.setView(v);
                    builder.create().show();
                }else if (arrayList.get(position).equals("Apple")) {

                    View v = getLayoutInflater().inflate(R.layout.apple, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                    builder.setView(v);
                    builder.create().show();
                }else if (arrayList.get(position).equals("Banana")) {

                    View v = getLayoutInflater().inflate(R.layout.banana, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                    builder.setView(v);
                    builder.create().show();
                }else if (arrayList.get(position).equals("Cat")) {

                    View v = getLayoutInflater().inflate(R.layout.cat, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                    builder.setView(v);
                    builder.create().show();
                }else{

                    View v = getLayoutInflater().inflate(R.layout.dog, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                    builder.setView(v);
                    builder.create().show();
                }
            }

        });

    }

    public void launchSecondActivity(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(Main2Activity.this);
        alert.setCancelable(false);
        alert.setTitle("EXIT😡😱");
        alert.setMessage("Are you sure to Exit??🙄");
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               // Toast.makeText(Main2Activity.this, "then exit", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Main2Activity.this, "continue", Toast.LENGTH_SHORT).show();
            }

        });
        alert.create().show();
    }
}
