package com.smartmobileproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartmobileproject.function.JsonParsing;

public class ShareActivity extends AppCompatActivity {

    private EditText et_email;
    private Button btn_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        et_email = findViewById(R.id.et_email);
        btn_email = findViewById(R.id.btn_email);

        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String shared_email = et_email.getText().toString();

                JsonParsing jsonParsing = new JsonParsing();
                jsonParsing.execute("https://phpproject-cparr.run.goorm.io/Shareuser.php?r=shared_email"+shared_email);

                startActivity(new Intent(ShareActivity.this, MapActivity.class));
                Toast.makeText(ShareActivity.this, "공유대상이 추가되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });

    }
}