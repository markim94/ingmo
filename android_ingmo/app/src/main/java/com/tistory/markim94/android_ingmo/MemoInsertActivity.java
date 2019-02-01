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

    // button 객체
    Button btnStatusMemo;
    Button btnStoreMemo;
    Button btnDeleteMemo;
    Button btnCancelMemo;

    // intent를 통해 넘겨받은 memo data를 저장할 String 객체
    String mMemoId;
    String mMemoDate;
    String mMemoText;
    String isStarChecked;


    // 인텐트로 전달된 메모의 모드를 저장할 String
    String mMemoMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_insert_activity);

        // 타이틀 상태버튼과 하단부의 버튼 객체 참조 메소드.
        buttonLayout();

        // memolist activity에서 getIntent.
        Intent intent = getIntent();
        // 1. intent의 extra data의 모드를 받아온다.
        mMemoMode = intent.getStringExtra("MEMO_MODE");
        // 2. 받아온 모드가 기존에 존재하는 리스트를 클릭한 경우(mode가 view 일 경우)
        if(mMemoMode.equals("MODE_VIEW")){

            // data를 넘겨받는 processIntent
            processIntent(intent);

            btnStatusMemo.setText("메모 보기");
            btnDeleteMemo.setVisibility(View.VISIBLE);
            // 이외의 경우 새로운 메모 추가 버튼을 누른 경우 이므로
        } else {
            btnStatusMemo.setText("새 메모");
            btnDeleteMemo.setVisibility(View.GONE);
        }

    }


    public void processIntent(Intent intent){
        mMemoId = intent.getStringExtra("MEMO_ID");
        mMemoDate = intent.getStringExtra("MEMO_DATE");
        mMemoText = intent.getStringExtra("MEMO_TEXT");
        isStarChecked = intent.getStringExtra("MEMO_STARCHECKED");
        
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
