package com.example.logeasetics;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class companyRegistration extends AppCompatActivity {

    TextView goToLogin;
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegisterBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_registration);


        mFullName= findViewById(R.id.productCompanyName);
        mEmail=findViewById(R.id.companyloginEmailAdress);
        mPassword=findViewById(R.id.companyloginPassword);
        mPhone=findViewById(R.id.companyPhone);
        mRegisterBtn=findViewById(R.id.addProduct);
        goToLogin=findViewById(R.id.goFromLoginToRegister);

        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        progressBar=findViewById(R.id.progressBar);

        //IF USER IS ALREADY PRESENT WE WILL REDIRECT HIM TO LOGIN PAGE
        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(), companyLogin.class ));
            finish();
        }
        //EVENT WHEN REGISTER BUTTON IS CLICKED
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                String fullName=mFullName.getText().toString();
                String phone=mPhone.getText().toString();
                final String[] userId = new String[1];
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

                //Registering the USER
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            userId[0] =fAuth.getCurrentUser().getUid();
                            Toast.makeText(companyRegistration.this, "USER CREATED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                            DocumentReference documentReference=fstore.collection("company").document(userId[0]);
                            HashMap<String, Object> user =new HashMap<>();
                            user.put("companyName", fullName);
                            user.put("companyEmail", email);
                            user.put("companyPhone", phone);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("tag"," user created");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("failure","failed");
                                }
                            });

                            startActivity(new Intent(getApplicationContext(), CompanyWelcome.class));
                            //Task is completed and now user is redirected to his dashBoard
                        }
                        else
                        {
                            Toast.makeText(companyRegistration.this, "Error! "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), companyLogin.class));
            }
        });
    }
}