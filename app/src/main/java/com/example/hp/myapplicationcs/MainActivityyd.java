package com.example.hp.myapplicationcs;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivityyd extends AppCompatActivity {

    private TextView tv;
    private CountDownTimer countDownTimer;
    private int anInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2018yindao);
        tv = findViewById(R.id.yind);
        Log.e("aa","dd");
        countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv.setText(millisUntilFinished/1000 + " S");
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(MainActivityyd.this,MainActivitycs.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}