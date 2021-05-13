package com.example.firebasedatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FirebaseStorage extends AppCompatActivity {
    StorageReference storageReference;
    Button button;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_storage);
        storageReference = com.google.firebase.storage.FirebaseStorage.getInstance().getReference("Images");
        button = findViewById(R.id.image);
        intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
    }
    public void onButtonClicked(View view) {
        startActivityForResult(intent, 8888);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 8888 && resultCode == RESULT_OK && data!=null)
        {
            final ProgressDialog progressDialog=new ProgressDialog(FirebaseStorage.this);
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

                                String url = task.getResult().toString();

                            }
                        });



                        Toast.makeText(FirebaseStorage.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                        FirebaseStorage.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                            }
                        });
                    }
                    else{
                        Toast.makeText(FirebaseStorage.this, "Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


}
