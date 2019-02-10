package com.tistory.markim94.android_ingmo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*
 * create by brother jun
 *
 * */
public class MemoInsertActivity extends AppCompatActivity {

    public static final String TAG = "MemoInsertActivity";

    // button 객체
    Button btnStatusMemo;
    Button btnStoreMemo;
    Button btnDeleteMemo;
    Button btnCancelMemo;
    Button btnStarMemo;

    // intent를 통해 넘겨받은 memo data를 저장할 String 객체
    String mMemoId;
    String mMemoDate;
    EditText mMemoText;
    String mMemoStarChecked;

    // 중요 메모인지 아닌지
    boolean isChecked;


    // 인텐트로 전달된 메모의 모드를 저장할 String
    String mMemoMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_insert_activity);

        // 타이틀 상태버튼과 하단부의 버튼 객체 참조 메소드.
        buttonLayout();
        Log.d(TAG,"buttonLayout finish");

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
            btnStoreMemo.setText("수정");

            // 이외의 경우 새로운 메모 추가 버튼을 누른 경우 이므로
        } else {
            btnStatusMemo.setText("새 메모");
            btnStoreMemo.setText("저장");
            btnDeleteMemo.setVisibility(View.GONE);

        }

    }


    public void processIntent(Intent intent){
        mMemoId = intent.getStringExtra("MEMO_ID");
        mMemoDate = intent.getStringExtra("MEMO_DATE");
        mMemoText.setText(intent.getStringExtra("MEMO_TEXT"));
        mMemoStarChecked = intent.getStringExtra("MEMO_STARCHECKED");

        setStatusMemo(mMemoDate,mMemoStarChecked);

    }


    public void setStatusMemo(String mMemoDate, String mMemoStarChecked){
        // date받아서 그대로 갖고 있다가 반환
        if (mMemoStarChecked.equals("true")){
            isChecked = true;
            btnStarMemo.setBackgroundResource(R.drawable.btn_star);
        } else {
            isChecked = false;
            btnStarMemo.setBackgroundResource(R.drawable.btn_non_star);
        }
    }






    /**
     * 상단 상태버튼, 하단부 버튼 객체 참조 메소드
     */
    void buttonLayout(){
        btnStatusMemo = (Button)findViewById(R.id.btnStatusMemo);
        btnStoreMemo = (Button)findViewById(R.id.btnStoreMemo);
        btnDeleteMemo = (Button)findViewById(R.id.btnDeleteMemo);
        btnCancelMemo = (Button)findViewById(R.id.btnCancelMemo);
        btnStarMemo = (Button)findViewById(R.id.btnStarMemo);

        Log.d(TAG,"buttonLayout load");

        btnCancelMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDeleteMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog(1);
            }
        });

        btnStoreMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 실행코드


            }
        });

        btnStarMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 실행코드
            }
        });

    }


    private void saveInput(){

        // db에 새로이 데이터 추가하는것
        Log.d(TAG,"saveInput start");
    }

    private void modifyInput(){

        // db 레코드 수
        Log.d(TAG,"modifyInput start");
    }

    private boolean parseValues(){

        // 메모모드가 modify, view일 경우
        // memoText 받아와서 1보다 적을 경우 메모 길이 더 늘리라는 dialog띄우기

        return true;
    }


    public void deleteMemo(){

        // db에서 메모를 지우는
    }



    protected Dialog onCreateDialog(int id){
        AlertDialog.Builder builder = null;

        switch (id){
            // case별로
            case 1:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("잉모");
                builder.setMessage("메모를 삭제하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteMemo();
                        Log.d(TAG,"deleteMemo finish");
                    }
                });
                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismissDialog(1);
                        Log.d(TAG,"dismissDialog finish");
                    }
                });
                break;
            //case 2:
        }
        return builder.create();
    }


}
