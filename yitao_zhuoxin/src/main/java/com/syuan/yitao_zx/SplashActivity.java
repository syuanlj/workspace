package com.syuan.yitao_zx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.syuan.commons.ActivityUtils;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        activityUtils = new ActivityUtils(this);

        // TODO: 2017/6/23 0023  判断用户是否登录，自动登录
        // TODO: 2017/6/23 0023  环信登录相关（账号冲突踢出）

        //1.5s跳转到主页
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //跳转到主页
                activityUtils.startActivity(MainActivity.class);
                finish();
            }
        }, 1500);
    }
}
