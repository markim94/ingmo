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

    public MemoListItem(String memoId, String memoDate, String memoText, String isStarChecked){
        mId = memoId;
        mData = new String[3];
        mData[0] = memoDate;
        mData[1] = memoText;
        mData[2] = isStarChecked;
    }

    // item 선택여부 반환(getter)
    public boolean isSelectable() { return mSelectable; }

    // selectable setter
    public void setSelectable(boolean selectable) { mSelectable = selectable; }

    // Id getter
    public String getId() { return mId; }

    // Id setter
    public void setId(String itemId) { mId = itemId; }

    // Data getter (array 통째로)
    public String[] getData() { return mData; }

    // Data index getter
    public String getData(int index){
        if (mData == null || index >= mData.length){
            return null;
        }
        return mData[index];
    }

    // Data setter (array)
    public void setData(String[] obj) { mData = obj; }


}
