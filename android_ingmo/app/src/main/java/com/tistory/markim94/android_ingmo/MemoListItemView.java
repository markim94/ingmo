package com.tistory.markim94.android_ingmo;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/*
 * create by brother jun
 *
 * */
public class MemoListItemView extends LinearLayout {

    TextView itemMemoTitle;
    TextView itemDate;
    TextView itemMemoText;
    Button btnStarMemo;

    Context mContext;

    public MemoListItemView(Context context) {
        super(context);

        // 리스트뷰가 존재하는 context에 토스트 메시지등을 띄우기 위해 adapter로 부터 context를 받아 mContext에 저장한다.
        mContext = context;

        // 인플레이터
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.memo_list_item, this, true);

        itemMemoTitle = (TextView) findViewById(R.id.itemMemoTitle);
        itemDate = (TextView) findViewById(R.id.itemDate);
        itemMemoText = (TextView) findViewById(R.id.itemMemoText);
        btnStarMemo = (Button) findViewById(R.id.btnStarMemo);

        btnStarMemo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                // btn의 star 상태를 확인하여 반대로 background resource를 전환
                // Toast.makeText(mContext,"중요 메모가 해지됐습니다.", Toast.LENGTH_SHORT);
            }
        });

    }

    /**
     * memo list data를 지정하는 메소드
     * @param index
     * @param data
     *
     * 0번 인덱스 : 날짜 지정
     * 1번 인덱스 : 텍스트 받아와서 12자까지 슬라이싱 --> 이전 파트는 title로, 이후 부분은 텍스트로
     * 2번 인덱스 : 중요 메모 확인
     */
    public void setContents(int index, String data){
        if (index ==0){
            itemDate.setText(data);
        } else if (index ==1){
            // ++ 메모의 내용이 12자보다 적을 경우
            if(data.length() < 12){
                itemMemoTitle.setText(data.substring(0));
                itemMemoText.setText(data.substring(0));
            } else {
                itemMemoTitle.setText(data.substring(0,11));
                itemMemoText.setText(data.substring(12));
            }
        } else if (index ==2){
            // starmemo가 체크되어 있을 경우
            if(data == "true"){
                btnStarMemo.setBackgroundResource(R.drawable.btn_star);
            } else {
                btnStarMemo.setBackgroundResource(R.drawable.btn_non_star);

            }
        }
    }


}
