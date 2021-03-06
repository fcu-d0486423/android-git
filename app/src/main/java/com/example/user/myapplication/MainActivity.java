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
import android.widget.AdapterView;
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

    ListView lvHotels;
    EditText ed_search;
    Button btn_search;
    HotelArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvHotels = (ListView) findViewById(android.R.id.list);
        ed_search = (EditText) findViewById(R.id.ed_search);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(searchListener);
        adapter = new HotelArrayAdapter(this, new ArrayList<Hotel>());
        lvHotels.setAdapter(adapter);
<<<<<<< HEAD
        lvHotels.setOnItemClickListener(iclick);
        getHotelFromFirebase();



    }
=======
        //lvHotels.setOnItemClickListener(iclick);
        getHotelFromFirebase();


        lvHotels.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long id) {


                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ShowDetail.class);
                intent.putExtra("STORE", position);
                startActivity(intent);


            }

            setContentView(lvHotels);

        });

}
>>>>>>> origin/master

    AdapterView.OnItemClickListener iclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent();
            intent.setClass(MainActivity.this,ShowDetail.class);
            intent.putExtra("STORE",position);
            startActivity(intent);
        }
    };

    View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            String storeName = ed_search.getText().toString();
            intent.setClass(MainActivity.this,SearchResult.class);
            intent.putExtra("KEY_NAME",storeName);
            startActivity(intent);
        }
    };

   /* AdapterView.OnItemClickListener iclick = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> av, View v,
                                int position, long id) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,
                    ShowDetail.class);
            intent.putExtra("POSITION",position);
            startActivity(intent);
        }
    };*/




    private void getHotelFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                new FirebaseThread(dataSnapshot, adapter,1).start();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("android-git", databaseError.getMessage());
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
