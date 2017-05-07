package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class StoreAdd extends ActionBarActivity {
    FileProcess fp;
    EditText et_title, et_body;
    ArrayList<String> titlelist;
    boolean bSDCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_add);

        et_title = (EditText) findViewById(R.id.ed_title);
        et_body = (EditText) findViewById(R.id.ed_body);

        Intent intent = getIntent();
        bSDCard = intent.getBooleanExtra("SDCARD", false);
        int addstore = intent.getIntExtra("STORE", -1);

        fp = new FileProcess(this, bSDCard);
        titlelist = fp.getStoreList();

        if (addstore != -1) {
            String title = titlelist.get(addstore);
            et_title.setText(title);
            title = title + ".txt";
            et_body.setText(fp.readFile(title));
        } else {
            et_title.setText("");
            et_body.setText("");
        }
    }

    public void onPause() {
        super.onPause();
        String title = et_title.getText().toString();
        if (title.length() == 0) {
            Toast.makeText(this, "標題不能為空白，店家無儲存",
                    Toast.LENGTH_LONG).show();
        } else {
            fp.addStore(et_title.getText().toString(),
                    et_body.getText().toString(),
                    !isTitleExist(title));
        }
    }

    public boolean isTitleExist(String title) {
        for (int i = 0; i < titlelist.size(); i++)
            if (title.equalsIgnoreCase(titlelist.get(i)))
                return true;
        return false;
    }

}