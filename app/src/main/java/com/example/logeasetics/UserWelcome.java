package com.example.logeasetics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserWelcome extends AppCompatActivity {

    TextView fullName, email, phone, welcomeName;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;

    public void logout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), UserActivity.class));
    }


    public void goToProductsView(View view)
    {
        Intent i=new Intent(UserWelcome.this, MapsActivity.class);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome);
        phone=findViewById(R.id.userphone);
        fullName=findViewById(R.id.userName);
        email=findViewById(R.id.userEmail);
       welcomeName=findViewById(R.id.productDisplay);

        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        userId=fAuth.getCurrentUser().getUid();

        DocumentReference documentReference=fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                phone.setText(value.getString("phone"));
                fullName.setText(value.getString("Name"));
                welcomeName.setText(value.getString("Name"));
                email.setText(value.getString("email"));
            }
        });

    }


}