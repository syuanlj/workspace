package com.user.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.syuan.commons.ActivityUtils;
import com.syuan.network.EasyShopClient;
import com.syuan.yitao_zx.R;
import com.user.register.RegisterActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    EditText et_userName;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ActivityUtils activityUtils;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);

        init();
    }

    private void init() {
        //给左上角加上一个返回图标
        setSupportActionBar(toolbar);
        //toolbar显示返回按钮,但是点击效果需要“实现菜单点击事件”
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //给EditText添加监听
        et_userName.addTextChangedListener(textWatcher);
        et_pwd.addTextChangedListener(textWatcher);

    }

    //toolbar显示返回按钮,但是点击效果需要“实现菜单点击事件”
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //如果点击的是返回，则finish
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    //EditText监听
    private TextWatcher textWatcher = new TextWatcher() {
        //这里的s表示改变之前的内容，通常start和count组合，可以在s中读取本次改变字段中被改变的内容。
        //而after表示改变后新的内容的数量
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        //这里的s表示改变之后的内容，通常start和count组合，可以在s中读取本次改变字段中新的内容。
        //而before表示被改变的内容的数量。
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        //表示最终内容
        @Override
        public void afterTextChanged(Editable s) {
            username = et_userName.getText().toString();
            password = et_pwd.getText().toString();
            //判断用户民和密码是否为空
            boolean canLogin = !(TextUtils.isEmpty(username) || TextUtils.isEmpty(password));
            btn_login.setEnabled(canLogin);
        }
    };

    //登录按钮点击事件
    @OnClick({R.id.btn_login,R.id.tv_register})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_login:
                // TODO: 2017/6/23 0023 执行登录的网络请求
                activityUtils.showToast("执行登录的网络请求");

//                OkHttpClient okHttpClient=new OkHttpClient();
//
//                RequestBody requestBody=new FormBody.Builder()
//                        .add("username",username)
//                        .add("password",password)
//                        .build();
//                Request request=new Request.Builder()
//                        .url("http://wx.feicuiedu.com:9094/yitao/UserWeb?method=login")
//                        .post(requestBody)
//                        .build();
                Call call= EasyShopClient.getInstance().login(username,password);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("aaa", "网络连接失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("aaa", "网络连接成功");
                        if (response.isSuccessful()) {
                            Log.e("aaa", "服务器成功响应");
                        } else {
                            Log.e("aaa", "请求失败");
                        }
                    }
                });
                break;
            case R.id.tv_register:
                activityUtils.startActivity(RegisterActivity.class);
                break;
        }
    }
}
