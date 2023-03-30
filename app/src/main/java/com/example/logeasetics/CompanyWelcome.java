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

public class CompanyWelcome extends AppCompatActivity {

    TextView addProducts;
    EditText mCompanyName, mOwnerEmail, mOwnerPhone, mOTP, mProductDescription, mSource, mDestination, mSourceLink, mDestinationLink, mOwner, mVehicleType;

    Button addProduct;
    ProgressBar cwprogressBar;
    FirebaseAuth fAuthCW;
    FirebaseFirestore fstoreCW;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_welcome);

        mCompanyName=findViewById(R.id.productCompanyName);
        mOwner=findViewById(R.id.owner);
        mOwnerEmail=findViewById(R.id.ownerEmailAdress);
        mOwnerPhone=findViewById(R.id.ownerPhone);
        mProductDescription=findViewById(R.id.productDescription);
        mSource=findViewById(R.id.pickupPoint);
        mDestination=findViewById(R.id.droppingpoint);
        mSourceLink=findViewById(R.id.maplinkofPickUP);
        mDestinationLink=findViewById(R.id.mapLinkOfDropOff);
        mVehicleType = findViewById(R.id.mVehicleType);
        mOTP=findViewById(R.id.otp);


        fAuthCW=FirebaseAuth.getInstance();
        fstoreCW=FirebaseFirestore.getInstance();

        addProduct=findViewById(R.id.addProduct);
        cwprogressBar=findViewById(R.id.progressBar);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String CompanyName=mCompanyName.getText().toString();
                String Owner=mOwner.getText().toString();
                String OwnerEmail=mOwnerEmail.getText().toString();
                String OwnerPhone=mOwnerPhone.getText().toString();
                String ProductDescription=mProductDescription.getText().toString();
                String Source=mSource.getText().toString();
                String Destination=mDestination.getText().toString();
                String SourceLink=mSourceLink.getText().toString();
                String DestinationLink=mDestinationLink.getText().toString();
                String OTP=mOTP.getText().toString();
                String VehicleType=mVehicleType.getText().toString();

                final String[] userId = new String[1];

                if(TextUtils.isEmpty(CompanyName))
                {
                    mCompanyName.setError("Company Name is required");
                    return;
                }
                if(TextUtils.isEmpty(Owner))
                {
                    mOwner.setError("Name of owner is required");
                    return;
                }
                if(TextUtils.isEmpty(OwnerEmail))
                {
                    mOwnerEmail.setError("Email of owner is required");
                    return;
                }
                if(TextUtils.isEmpty(OwnerPhone))
                {
                    mOwnerPhone.setError("Phone No. of owner is required");
                    return;
                }
                if(TextUtils.isEmpty(ProductDescription))
                {
                    mProductDescription.setError("Product Description is required.");
                    return;
                }
                if(TextUtils.isEmpty(Source))
                {
                    mSource.setError("Adress of pickup point is required.");
                    return;
                }
                if(TextUtils.isEmpty(Destination))
                {
                    mDestination.setError("Adress of destination is required");
                    return;
                }
                if(TextUtils.isEmpty(SourceLink))
                {
                    mSourceLink.setError("Provide the link of source.");
                    return;
                }
                if(TextUtils.isEmpty(DestinationLink))
                {
                    mDestinationLink.setError("Provide the link of destination.");
                    return;
                }
                if(TextUtils.isEmpty(VehicleType))
                {
                    mVehicleType.setError("Please choose vehicle type");
                }
                fAuthCW.createUserWithEmailAndPassword(OwnerEmail, OTP).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            userId[0] =fAuthCW.getCurrentUser().getUid();
                            Toast.makeText(CompanyWelcome.this, "Product Added Successfully", Toast.LENGTH_SHORT).show();
                            DocumentReference documentReference=fstoreCW.collection("products1").document(userId[0]);
                            HashMap<String, Object> user =new HashMap<>();
                            user.put("CompanyName", CompanyName);
                            user.put("OwnerEmail", OwnerEmail);
                            user.put("OwnerPhone", OwnerPhone);
                            user.put("OwnerName", Owner);
                            user.put("Owner", Owner);
                            user.put("SourceAdress", Source);
                            user.put("DestinationAdress",Destination);
                            user.put("SourceLink",SourceLink);
                            user.put("DestinationLink", DestinationLink);
                            user.put("vehicleType", VehicleType);
                            user.put("OTP", OTP);



                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("tag"," product added");
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
                            Toast.makeText(CompanyWelcome.this, "Error! "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

    }
}