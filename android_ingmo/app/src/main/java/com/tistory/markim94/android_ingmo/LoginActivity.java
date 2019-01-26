package com.tistory.markim94.android_ingmo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button btnLoginKakao;
    Button btnLoginFacebook;
    Button btnLoginNaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        // 로그인 레이아웃
        layoutLogin();
    }



    void layoutLogin(){

        // 로그인 버튼 객체 참조
        btnLoginKakao = findViewById(R.id.btnLoginKakao);
        btnLoginFacebook = findViewById(R.id.btnLoginFacebook);
        btnLoginNaver = findViewById(R.id.btnLoginNaver);

    }
}
