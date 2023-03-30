package com.example.logeasetics;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logeasetics.databinding.ActivityDisplayProductsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import java.util.ArrayList;

public class DisplayProducts extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



//    TextView product, sourceAdd, DestinationAdd;
//    FirebaseAuth fAuth;
//    FirebaseFirestore fstore;
//    String userId, sourceLink, destinationLink;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_display_products);
//        product=findViewById(R.id.product);
//        sourceAdd=findViewById(R.id.sourceAdress);
//        DestinationAdd=findViewById(R.id.destinationAdress);
//
//
//
//        fAuth=FirebaseAuth.getInstance();
//        fstore=FirebaseFirestore.getInstance();
//
//        userId=fAuth.getCurrentUser().getUid();
//
//        DocumentReference documentReference=fstore.collection("products1").document(userId);
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                product.setText(value.getString("ProductDES"));
//                sourceAdd.setText(value.getString("SourceAdress"));
//                DestinationAdd.setText(value.getString("DestinationAdress"));
//                sourceLink=value.getString("SourceLink");
//                destinationLink=value.getString("Destination Link");
//
//            }
//        });
//
//    }
//    public void gotoSOurceMapView (View view)
//    {
//        Intent viewIntent =
//                new Intent("android.intent.action.VIEW",
//                        Uri.parse("https://www.google.com/maps/place/Bharat+Petroleum+Mahavir+Stambh+Baba+Petrol+Pump/@19.8737121,75.3152838,17z/data=!4m5!3m4!1s0x3bdb98426f3544d5:0xfdc8a7c2f0bf1717!8m2!3d19.8740854!4d75.3157881"));
//        startActivity(viewIntent);
//    }
//
//
//    public void gotoDestinationMapView(View view)
//    {
//        Intent viewIntent =
//                new Intent("android.intent.action.VIEW",
//                        Uri.parse("https://www.google.com/maps/place/Maharashtra+Institute+Of+Technology/@19.8859934,75.3407035,13.35z/data=!4m5!3m4!1s0x3bdb988c254eb873:0x4388791935b718e6!8m2!3d19.8494163!4d75.322037"));
//        startActivity(viewIntent);
//    }


//    ListView productListView;
//    FirebaseFirestore db=FirebaseFirestore.getInstance();
//    ArrayList<products> list;
//    MyAdapter myAdapter;
//
//    ArrayAdapter<products> adapter;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_display_products);
//
//        productListView=findViewById(R.id.productList);
//         adapter=new ArrayAdapter<products>(
//             this, android.R.layout.simple_list_item_1, new ArrayList<products>()
//
//        );
//        productListView.setAdapter(adapter);
//
//
////        list=new ArrayList<>();
////        myAdapter=new MyAdapter(this, list);
////        recyclerView.setAdapter(myAdapter);
//
//
//
//
//       /* database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot: snapshot.getChildren())
//                {
//                    products products=dataSnapshot.getValue(products.class);
//                    list.add(products);
//                }
//                myAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });*/
//
//
//
//
//    }
//    public void onRefresh(View view)
//    {
//        db.collection("products").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//               ArrayList<products> products=new ArrayList<>();
//               for(QueryDocumentSnapshot document: queryDocumentSnapshots)
//               {
//                   products p=document.toObject(products.class);
//                   products.add(p);
//
//               }
//               adapter.addAll(products);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(DisplayProducts.this, "Fail to get the data", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
    }
    public void goToMaps(View view)
    {
        startActivity(new Intent( getApplicationContext(), UserActivity .class));
    }
}