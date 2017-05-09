package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowDetail extends AppCompatActivity {

    FileProcess fp;
    TextView tv_title, tv_body;
    ArrayList<String> titlelist;
    Button btn_addComment;
    boolean bSDCard;
    private static final int ACTIVITY_SET_COMMENT = 1;
    ListView LIST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        Intent intent = getIntent();
        bSDCard = intent.getBooleanExtra("SDCARD", false);
        int addstore = intent.getIntExtra("STORE", -1);
        tv_title = (TextView) findViewById(R.id.tv_storeName2);
        tv_body = (TextView) findViewById(R.id.tv_storeContent2);
        fp = new FileProcess(this, bSDCard);
        titlelist = fp.getStoreList();
        String title = titlelist.get(addstore);
        tv_title.setText(title);
        title = title + ".txt";
        tv_body.setText(fp.readFile(title));
        btn_addComment = (Button) findViewById(R.id.btn_comment);
        btn_addComment.setOnClickListener(commentClick);
        ArrayList<String> commentList = new ArrayList<String>();

    }


    View.OnClickListener commentClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(ShowDetail.this, StoreComment.class);
            startActivityForResult(intent, ACTIVITY_SET_COMMENT);
            // ShowDetail.this.finish();
        }
    };

/*

    View.OnClickListener commentClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(ShowDetail.this,StoreComment.class);
            intent.putExtra("SDCARD", bSDCard);
            intent.putExtra("STORE", -1);
            startActivity(intent);
        }
    };
*/


    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        String msg;
        LIST = (ListView)findViewById(android.R.id.list);
        if (intent == null) return;

        super.onActivityResult(requestCode, resultCode, intent);

        switch (resultCode) {
            case ACTIVITY_SET_COMMENT:
                msg = intent.getStringExtra("KEY_MSG");
                titlelist.add(msg);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        titlelist);

                LIST.setAdapter(adapter);
                break;
        }

    }

}
