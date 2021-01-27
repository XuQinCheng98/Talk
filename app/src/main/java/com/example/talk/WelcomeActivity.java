package com.example.talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.example.talk.Login.LoginActivity;
import com.example.talk.utils.StatusTextColor;
import com.example.talk.utils.ThreadUtils;
import com.jaeger.library.StatusBarUtil;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        StatusTextColor.Drak(this);
        StatusBarUtil.setTransparent(this);
        ThreadUtils.runThread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}