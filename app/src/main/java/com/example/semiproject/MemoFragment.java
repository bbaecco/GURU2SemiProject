package com.example.semiproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.semiproject.DataBase.DataBase;
import com.example.semiproject.Model2.MyItem;

import java.util.ArrayList;
import java.util.List;

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




//    public static  final int SAVE = 1001;
//
//    Button memoBtnAddMemo;
//    ListView memoListView;
//
//    //원본 데이터 추가
//    List<MyItem> items = new ArrayList<>();
//    ListAdapter adapter;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {  //액티비티의 setContentView = 프래그먼트의 inflater >> 확산시켜준다
//
//       // Fragment UI 생성
//        View view = inflater.inflate(R.layout.activity_memo_fragment, container, false);
//
//        memoListView = view.findViewById(R.id.MemoListView);
//
//        //저장된 items 리스트 획득
//        items = DataBase.getInstance(this).loaditems();
//
//        //Adapter 생성 및 적용
//        adapter = new ListAdapter(items, this);
//        //리스트 뷰에 Adapter 설정 > Adapter가 리스트 뷰를 돌면서 원본 데이터를 찍어낸다
//        memoListView.setAdapter(adapter);
//
//        Button memoBtnAddMemo = view.findViewById(R.id.MemoBtnAddMemo);
//        memoBtnAddMemo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Item 추가 액티비티 호출
//                Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
//                //startActivity(intent);
//                startActivityForResult(intent, SAVE);
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {  //requestCode에 SAVE가 같이 실려서 옴
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == SAVE){  //리스트 갱신
//            //DB 데이터 획득
//            items = DataBase.getInstance(getApplicationContext()).loaditems();
//            //Adapter에 원본 데이터 저장
//            adapter.setItems(items);
//            adapter.notifyDataSetChanged();  //리스트 UI 갱신
//        }
//    }
//
//    class ListAdapter extends BaseAdapter {
//
//        List<MyItem> items;  //원본 데이터
//        Context mContext;
//        LayoutInflater inflater;
//
//        //xml의 UI를 그리기 위해선 inflater가 필요
//        public ListAdapter(List<MyItem> items, Context context){
//            this.items = items;
//            this.mContext = context;
//            this.inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);  //개별적인 UI를 확산하기 위해선 inflater 필요
//        }
//
//        public void setItems(List<MyItem> items){
//            this.items = items;
//        }
//
//        @Override
//        public int getCount() {
//            return items.size();
//        }  //원본 데이터의 수만큼 돌면서 getView를 보여줌
//
//        @Override
//        public Object getItem(int i) {
//            return items.get(i);  //i번째를 할당받으면 그냥 그걸 가져온다
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return i;  //크게 의미가 없어서 그냥 파라미터로 받은 index를 리턴
//        }
//
//        @Override
//        public View getView(final int i, View view, ViewGroup viewGroup) {  //getView에 의해 이미지가 찍힌다.
//            //view_item.xml 획득
//            view = inflater.inflate(R.layout.view_item, null);
//
//            //객체 획득
//            ImageView itemImg = view.findViewById(R.id.ItemImg);
//            TextView txtTitle = view.findViewById(R.id.TxtTitle);
//            TextView txtTitleDesc = view.findViewById(R.id.TxtTitleDesc);
//            TextView txtPrice = view.findViewById(R.id.TxtPrice);
//
//            //원본에서 i번째 item획득
//            final MyItem item = items.get(i);
//
//            //원본 데이터를 UI에 적용
//            itemImg.setImageResource(item.getImgId());
//            txtTitle.setText(item.getTitle());
//            txtTitleDesc.setText(item.getSubTitle());
//            txtPrice.setText(item.getPrice());
//
////            //이미지를 클릭했을 때 -> 상세 화면으로 이동
////            itemImg.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View view) {
////                    Intent intent = new Intent(mContext, ItemDetailActivity.class);
////                    intent.putExtra("INDEX", i);  //원본 데이터의 순번
////                    intent.putExtra("ITEM", item);  //상세 표시할 원본 데이터(MyItem.java에서 Serializable 해야 함)
////                    startActivity(intent);
////                }
////            });
//
//            //전체를 클릭했을 때 -> 상세 화면으로 이동
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(mContext, ItemDetailActivity.class);
//                    intent.putExtra("INDEX", i);  //원본 데이터의 순번
//                    intent.putExtra("ITEM", item);  //상세 표시할 원본 데이터(MyItem.java에서 Serializable 해야 함)
//                    startActivity(intent);
//                }
//            });
//
//            return view;  //완성된 UI 리턴
//        }
//    }
}
