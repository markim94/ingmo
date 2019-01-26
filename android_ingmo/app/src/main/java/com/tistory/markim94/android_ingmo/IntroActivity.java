package com.tistory.markim94.android_ingmo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
* create by brother jun
*
* */

public class IntroActivity extends AppCompatActivity {

    // 인트로 실행시간을 설정하기 위한 handler
    Handler handler = new Handler();
    // 쓰레드
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // LoginActivity 전환
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            // Intro Activity 화면 제거
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 3000ms 이후 runnable 객체 수행
        handler.postDelayed(runnable, 3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 예약 취소
        handler.removeCallbacks(runnable);
    }


}
