package com.tistory.markim94.android_ingmo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MemoListActivity extends AppCompatActivity {

    Button btnAddMemo;
    ListView listMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_list_activity);

        listMemo = findViewById(R.id.listMemo);
        btnAddMemo = findViewById(R.id.btnAddMemo);


        btnAddMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MemoInsertActivity.class);
                //intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_INSERT);
                startActivityForResult(intent, BasicInfo.REQ_INSERT_ACTIVITY);
            }
        });


    }
}
