package com.example.internship;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class TakeATTENDENCE extends AppCompatActivity implements
        View.OnClickListener {
    Button btn;
    RadioGroup rbg;
    RadioButton select;
    Button btnDatePicker;
    EditText txtDate;
    private int mYear, mMonth, mDay;
    String checkValue, dateValue, name, course,usn;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendence);
        //----------------------------------------------------
        btn = findViewById(R.id.button1);
        btnDatePicker = (Button) findViewById(R.id.btn_date);
        txtDate = (EditText) findViewById(R.id.in_date);
        btnDatePicker.setOnClickListener(this);
        rbg = findViewById(R.id.radioGroup1);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //-----------------------------------------------------

        //-----------------------------------------------------
//        btn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                int selected = rbg.getCheckedRadioButtonId();
//                select = findViewById(selected);
//                checkValue = select.getText().toString();
//                Toast.makeText(getApplicationContext(), checkValue, Toast.LENGTH_SHORT).show();
//
//            }
//        });
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

    public void onCheckAttendanceClicked(View view) {
        int selected = rbg.getCheckedRadioButtonId();
        select = findViewById(selected);
        checkValue = select.getText().toString();
        Toast.makeText(getApplicationContext(), checkValue, Toast.LENGTH_SHORT).show();
        name = (String) getIntent().getExtras().get("parcel_name");
        course = (String) getIntent().getExtras().get("parcel_names");
        usn = (String) getIntent().getExtras().get("parcel_namee");
        Toast.makeText(this, course, Toast.LENGTH_SHORT).show();
        checkValue = select.getText().toString();
        dateValue = txtDate.getText().toString();
        if (checkValue.equals("PRESENT")) {
            databaseReference = FirebaseDatabase.getInstance().getReference("Attendance").child(course).child(dateValue).child(usn);
            databaseReference.child("Name").setValue(name).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(TakeATTENDENCE.this, "Data Added Succesfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TakeATTENDENCE.this, "Try Again Later", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
