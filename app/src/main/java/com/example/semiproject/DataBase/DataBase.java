package com.example.semiproject.DataBase;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.semiproject.Model2.MyItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase {

    public final static String TBL_ITEM = "ITEM";

    static DataBase inst;
    private static SharedPreferences sf = null;  //저장 객체  //밑에 데이터베이스 함수가 static이기 때문에 sf 변수도 sratic이어야 한다

    private static List<MyItem> items = null;  //원본 데이터


    //싱글톤 > 객체를 하나만 만들고 싶을 때
    private DataBase(){}

    public static DataBase getInstance(Context context){

        if(items == null){
            items = new ArrayList<>();
        }

        if(sf == null){
            sf = context.getSharedPreferences("MEMO", Activity.MODE_PRIVATE);  //DB의 스키마 정도의 기능
        }

        if(inst == null){
            inst = new DataBase();
        }
        return inst;  //자기 자신을 리턴
    }

    //item 선두에 추가
    public void additem(MyItem item){
        items.add(0, item);
    }

    //item 획득
    public MyItem getItem(int index){
        return items.get(index);
    }
    //item 변경(덮어씀으로써)
    public void setItem(int index, MyItem item){
        items.set(index, item);
    }

    //item 삭제
    public void removeItem(int index){
        items.remove(index);
    }

    //item SharedPreferences에 저장
    public void saveitems(){
        //객체를 문자열(Json)로 변경
        String itemString = new GsonBuilder().serializeNulls().create().toJson(items);

        //저장
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(TBL_ITEM, itemString);  //key : value 형식으로 저장
        editor.commit();
    }

    //items 획득
    public List<MyItem> loaditems(){
        //SharedPreferences의 items정보를 문자열로 획득
        String itemString = sf.getString(TBL_ITEM, "");
        if(!itemString.isEmpty()){
            //문자열을 Myitem 배열 형태로 변환
            MyItem[] itemArray = new Gson().fromJson(itemString, MyItem[].class);

            //배열을 ArrayList형태로 변환
            items = new ArrayList<>(Arrays.asList(itemArray));
        }
        return  items;
    }

//    //회원 저장
//    public void setMember(MemberModel memberModel){
//        //Member 객체를 Json 형태의 문자열로 변환
//        String memberString = new GsonBuilder().serializeNulls().create().toJson(memberModel);
//        Log.d("Database", "memberString" + memberString);
//
//        //저장
//        SharedPreferences.Editor editor = sf.edit();
//        editor.putString(memberModel. getId(), memberString);  //key : value 형식으로 저장
//        editor.commit();
//    }
//
//
//
//    //회원 조회
//    public MemberModel getMember(String id){
//        MemberModel memberModel = null;
//
//        //id를 이용해 회원 정보 획득
//        String memberString = sf.getString(id, "");
//        if(memberString != null && memberString.length() > 0){
//            memberModel = new Gson().fromJson(memberString, MemberModel.class);
//        }
//        else{
//            Log.e("Database", "member null");
//        }
//        return memberModel;
//    }
//
//    //회원의 비밀번호 체크
//    public boolean checkMember(String id, String pass){
//        boolean isTrue = false;
//
//        if(id.isEmpty() || pass.isEmpty()){
//            return isTrue;  //실패
//        }
//        //회원 획득
//        MemberModel memberModel = getMember(id);  //호출을 통해 저장되어 있는 멤버를 획득
//        if(memberModel == null){
//            return isTrue;  //실패
//        }
//
//        if(memberModel.getPass().equals(pass)){
//            isTrue = true;  //성공
//        }
//        return  isTrue;
//    }

}
