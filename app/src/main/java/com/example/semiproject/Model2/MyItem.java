package com.example.semiproject.Model2;

import java.io.Serializable;

public class MyItem implements Serializable {

    private int imgId;   //이미지
    private String memo;  //메모 내용

    public MyItem(){};  //디폴트 생성자

    public MyItem(int imgId, String memo) {  //생성자
        this.imgId = imgId;
        this.memo = memo;
    }

    @Override
    public String toString() {  //나중에 데이터를 확인할 수 있게 해줌
        return "MyItem{" +
                "imageId=" + imgId +
                ", title='" + memo + '\'' +
                '}';
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


}
