package com.example.semiproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class MemoFragment extends Fragment {

    Button memoBtnAddMemo;
    ListView memoListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {  //액티비티의 setContentView = 프래그먼트의 inflater >> 확산시켜준다

        //Fragment UI 생성
        View view = inflater.inflate(R.layout.activity_memo_fragment, container, false);

        memoBtnAddMemo = view.findViewById(R.id.MemoBtnAddMemo);
        memoListView = view.findViewById(R.id.MemoListView);

        //버튼 클릭시 메모 작성 페이지로
        memoBtnAddMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //메모 작성 탭 나타남
                Intent intent = new Intent(getContext(), NewMemoTabActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_memo_fragment);
//
//        memoBtnAddMemo = findViewById(R.id.MemoBtnAddMemo);
//        memoListView = findViewById(R.id.MemoListView);
//
//        //버튼 클릭시 메모 리스트 추가
//        memoBtnAddMemo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //TODO
//            }
//        });
//    }
}
