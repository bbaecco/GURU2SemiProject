package com.example.semiproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewMemo_LocationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {  //액티비티의 setContentView = 프래그먼트의 inflater >> 확산시켜준다

        //Fragment UI 생성
        View view = inflater.inflate(R.layout.activity_new_memo__location_fragment, container, false);


        return view;
    }
}
