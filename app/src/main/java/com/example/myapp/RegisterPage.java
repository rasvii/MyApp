package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterPage extends AppCompatActivity {

    EditText emailid, password;
    Button signup;
    TextView tvsignin;
    FirebaseAuth mfirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("New User Registration");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        mfirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.eREmail);
        password = findViewById(R.id.eRPassword);
        tvsignin = findViewById(R.id.textRSignin);
        signup = findViewById(R.id.buttonSignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    emailid.setError("Please enter email!");
                    emailid.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Please enter password!");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(RegisterPage.this,"Fields are empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    mfirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(RegisterPage.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(RegisterPage.this,"Signup Unsuccessful, Try Again!",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(RegisterPage.this,WelcomePage.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterPage.this,"Error Ocurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
