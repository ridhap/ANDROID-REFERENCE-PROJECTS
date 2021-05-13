package com.example.internship;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherView extends AppCompatActivity {
    DatabaseReference facultyDatabaseReference, studentDatabaseReference;
    String Name,usn;
    String message,studentmessage,course;
    ListView listView;
    ArrayList<String> studentNameList;
    Intent intent;
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view);
        listView = findViewById(R.id.list);
        studentNameList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(TeacherView.this, android.R.layout.simple_list_item_1, studentNameList);
        listView.setAdapter(arrayAdapter);
        //---------------------------------------------------------------------------------------------------------------------
        facultyDatabaseReference = FirebaseDatabase.getInstance().getReference("FacultyInfo");
        studentDatabaseReference = FirebaseDatabase.getInstance().getReference("StudentInfo");
        //----------------------------------------------------------------------------------------------------------------------
        message = (String) getIntent().getExtras().get("parcel_name");
        System.out.println("MESSAGE " + message);

        facultyDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        if (issue.child("Username").getValue().toString().equals(message)) {

                            course = issue.child("Course").getValue().toString();
                            System.out.println("MESSAGE " + course);


                            studentDatabaseReference.child(course).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                                    if (dataSnapshot2.exists()) {
//                                        System.out.println("MESSAGE "+dataSnapshot2.child("Prerana").child("Name").getValue(String.class) );
                                        for (DataSnapshot d2 : dataSnapshot2.getChildren()) {
                                            Name = d2.child("Name").getValue().toString();
                                            usn=d2.child("Usn").getValue().toString();
                                            System.out.println("MESSAGE "+Name);
                                            studentNameList.add(Name);
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
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                studentmessage=studentNameList.get(position);
                intent = new Intent(TeacherView.this,TakeATTENDENCE.class);
                Name = studentNameList.get(position);
                usn=studentNameList.get(position);
                intent.putExtra("parcel_names", course);
                intent.putExtra("parcel_name", Name);
                intent.putExtra("parcel_namee", usn);
                startActivity(intent);
                //Toast.makeText(TeacherView.this,studentNameList.get(position), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void onBackClicked(View view) {

        Intent intent = new Intent(TeacherView.this,FirstPage.class);
        startActivity(intent);

    }
}
