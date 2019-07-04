package com.example.semiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.semiproject.DataBase.DataBase;
import com.example.semiproject.Model2.MemberModel;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();  //클래스명 획득

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //로그인 버튼 눌러 메모 작성 화면으로 이동
        Button btnLogin = findViewById(R.id.BtnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), TabActivity.class);
//                startActivity(intent);

                //DB에 저장된 아이디, 패스워드와 로그인 화면에서 입력받은 아이디, 패스워드가 같으면 로그인 토스트 띄우고 메모 화면으로
                //다르면 다시 입력받기 토스트

                DataBase db = DataBase.getInstance(getApplicationContext());
                MemberModel memberModel = new MemberModel();

                //ID, PASS 체크
                boolean check = db.checkMember(memberModel.getId(), memberModel.getPass());
                Log.d(TAG, "checkedMember" + check);

                if(check == true){
                    Toast.makeText(getApplicationContext(), "로그인 되었습니다.", Toast.LENGTH_SHORT).show();

                    //탭 액티비티로 넘어가기
                    Intent intent = new Intent(getApplicationContext(), TabActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "아이디, 패스워드가 알맞지 않습니다. 다시 확인하세요", Toast.LENGTH_SHORT).show();
                }
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
