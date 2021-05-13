package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {
    private EditText usn,name;
    String usnValue,nameValue,value;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        usn=findViewById(R.id.usn);
        name=findViewById(R.id.name);

        databaseReference= FirebaseDatabase.getInstance().getReference("");

    }

    public void pushdata(View view) {
        if(usn.getText().toString().equals(""))
        {
            usn.setError("Enter your USN");
            usn.requestFocus();
            return;

        }

        if(name.getText().toString().equals(""))
        {
            name.setError("Enter your Name");
            name.requestFocus();
            return;

        }
        usnValue=usn.getText().toString();
        nameValue=name.getText().toString();

        if (usnValue.contains(".")||usnValue.contains("/"))
        {
            usn.setError("Enter your USN");
            usn.requestFocus();
            return;
        }

        databaseReference.child(usnValue).setValue(nameValue).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Main2Activity.this, "Data Added Succesfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Main2Activity.this, "Try Again Later", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void onclickpopvalue(View view) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               value= dataSnapshot.getValue(String.class);
                Toast.makeText(Main2Activity.this,value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
