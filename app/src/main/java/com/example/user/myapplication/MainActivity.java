package com.example.user.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    FileProcess fp;
    boolean bSDCard = false; // true;
    EditText ed_search;
    Button btn_add;
    ListView lv_store;
    HotelArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvHotels = (ListView)findViewById(android.R.id.list);

        adapter = new HotelArrayAdapter(this, new ArrayList<Hotel>());
        lvHotels.setAdapter(adapter);

        getHotelFromFirebase();
    }

    private void getHotelFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                new FirebaseThread(dataSnapshot, adapter).start();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("AdoptPet", databaseError.getMessage());
            }
        });
    }

    //@Override
   /* protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fp = new FileProcess(this, bSDCard);

        ed_search = (EditText)findViewById(R.id.ed_search);

        lv_store = (ListView)findViewById(android.R.id.list);

        ArrayList<String> storelist = new ArrayList<String>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                storelist);

        lv_store.setAdapter(adapter);
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*public void onResume() {
        super.onResume();

        ArrayList<String> notelist = fp.getStoreList();
        ArrayAdapter<String> adapter =new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, notelist);
        lv_store.setAdapter(adapter);
    }*/
}
