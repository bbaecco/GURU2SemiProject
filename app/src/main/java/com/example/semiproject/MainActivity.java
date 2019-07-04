package com.example.semiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 로그인 실패 메세지 : 다이얼로그 또는 토스트
        // 회원이 아닌데 로그인 : 회원이 아닙니다
        //리스트를 제이슨으로 저장하고 뽑고
        //사진이 찍힌 경로를 잘 저장하고 있어야 함
        //회원가입한 사람의 메모가 다 보여야 함
        //위치 정보는 아직 안배워서 안해도 됨
        //사진을 안찍으면 저장이 되면 안됨
        //메모가 완성이 될 때마다 리스트뷰가 완성되야 한다
        //상세 보기 화면 > textview 아니고 edittext로 하기
        //삭제할 땐 삭제하시겠습니까? 물어보고 삭제하기
        //위에 탭이 있다고 생각하기 > 메모 탭 / 회원정보 탭
        //회원 정보는 수정할 필요 없고 뿌려만 주면 됨 > 여기서 비밀번호는 보여도 됨

        //로그인 버튼 눌러 메모 작성 화면으로 이동
        Button btnLogin = findViewById(R.id.BtnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TabActivity.class);
                startActivity(intent);
            }
        });

       //회원가입 버튼 눌러 회원가입 화면으로 이동
        Button btnSignin = findViewById(R.id.BtnSignin);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignActivity.class);
                startActivity(intent);
            }
        });


    }
}
