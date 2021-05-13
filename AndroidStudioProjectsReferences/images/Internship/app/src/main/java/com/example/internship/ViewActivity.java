package com.example.internship;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class ViewActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseReference databaseReference;
    Button btn;
    Button btnDatePicker;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    EditText txtDate;
    public int mYear, mMonth, mDay;
    String  dateValue, courseValue,usn,name;
    ArrayList<String> studentNameList;
    Intent intent;
    SpinnerDialog spinnerDialog;
    EditText courseNameList,course;
    ArrayList<String> items=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        listView = findViewById(R.id.listView);
        studentNameList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(ViewActivity.this, android.R.layout.simple_list_item_1, studentNameList);
        listView.setAdapter(arrayAdapter);
        //----------------------------------------------------
        btn = findViewById(R.id.button1);
        btnDatePicker = (Button) findViewById(R.id.btn_date);
        txtDate = (EditText) findViewById(R.id.in_date);
        btnDatePicker.setOnClickListener(this);
        course=findViewById(R.id.courseForStudent);

        //----------------------------------------------------------------------------------------------------------------------------------
        courseNameList=findViewById(R.id.courseForStudent);
        items.add("Internet Of Things");
        items.add("Artificial Intelligence");
        items.add("Full Stack Web Development");
        items.add("Electric Vehicle Technology");
        items.add("IC Engine design and Management");
        items.add("Android Application Development");
        items.add("Cyber Security and Ethical Hacking");
        items.add("Virtual and Augmented Reality");


        spinnerDialog=new SpinnerDialog(ViewActivity.this,items,"Select or Search Course","Close");// With No Animation
        spinnerDialog=new SpinnerDialog(ViewActivity.this,items,"Select or Search course",R.style.DialogAnimations_SmileWindow,"Close");// With 	Animation

        spinnerDialog.setCancellable(true); // for cancellable
        spinnerDialog.setShowKeyboard(false);// for open keyboard by default


        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                Toast.makeText(ViewActivity.this, item, Toast.LENGTH_SHORT).show();
                courseNameList.setText(item );
            }
        });



    }
    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
    public void onShowClicked(View view) {
        spinnerDialog.showSpinerDialog();
    }

    public void onOkClicked(View view) {
        dateValue = txtDate.getText().toString();
        courseValue=course.getText().toString();
        System.out.println("MESSAGE "+dateValue );
        databaseReference = FirebaseDatabase.getInstance().getReference("Attendance").child(courseValue);
        databaseReference.child(dateValue).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                if (dataSnapshot2.exists()) {
//                                        System.out.println("MESSAGE "+dataSnapshot2.child("Prerana").child("Name").getValue(String.class) );
                    for (DataSnapshot d2 : dataSnapshot2.getChildren()) {
                        name = d2.child("Name").getValue().toString();
                        System.out.println("MESSAGE "+name);
                        studentNameList.add(name);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
