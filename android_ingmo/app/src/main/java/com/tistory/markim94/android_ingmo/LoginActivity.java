package com.tistory.markim94.android_ingmo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.tistory.markim94.android_ingmo.Connections.PostConnection;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Map;

/*
 * create by brother jun
 *
 * */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    com.kakao.usermgmt.LoginButton btnLoginKakao;
    LoginButton btnLoginFacebook;
    Button btnLoginNaver;

    // facebook callback
    private CallbackManager callbackManager;

    // kakao callback
    private SessionCallback callback;

    // 카카오 정보를 담을 컬렉션 map(key, value)
    Map<String,String> kakao_properties;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        // 로그인 레이아웃
        layoutLogin();

        /**
         * kakaotalk Login
         */
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);

        /**
         * facebook Login
         */
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        // 읽어오는 권한 (이메일)
        btnLoginFacebook.setReadPermissions("email");

        // login result에 거부된 권한과 accesstoken이 날라옴.
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        //loginResult.getAccessToken();
                        // App code
                        Log.d(TAG,"onSucces LoginResult="+loginResult);
                        // accesstoken을 db로 보낸다
                        // 이후 redirectionMain 인텐트 전환
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.d(TAG,"onCancel");
                        // error코드 확인후 팝업 윈도우 메시지 보낼것.
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.d(TAG,"onError");
                        // error코드 확인후 팝업 윈도우 메시지 보낼것.
                    }
                });


        // kakaotalk Login : 사용자 정보 요청
        requestMe();

    }

    // kakao talk 세션성공시, 진행하는 메소드
    public void redirectLoginActivity() {
        String token = Session.getCurrentSession().getAccessToken();
        if (token.length() > 0) {
            // 1. 정해진 서버 api주소에 accessToken을 보낸다.
            PostConnection postConnection = new PostConnection(getResources().getString(R.string.ingmo_server_addr) + "login/kakao"); // api 주소 정해지면 수정할 것
            postConnection.addParam("accessToken", token);
            try {
                postConnection.execute().get();
                // 2. accessToken이 올바르게 전송되고 signup_req 신호가 반송되면, memoListActivity로 화면 전환(로그인 성공)
                if (postConnection.resultObj.getString("result").contentEquals("signup_req")) {
                    Log.e("server logged in", postConnection.resultObj.toString());
                    Intent intent = new Intent(LoginActivity.this, MemoListActivity.class);
                    startActivity(intent);
                    finish();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }

        } else {
            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }


    /**
     * 로그인 버튼 레이아웃
     */
    void layoutLogin(){

        btnLoginKakao = findViewById(R.id.btnLoginKakao);
        btnLoginFacebook = findViewById(R.id.btnLoginFacebook);
        btnLoginNaver = findViewById(R.id.btnLoginNaver);

    }

    // kakaotalk Login: 정보 얻어오기
    public void requestMe() {
        //유저의 정보를 받아오는 함수

        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.e(TAG, "error message=" + errorResult);
//                super.onFailure(errorResult);
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {

                Log.d(TAG, "onSessionClosed1 =" + errorResult);
            }

            @Override
            public void onNotSignedUp() {
                //카카오톡 회원이 아닐시
                Log.d(TAG, "onNotSignedUp ");

            }
            @Override
            public void onSuccess(UserProfile result) {
                Log.e("UserProfile", result.toString());
                Log.e("UserProfile", result.getId() + "");
            }
        });
    }


    // kakaotalk session
    class SessionCallback implements ISessionCallback {


        @Override
        public void onSessionOpened() {
            UserManagement.requestMe(new MeResponseCallback() {

                @Override
                public void onFailure(ErrorResult errorResult) {
                    String message = "failed to get user info. msg=" + errorResult;

                    ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                    if (result == ErrorCode.CLIENT_ERROR_CODE) {
                        //에러로 인한 로그인 실패
//                        finish();
                    } else {
                        //redirectMainActivity();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                }

                @Override
                public void onNotSignedUp() {

                }

                @Override
                public void onSuccess(UserProfile userProfile) {
                    //로그인에 성공하면 로그인한 사용자의 일련번호, 닉네임, 이미지url등을 리턴합니다.
                    //사용자 ID는 보안상의 문제로 제공하지 않고 일련번호는 제공합니다.

//                    Log.e("UserProfile", userProfile.toString());
//                    Log.e("UserProfile", userProfile.getId() + "");



                    long number = userProfile.getId();

                    // 로그인 성공시 액티비티 전환
                    redirectLoginActivity();


                }
            });

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            // 세션 연결이 실패했을때
            // 어쩔때 실패되는지는 테스트를 안해보았음 ㅜㅜ

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // facebook 정보 받아오기
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }





}


