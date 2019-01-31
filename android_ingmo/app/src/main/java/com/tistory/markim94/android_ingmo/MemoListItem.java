package com.tistory.markim94.android_ingmo;

/*
 * create by brother jun
 * 객체지향성을 위해 데이터를 별도로 set, get하는 listitem.class 생성
 * */
public class MemoListItem {

    // list item 담을 mData 배열
    private String[] mData;

    // 메모 고유 id
    private String mId;

    // 메모 아이템의 선택여부
    private boolean mSelectable = true;


    /**
     * constructor
     * @param mId
     * @param mData
     */
    public MemoListItem(String mId, String[] mData) {
        this.mId = mId;
        this.mData = mData;
    }




}
