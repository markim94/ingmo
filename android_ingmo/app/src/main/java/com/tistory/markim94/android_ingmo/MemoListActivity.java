package com.tistory.markim94.android_ingmo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MemoListActivity extends AppCompatActivity {

    Button btnAddMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_list_activity);

        btnAddMemo = findViewById(R.id.btnAddMemo);


    }
}
