package com.smartmobileproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;
import com.smartmobileproject.function.GetHttpResponse;
import com.smartmobileproject.function.JsonParsing;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import static com.kakao.usermgmt.StringSet.email;

public class KaKaoLoginActivity extends AppCompatActivity {

    private ISessionCallback mSessionCallback;
    boolean response = false;
    String email, shared_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao);

        mSessionCallback = new ISessionCallback() {

            @Override
            public void onSessionOpened() {
                //로그인 요청
                UserManagement.getInstance().me(new MeV2ResponseCallback() {
                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        //로그인 실패
                        Toast.makeText(KaKaoLoginActivity.this, "로그인 도중에 오류가 발생했습니다.. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        //세션닫힘
                        Toast.makeText(KaKaoLoginActivity.this, "세션이 닫혔습니다.. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(MeV2Response result) {
                        //로그인 성공
                        Intent intent = new Intent(KaKaoLoginActivity.this, MapActivity.class);
                        intent.putExtra("name", result.getKakaoAccount().getProfile().getNickname());
                        intent.putExtra("profileImg", result.getKakaoAccount().getProfile().getProfileImageUrl());
                        intent.putExtra("email", result.getKakaoAccount().getEmail());
                        Log.d("email", result.getKakaoAccount().getEmail());

                        startActivity(intent);

/*
                        JsonParsing jsonParsing = new JsonParsing();
                        jsonParsing.execute("https://phpproject-cparr.run.goorm.io/Kakaouser.php?email="+email);


                        GetHttpResponse getHttpResponse = new GetHttpResponse();
                        getHttpResponse.execute(//구름주소 );*/

                        Toast.makeText(KaKaoLoginActivity.this, "환영합니다!", Toast.LENGTH_SHORT).show();

/*
                        if(response = true) {
                            Intent intent = new Intent(KaKaoLoginActivity.this, MainActivity.class);
                            intent.putExtra("response", response);//파싱한 값을 넘겨

                            KaKaoLoginActivity.this.startActivity(intent);

                        }
                        else {
                            startActivity(new Intent(KaKaoLoginActivity.this, ShareActivity.class));
                        }*/

                    }
                });
            }


            @Override
            public void onSessionOpenFailed(KakaoException exception) {
                Toast.makeText(KaKaoLoginActivity.this, "세션 열기 실패..", Toast.LENGTH_SHORT).show();

            }
        };




        Session.getCurrentSession().addCallback(mSessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();



        //getHashKey();
    }

    public void Upload(String email){

        String serverUrl="https://phpproject-cparr.run.goorm.io/Kakaouser.php?email="+email;

        SimpleMultiPartRequest smpr= new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("hellosuccess",response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("hellofail", String.valueOf(error));

            }
        });

        smpr.addStringParam("email",email);
        Log.d("email",email);


        RequestQueue requestQueue= Volley.newRequestQueue(this);
        Log.d("smpr", String.valueOf(smpr));
        requestQueue.add(smpr);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(mSessionCallback);
    }

    /*
    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }*/
}