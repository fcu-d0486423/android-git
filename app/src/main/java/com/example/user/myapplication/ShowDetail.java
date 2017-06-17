package com.example.user.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ShowDetail extends AppCompatActivity {

    TextView tv_title, tv_body;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        Intent intent = getIntent();

        position = intent.getIntExtra("STORE",-1);
        tv_title = (TextView) findViewById(R.id.tv_storeName2);
        tv_body = (TextView) findViewById(R.id.tv_storeContent2);


        if(position!=-1){
            getHotelFromFirebase();
        }

    }

    private void getHotelFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (position == i) {
                        DataSnapshot dsSDes = ds.child("HostWords");
                        DataSnapshot dsSName = ds.child("Name");

                        String storeName = (String) dsSName.getValue();
                        String storeDes = (String) dsSDes.getValue();
                        tv_title.setText(storeName);
                        tv_body.setText(storeDes);
                        break;
                    }
                    else i++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("android-git", databaseError.getMessage());
            }
        });
    }

}
