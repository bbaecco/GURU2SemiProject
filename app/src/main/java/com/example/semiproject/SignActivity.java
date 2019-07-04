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

    Button signBtnCamera;
    ImageView imgSign;

    //request code
    final int CAMERA_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
    }


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

//    private final String TAG = this.getClass().getSimpleName();  //클래스명 획득
//
////    public static final int VIEW_DETAIL = 100;  //상세화면 식별자 > 액티비티가 여러개면 여러 개 정의 > 식별자(숫자)는 자기가 임의로 설정하면 됨
////    public static final int VIEW_SAVE = 101;  //저장화면 식별자
//
//    EditText signEditID, signEditName, signEditPW, signEditPWconfirm;
//    Button signBtnSign;
//            //btnCancle, btnGet, btnCheck, btnDetail;
////    TextView txtResult;  //결과 표시
//
//    //DB 객체
//    DataBase db;
//
//
//    private static final int MY_PERMISSION_CAMERA = 1111;
//    private static final int REQ_TAKE_PHOTO = 2222;
//    private static final int REQ_TAKE_ALBUM = 3333;
//    private static final int REQ_TAKE_IMAGE_CROP = 4444;
//
//    Button signBtnCamera;
//    ImageView imgSign;
//
//    String mCurrentImageFilePath = null;
//    Uri mProviderUri = null;
//    Uri mPhotoUri = null;
//    Uri mAlbumUri = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign);
//
//        //XML바인딩
//        signBtnCamera = findViewById(R.id.SignBtnCamera);
//        imgSign = findViewById(R.id.ImgSign);
//
//        //사진찍기 버튼 눌러 카메라 작동시키기
//        signBtnCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //카메라 어플 호출
//                captureCamera();
//            }
//        });
//
//        //권한 체크
//        checkPermission();
//
/////////////////////////////////////////////////////////////////////////////////////////////////////////
//        //회원가입 버튼 눌러 메모 작성 화면으로 이동
//        signBtnSign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), TabActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        ///////////////////////////////////////////////////////////////////////////////////////////
//
//
//
//        signEditID = findViewById(R.id.SignEditID);
//        signEditName = findViewById(R.id.SignEditName);
//        signEditPW = findViewById(R.id.SignEditPW);
//        signEditPWconfirm = findViewById(R.id.SignEditPWconfirm);
//
//        signBtnSign = findViewById(R.id.SignBtnSign);
////        btnCancle = findViewById(R.id.BtnCancle);
////        btnGet = findViewById(R.id.BtnGet);
////        btnCheck = findViewById(R.id.BtnCheck);
////        btnDetail = findViewById(R.id.BtnDetail);
//
////        //결과 표시 객체
////        txtResult = findViewById(R.id.TxtResult);
//
//
//        db = DataBase.getInstance(getApplicationContext());
//
//        //회원가입 버튼 이벤트
//        signBtnSign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //저장
//                Log.d(TAG, "id = " + signEditID.getText());
//                Log.d(TAG, "name = " + signEditName.getText());
//                Log.d(TAG, "password = " + signEditPW.getText());
//                Log.d(TAG, "password confirm = " + signEditPWconfirm.getText());
//
//                MemberModel memberModel = new MemberModel();
//                memberModel.setId(signEditID.getText().toString());
//                memberModel.setPass(signEditName.getText().toString());
//                memberModel.setName(signEditPW.getText().toString());
//                memberModel.setBirth(signEditPWconfirm.getText().toString());
//
//
//
//                DataBase db = DataBase.getInstance(getApplicationContext());
////                //저장
////                // DataBase.getInstance(getApplicationContext()).setMember(memberModel);  //위에 DataBase db = DataBase.getInstance(getApplicationContext()); 이 문장 없으면 이렇게 써야 함
////                db.setMember(memberModel);
////
////                //획득
////                //DataBase.getInstance(getApplicationContext()).getMember(editID.getText().toString());  ////위에 DataBase db = DataBase.getInstance(getApplicationContext()); 이 문장 없으면 이렇게 써야 함
////                MemberModel saveeMember = db.getMember(signEditID.getText().toString());
////                Log.e(TAG, "savedModel" + saveeMember.toString());
//
//                //ID, PASS 체크
//                boolean check = db.checkMember(signEditID.getText().toString(), signEditPW.getText().toString());
//                Log.d(TAG, "checkedMember" + check);
//
//
//            }
//        });
//
//
//
////        //회원 정보 획득
////        btnGet.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                //획득
////                //DataBase db = DataBase.getInstance(getApplicationContext());
////                MemberModel savedMember = db.getMember(editID.getText().toString());
////                if(savedMember != null){
////                    Log.e(TAG, "savedMember" + savedMember.toString());
////                    txtResult.setText("회원 정보 획득 : " + savedMember.toString());
////                }
////                else{
////                    txtResult.setText("회원 정보 없음");
////                }
////            }
////        });
//
//    }//End onCreate
//
//    /////////////////////////////////////////////////////////////////////////////////////////////////////
//    // 카메라 어플리케이션 호출
//    private void captureCamera() {
//        String state = Environment.getExternalStorageState();
//
//        if (!Environment.MEDIA_MOUNTED.equals(state)) {
//            return;
//        }
//
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            File photoFile = createFileName(); // 저장할 파일
//            if (photoFile != null) {
//                Uri providerURI = FileProvider.getUriForFile(this, getPackageName(), photoFile);
//                mProviderUri = providerURI;
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, providerURI);
//
//                startActivityForResult(intent, REQ_TAKE_PHOTO);
//            }
//        }
//    }
//
////    // 앨범 어플리케이션 호출
////    private void getAlbum() {
////        Intent intent = new Intent(Intent.ACTION_PICK);
////        intent.setType("image/*");
////        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
////        startActivityForResult(intent, REQ_TAKE_ALBUM);
////    }
//    // 카메라, 앨범등의 처리결과
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        DataBase db = DataBase.getInstance(this);
//
//        switch (requestCode) {
//
//            case REQ_TAKE_PHOTO:
//                if (resultCode == Activity.RESULT_OK) {
//                    //gallaryAddPic();
//
//                    imgSign.setImageURI(mProviderUri); // 사진촬영한 이미지 설정
//
//                } else {
//                    Toast.makeText(this, "사진촬영을 취소하였습니다.", Toast.LENGTH_LONG).show();
//                }
//                break;
////            case REQ_TAKE_ALBUM:
////                if (resultCode == Activity.RESULT_OK) {
////                    File albumFile = createFileName();
////                    mPhotoUri = data.getData();
////                    mAlbumUri = Uri.fromFile(albumFile);
////
////                    imgSign.setImageURI(mPhotoUri);   // 앨범에서 선택한 이미지 설정
////                }
////                break;
//        } // End switch
//    }
//
////    // 생성한 파일을 갤러리에 추가
////    private void gallaryAddPic() {
////        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
////
////        File file = new File(mCurrentImageFilePath);
////        Uri contentUri = Uri.fromFile(file);
////        intent.setData(contentUri);
////        sendBroadcast(intent);
////
////        Toast.makeText(this, "앨범에 사진이 추가되었습니다.", Toast.LENGTH_LONG).show();
////    }
////
//    // 이미지 파일명 생성
//    private File createFileName() {
//        // 현재 "년월일 시분초"를 기준으로 파일명 생성
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String fileName = timeStamp + ".jpg";
//
//        File myDir = new File(Environment.getExternalStorageDirectory() + "/Pictures", "item");
//        if (!myDir.exists()) {
//            myDir.mkdir();
//        }
//
//        File imageFile = new File(myDir, fileName);
//        mCurrentImageFilePath = imageFile.getAbsolutePath();
//
//        return imageFile;
//    }
//
//    //권한 체크 함수(매니패스트의 사용 권한 추가 부분 체크)
//    private void checkPermission(){
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
//                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
//                || ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            //권한 동의 체크
//            if( ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
//
//                DialogUtil.showDialog(this, "알림", "권한이 거부되었습니다. 직접 권한을 허용하삼", "설정",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                //어플 설정으로 이동
//                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                intent.setData(Uri.fromParts("package", getPackageName(), null));
//                                intent.putExtra("package", getPackageName());
//                                startActivity(intent);
//                            }
//                        },
//                        "취소",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                //닫기
//                                finish();
//                            }
//                        });
//            }
//            else {  //앱 실행 후 최초
//                //권한 동의 팝업 표시 요청
//                ActivityCompat.requestPermissions(this, new String[]{
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.CAMERA}, 1111);
//            }
//        }
//
//    }//End CheckPermission
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        switch (requestCode){
//            case 1111:
//                //0 : 권한 허용  -1 : 권한 거부
//                for (int i = 0; i < grantResults.length; i++){
//                    if (grantResults[i] < 0){
//                        Toast.makeText(this, "해당 권한을 활성화 하셔야 합니다.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                break;
//            default:
//        }  //End switch
//    }
}

