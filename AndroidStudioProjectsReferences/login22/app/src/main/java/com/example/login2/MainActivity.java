package com.example.login2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    String name,email,password,message;
    EditText names,passwords,emails;
    CircleImageView circleImageView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        names=findViewById(R.id.names);
        emails=findViewById(R.id.email);
        passwords=findViewById(R.id.password);
//        final EditText username = (EditText) findViewById(R.id.names);
//        final EditText passwordss = (EditText) findViewById(R.id.password);
//        final EditText emails = (EditText) findViewById(R.id.email);


        button=findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=names.getText().toString();
                password = passwords.getText().toString();
                email = emails.getText().toString();// however you get the password
                if (password.isEmpty() || password.length() < 6) {
                    Toast.makeText(MainActivity.this, "Password cannot be less than 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
//                    Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                }
                if (email.equals("")) {
                    Toast.makeText(MainActivity.this, "You did not enter a email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (name.equals("")) {
                    Toast.makeText(MainActivity.this, "You did not enter a username", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intents = new Intent(MainActivity.this, Main2Activity.class);
                message = names.getText().toString();
                intents.putExtra("parcel_name", message);
                startActivity(intents);
                finish();
            }
        });


        circleImageView = findViewById(R.id.circleImageView2);
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(circleImageView);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent, 8888);


            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 8888 && resultCode == RESULT_OK && data!=null)
        {
            Uri uri =data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                circleImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
