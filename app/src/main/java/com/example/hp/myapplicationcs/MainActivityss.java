package com.example.hp.myapplicationcs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivityss extends AppCompatActivity {
    private ViewPager vp;
    private List<View> mlist;
    private Button btn;
    private LinearLayout ll;
    private List<TextView> mDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2018ss);

        SharedPreferences setting = getSharedPreferences("setting", 0);
        setting.edit().putBoolean("isFrast",false).commit();



        vp = findViewById(R.id.vp);
        btn = findViewById(R.id.btn);
        ll = findViewById(R.id.ll);
        View view1 = View.inflate(this, R.layout.vp_layout1, null);
        View view2 = View.inflate(this, R.layout.vp_layout2, null);
        View view3 = View.inflate(this, R.layout.vp_layout3, null);
        mlist = new ArrayList<>();
        mlist.add(view1);
        mlist.add(view2);
        mlist.add(view3);
        vp.setAdapter(new myVpAdapter(mlist));

        mDots = new ArrayList<>();

        for (int i = 0; i < mlist.size(); i++) {
            TextView textView = new TextView(this);
            textView.setText("。");
            textView.setTextSize(10);
            textView.setTextColor(Color.BLACK);
            mDots.add(textView);
            ll.addView(textView);
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivityss.this, MainActivitycs.class);
                startActivity(intent);
            }
        });
        mDots.get(0).setTextColor(Color.RED);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(i == mlist.size()-1){
                    btn.setVisibility(View.VISIBLE);
                }else {
                    btn.setVisibility(View.INVISIBLE);
                }

                for (int i1 = 0; i1 < mDots.size(); i1++) {
                    mDots.get(i1).setTextColor(Color.BLACK);
                }

                mDots.get(i).setTextColor(Color.RED);



            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }




    class myVpAdapter extends PagerAdapter {

        List<View> mist;

        public myVpAdapter(List<View> list) {
            mlist = list;
        }

        @Override
        public int getCount() {
            return mlist.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            View view = mlist.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }



    }
}
