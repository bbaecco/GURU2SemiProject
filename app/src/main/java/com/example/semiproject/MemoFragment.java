package com.example.semiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MemoFragment extends AppCompatActivity {

    Button memoBtnAddMemo;
    ListView memoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_fragment);

        memoBtnAddMemo = findViewById(R.id.MemoBtnAddMemo);
        memoListView = findViewById(R.id.MemoListView);

        //버튼 클릭시 메모 리스트 추가
        memoBtnAddMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }
}
