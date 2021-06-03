package com.smartmobileproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class SubActivity extends AppCompatActivity {

    private String strNick, strProfileImg, strEmail;
    private Button btn_start;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        strNick = intent.getStringExtra("name");
        strEmail = intent.getStringExtra("email");
        strProfileImg = intent.getStringExtra("profileImg");

        TextView tv_nick = findViewById(R.id.tv_nickName);
        TextView tv_email = findViewById(R.id.tv_email);
        ImageView iv_profile = findViewById(R.id.iv_profile);
        btn_start = findViewById(R.id.btn_start);

        tv_nick.setText(strNick);
        tv_email.setText(strEmail);
        Glide.with(this).load(strProfileImg).into(iv_profile);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SubActivity.this, ShareActivity.class));

            }
        });




        //로그아웃
        findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        //로그아웃 성공 시
                        finish(); //현재 액티비티 종료
                    }
                });
            }
        });
    }
}