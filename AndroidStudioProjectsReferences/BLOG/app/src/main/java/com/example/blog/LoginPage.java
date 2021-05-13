package com.example.blog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    EditText email,password;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_page);
        email = findViewById(R.id.email);
       password=findViewById(R.id.password);
        firebaseAuth= FirebaseAuth.getInstance();
    }

    public void onLoginClicked(View view) {
        if(email.getText().toString().equals(""))
        {
            email.setError("Enter a valid Email Id");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
        {
            email.setError("Enter a valid Email Id");
            email.requestFocus();
            return;
        }

        if(password.getText().toString().length() < 6)
        {
            password.setError("Set A Password Length greater than 6");
            password.requestFocus();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginPage.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginPage.this, HomePage.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginPage.this, "create new account", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginPage.this,SignupPage.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
