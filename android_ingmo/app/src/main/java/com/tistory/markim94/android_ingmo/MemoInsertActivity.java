package com.tistory.markim94.android_ingmo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
 * create by brother jun
 *
 * */
public class MemoInsertActivity extends AppCompatActivity {

    Button btnStatusMemo;
    Button btnStoreMemo;
    Button btnDeleteMemo;
    Button btnCancelMemo;


    // 인텐트로 전달된 메모의 모드를 저장할 String
    String mMemoMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_insert_activity);

        // 타이틀 상태버튼과 하단부의 버튼 객체 참조 메소드.
        buttonLayout();

        

    }






    /**
     * 상단 상태버튼, 하단부 버튼 객체 참조 메소드
     */
    void buttonLayout(){
        btnStatusMemo = findViewById(R.id.btnStatusMemo);
        btnStoreMemo = findViewById(R.id.btnStoreMemo);
        btnDeleteMemo = findViewById(R.id.btnDeleteMemo);
        btnCancelMemo = findViewById(R.id.btnCancelMemo);

        btnCancelMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 실행코드
            }
        });

        btnDeleteMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 실행코드
            }
        });

        btnStoreMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 실행코드
            }
        });

    }

}
