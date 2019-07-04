package com.example.semiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        //사진찍기 버튼 눌러 카메라 작동시키기
        Button signBtnCamera = findViewById(R.id.SignBtnCamera);
        signBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //TODO
            }
        });

        //회원가입 버튼 눌러 메모 작성 화면으로 이동
        Button signBtnSign = findViewById(R.id.SignBtnSign);
        signBtnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TabActivity.class);
                startActivity(intent);
            }
        });
    }
}
