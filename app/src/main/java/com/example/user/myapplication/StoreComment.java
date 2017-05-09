package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class StoreComment extends ActionBarActivity {
   // FileProcess2 fp;
    EditText et_C;
    //ArrayList<String> titlelist;
   // boolean bSDCard1;
    Button btn_comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_comment);

        et_C = (EditText) findViewById(R.id.ed_C);
        btn_comment = (Button)findViewById(R.id.btn_comment2);
        btn_comment.setOnClickListener(btnClick);
//================================================================
  /*      Intent intent = getIntent();
        bSDCard1 = intent.getBooleanExtra("SDCARD", false);
        int storecomment = intent.getIntExtra("STORE", -1);

        fp = new FileProcess2(this, bSDCard1);
        titlelist = fp.getCommentList();

        if ( storecomment!= -1) {
            String title = titlelist.get(storecomment);
            et_C.setText(title);
            title = title + ".txt";
        } else {
            et_C.setText("");
        }
*/
//====================================================================


    }

    View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            String msg = et_C.getText().toString();
            intent.putExtra("KEY_MSG",msg);
            setResult(RESULT_OK, intent);
            finish();
        }
    };


  /*  public boolean isTitleExist(String title) {
        for (int i = 0; i < titlelist.size(); i++)
            if (title.equalsIgnoreCase(titlelist.get(i)))
                return true;
        return false;
    }
*/
}
