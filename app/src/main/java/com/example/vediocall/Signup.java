package com.example.vediocall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
EditText name,email,password;
Button signup,alreadyhaveaccount;
 private  FirebaseAuth auth;
FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name=findViewById(R.id.namesignup);
        email=findViewById(R.id.emailsignup);
        password=findViewById(R.id.passwordsignup);
        signup=findViewById(R.id.sighupsignup);
        alreadyhaveaccount=findViewById(R.id.ahasignup);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.createUserWithEmailAndPassword
                                (email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    DataBase user=new DataBase(name.getText().toString(),email.getText().toString(),
                                            password.getText().toString());
                                    String id=task.getResult().getUser().getUid();
                                    database.getReference().child("users").child(id).setValue(user);
                                    Toast.makeText(Signup.this ,"successfully registered",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(Signup.this,Login.class);
                                    startActivity(intent);
                                    Signup.this.finish();
                                } else {
                                    Toast.makeText(Signup.this, "failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        alreadyhaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,Login.class));
            }
        });
    }
}