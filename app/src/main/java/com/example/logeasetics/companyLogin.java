package com.example.logeasetics;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class companyLogin extends AppCompatActivity {


    EditText mFullName, mEmail, mPassword, mPhone;
    Button mLoginBtn;

    ProgressBar progressBar;
    FirebaseAuth fAuth;
    TextView goToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);


        mFullName= findViewById(R.id.productCompanyName);
        mEmail=findViewById(R.id.companyloginEmailAdress);
        mPassword=findViewById(R.id.companyloginPassword);
        mPhone=findViewById(R.id.companyPhone);
        mLoginBtn=findViewById(R.id.companyloginButton);
        progressBar=findViewById(R.id.companyprogressBar);
        goToRegister=findViewById(R.id.goFromLoginToRegister);
        fAuth=FirebaseAuth.getInstance();

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                System.out.println(email);
                System.out.println(password);

                if(TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    mPassword.setError("Password is required");
                    return;
                }
                if(password.length()<6)
                {
                    mPassword.setError("Password must be greater or equal to length 6.");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //LOGGING IN THE USER

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(companyLogin.this, "Logged In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), CompanyWelcome.class));
                        }
                        else
                        {
                            Toast.makeText(companyLogin.this, "Error"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
      goToRegister.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              startActivity(new Intent(getApplicationContext(), companyRegistration.class));
          }
      });
    }


}