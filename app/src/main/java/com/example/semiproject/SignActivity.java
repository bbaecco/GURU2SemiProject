package com.example.semiproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.semiproject.DataBase.DataBase;
import com.example.semiproject.Model2.MemberModel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SignActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();  //클래스명 획득

    Button signBtnCamera;
    ImageView imgSign;

    //request code
    final int CAMERA_REQUEST_CODE = 1;


    EditText signEditID, signEditName, signEditPW, signEditPWconfirm;
    Button signBtnSign;

    //DB 객체
    DataBase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);


        signEditID = findViewById(R.id.SignEditID);
        signEditName = findViewById(R.id.SignEditName);
        signEditPW = findViewById(R.id.SignEditPW);
        signEditPWconfirm = findViewById(R.id.SignEditPWconfirm);

        signBtnSign = findViewById(R.id.SignBtnSign);

        db = DataBase.getInstance(getApplicationContext());


        //카메라
        signBtnCamera = (Button)findViewById(R.id.SignBtnCamera);
        imgSign = (ImageView)findViewById(R.id.ImgSign);

        signBtnCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(IsCameraAvailable()){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                }
            }
        });

        //회원가입 버튼 이벤트
        signBtnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBase db = DataBase.getInstance(getApplicationContext());

                //저장
                Log.d(TAG, "id = " + signEditID.getText());
                Log.d(TAG, "name = " + signEditName.getText());
                Log.d(TAG, "password = " + signEditPW.getText());
                Log.d(TAG, "password confirm = " + signEditPWconfirm.getText());

                //입력받은 내용 저장
                MemberModel memberModel = new MemberModel();
                memberModel.setId(signEditID.getText().toString());
                memberModel.setPass(signEditName.getText().toString());
                memberModel.setName(signEditPW.getText().toString());

                //패스워드 동일 여부 체크
                if(signEditPW.getText().toString().equals(signEditPWconfirm.getText().toString())){  //같으면
                    Toast.makeText(getApplicationContext(), "패스워드가 동일합니다. 회원이 되신 것을 환영합니다.", Toast.LENGTH_LONG).show();
                    finish();

                    //DB에 저장
                    DataBase.getInstance(getApplicationContext()).setMember(memberModel);  //위에 DataBase db = DataBase.getInstance(getApplicationContext()); 이 문장 없으면 이렇게 써야 함
                    db.setMember(memberModel);
                }
                else{  //다르면
                    Toast.makeText(getApplicationContext(), "패스워드가 동일하지 않습니다. 다시 확인바랍니다.", Toast.LENGTH_LONG).show();
                }

            }
        });

    }  //End OnCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CAMERA_REQUEST_CODE){
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap)bundle.get("data");
            imgSign.setImageBitmap(bitmap);
        }
    }

    //카메라 유무
    public boolean IsCameraAvailable(){
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}