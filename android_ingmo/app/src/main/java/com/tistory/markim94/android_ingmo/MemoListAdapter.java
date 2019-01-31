package com.tistory.markim94.android_ingmo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/*
 * create by brother jun
 *
 * 커스텀 리스트뷰를 위한 어댑터 클래스
 *
 * */
public class MemoListAdapter extends BaseAdapter {

    // 리스트뷰가 위치한 메인액티비티의 context를 받아서 저장할 공간, 또한 이를 memoListItemView에도 보냄.
    private Context mContext;

    // receive context from main activity
    public MemoListAdapter(Context context) {
        mContext = context;
    }

    // ArrayList에 mItems 데이터를 저장함
    public List<MemoListItem> mItems = new ArrayList<MemoListItem>();

    // array clear
    public void clear() { mItems.clear(); }

    // add item
    public void addItem(MemoListItem it) { mItems.add(it); }

    // array setter
    public void setListItems(List<MemoListItem> lit) {
        mItems = lit;
    }


    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MemoListItemView itemView;
        if(convertView == null){
            // 메인 액티비티의 context
            itemView = new MemoListItemView(mContext);
        } else {
            itemView = (MemoListItemView) convertView;
        }

        // 0번 : date, 1번 : text, 2번 : 중요메모 표시
        itemView.setContents(0, ((String) mItems.get(position).getData(0)));
        itemView.setContents(1, ((String) mItems.get(position).getData(1)));
        itemView.setContents(2, ((String) mItems.get(position).getData(2)));

        return itemView;
    }
}
