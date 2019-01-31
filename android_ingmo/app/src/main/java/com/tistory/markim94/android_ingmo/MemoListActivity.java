package com.tistory.markim94.android_ingmo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

/*
 * create by brother jun
 *
 * */
public class MemoListActivity extends AppCompatActivity {

    // memo add button
    Button btnAddMemo;

    // list view
    ListView listMemo;

    // adapter for list view
    MemoListAdapter memoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_list_activity);

        btnAddMemo = (Button) findViewById(R.id.btnAddMemo);

        // 메모 추가 버튼 클릭시, 메모 추가 액티비티로 startActivity
        btnAddMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MemoInsertActivity.class);
                // 추가 버튼이므로 메모 insert mode로
                intent.putExtra("MEMO_MODE", "MODE_INSERT");
                startActivityForResult(intent, BasicInfo.REQ_INSERT_ACTIVITY);
            }
        });

        // 커스텀 리스트뷰의 정상작동을 확인하기 위한 addItem 메소드
        testingAddItem();


        listMemo = (ListView) findViewById(R.id.listMemo);
        memoListAdapter = new MemoListAdapter(this);
        listMemo.setAdapter(memoListAdapter);

        listMemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 특정 넘버(포지션)을 매개변수로 받아와 해당 메모를 보여줌
                viewMemo(position);
            }
        });

    }


    // 커스텀 리스트뷰의 정상작동을 확인하기 위한 addItem 메소드
    public void testingAddItem(){
        MemoListItem testItem = new MemoListItem("1","2013년 1월 22일", "나의 메모는 이렇습니다. 나의 메모는 이렇습니다. 나의 메모는 이렇습니다. 나의 메모는 이렇습니다.", "true");
        MemoListItem testItem2 = new MemoListItem("2","2015년 1월 22일", "나의 메모는 이렇습니다. 나의 메모는 이렇습니다. 나의 메모는 이렇습니다. 나의 메모는 이렇습니다.", "false");
        MemoListItem testItem3 = new MemoListItem("3","2016년 6월 25일", "나의 메모는 이렇습니다. 나의 메모는 이렇습니다. 나의 메모는 이렇습니다. 나의 메모는 이렇습니다.", "true");
        MemoListItem testItem4 = new MemoListItem("4","2017년 10월 2일", "나의 메모는 이렇습니다. 나의 메모는 이렇습니다. 나의 메모는 이렇습니다. 나의 메모는 이렇습니다.", "true");
        MemoListItem testItem5 = new MemoListItem("5","2018년 11월 22일", "나의 메모는 이렇습니다. 나의 메모는 이렇습니다. 나의 메모는 이렇습니다. 나의 메모는 이렇습니다.", "false");
        memoListAdapter.addItem(testItem);
        memoListAdapter.addItem(testItem2);
        memoListAdapter.addItem(testItem3);
        memoListAdapter.addItem(testItem4);
        memoListAdapter.addItem(testItem5);
    }


    private void viewMemo(int position){
        MemoListItem item = (MemoListItem) memoListAdapter.getItem(position);

        Intent intent = new Intent(getApplicationContext(), MemoInsertActivity.class);
        // 주어진 메모를 클릭한 것이므로 해당 메모모드는 view로. (메모 삭제버튼 활성화)
        intent.putExtra("MEMO_MODE", "MODE_VIEW");
        intent.putExtra("MEMO_ID", item.getId());
        intent.putExtra("MEMO_DATE", item.getData(0));
        intent.putExtra("MEMO_TEXT", item.getData(1));
        intent.putExtra("MEMO_STARCHECKED", item.getData(2));

        startActivityForResult(intent, BasicInfo.REQ_VIEW_ACTIVITY);
    }


    public void loadMemoListData(){

        // mongo db와 연동할 코딩 부분.

    }



    /**
     * 다른 액티비티의 응답 처리
     */
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch(requestCode) {
//            case BasicInfo.REQ_INSERT_ACTIVITY:
//                if(resultCode == RESULT_OK) {
//                    loadMemoListData();
//                }
//
//                break;
//
//            case BasicInfo.REQ_VIEW_ACTIVITY:
//                loadMemoListData();
//
//                break;
//
//        }
//    }

}
