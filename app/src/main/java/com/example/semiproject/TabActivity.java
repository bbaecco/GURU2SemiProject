package com.example.semiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {

    private TabLayout tabLayout;  //Tab 영역
    private ViewPager viewPager;  //Tab별 표시할 영역

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        tabLayout = findViewById(R.id.TabLayout);
        viewPager = findViewById(R.id.ViewPager);

        //Tab 생성
        tabLayout.addTab(tabLayout.newTab().setText("메모"));
        tabLayout.addTab(tabLayout.newTab().setText("회원정보"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);  //Gravity = 정렬

        //ViewPager 생성
        MyPagerAdopter adopter = new MyPagerAdopter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adopter);  //ViewPager에 Adopter를 등록
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    //atopter가 있어야 프래그먼트가 반응을 함
    class MyPagerAdopter extends FragmentPagerAdapter {
        int tabSize;  //TAB 수

        public MyPagerAdopter(FragmentManager fm, int count) {
            super(fm);
            this.tabSize = count;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MemoFragment();
                case 1:
                    return new InformationFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return this.tabSize;
        }
    }
}
