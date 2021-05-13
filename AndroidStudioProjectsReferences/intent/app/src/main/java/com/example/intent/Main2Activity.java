package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    Button button2;
    String message;
    Bundle message2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button2=findViewById(R.id.button1);
        message2=getIntent().getExtras();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent1);
                finish();

            }
        });


        message = (String) getIntent().getExtras().get("parcel_name");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
