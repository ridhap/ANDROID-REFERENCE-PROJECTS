package com.example.blog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignupPage extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    CircleImageView circleImageView;
    EditText name, email, password;
    String nameValue, emailValue;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    String url;
//    Button button;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        circleImageView = findViewById(R.id.circleImageView);
//        button = findViewById(R.id.circleImageView);
        storageReference = FirebaseStorage.getInstance().getReference("images");
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent, 8888);

            }
        });

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    public void onImageClicked(View view) {
        startActivityForResult(intent, 8888);
    }

    public void onSignUpClicked(View view) {

        if (email.getText().toString().equals("")) {
            email.setError("Enter a valid Email Id");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Enter a valid Email Id");
            email.requestFocus();
            return;
        }

        if (password.getText().toString().length() < 6) {
            password.setError("Set A Password Length greater than 6");
            password.requestFocus();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignupPage.this, "New USER Created", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupPage.this, "Already Signed UP try logging In!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        nameValue = name.getText().toString();
        emailValue = email.getText().toString();
        if (name.getText().toString().equals("")) {
            name.setError("Enter your Name");
            name.requestFocus();
            return;
        }
        if (nameValue.contains(".") || nameValue.contains("/")) {
            name.setError("Enter your First Name only");
            name.requestFocus();
            return;
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(nameValue);
        databaseReference.child("Name").setValue(nameValue).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignupPage.this, "Data Added Succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupPage.this, "Try Again Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
        databaseReference.child("Email").setValue(emailValue).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignupPage.this, "Data Added Succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupPage.this, "Try Again Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
        databaseReference.child("dp").setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignupPage.this, "Data Added Succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupPage.this, "Try Again Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Intent intent = new Intent(SignupPage.this, HomePage.class);
        startActivity(intent);
        finish();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==8888 && resultCode==RESULT_OK && data!=null)
        {
            final Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                circleImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            final ProgressDialog progressDialog=new ProgressDialog(SignupPage.this);
            progressDialog.setMessage("on process");
            progressDialog.create();
            progressDialog.show();
            storageReference.child("Text").putFile(data.getData()).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    storageReference.child("Text").putFile(data.getData());
                    if(task.isSuccessful())
                    {

                        storageReference.child("Text").getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {

                                url = task.getResult().toString();
                                Picasso.get().load(url).into(circleImageView);


                            }
                        });
                    }
                }
            });

        }
    }


}