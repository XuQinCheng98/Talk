package com.example.talk.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.talk.MainActivity;
import com.example.talk.R;
import com.example.talk.service.IMService;
import com.example.talk.utils.StatusTextColor;
import com.example.talk.utils.ThreadUtils;
import com.example.talk.utils.ToastUtils;
import com.google.android.material.textfield.TextInputLayout;
import com.jaeger.library.StatusBarUtil;
import com.shaishavgandhi.loginbuttons.LinkedInButton;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import es.dmoral.toasty.Toasty;


public class LoginActivity extends AppCompatActivity {
    /*HOST为主机IP,PORT为端口*/
    public static final String HOST="159.75.13.217";
    public static final int PORT=5222;
    EditText et_account,et_key;
    LinkedInButton btn_log;
    TextInputLayout account_in,key_in;
    boolean FLAG=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setTransparent(this);
        initView();
        StatusTextColor.Drak(this);
        setInputLenth();
        checkInput();
        initListener();

    }
    private void initListener(){
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*初步检测输入值为否合法*/
                final String account=et_account.getText().toString().trim();
                final String key=et_key.getText().toString().trim();
                if (TextUtils.isEmpty(account)){
                    Toasty.warning(getApplication(),"Account is Empty!").show();
                    return;
                }
                else if (TextUtils.isEmpty(key)){
                    Toasty.warning(getApplication(),"Password is Empty!").show();
                    return;
                }
                ThreadUtils.runThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //创建连接配置对象
                            ConnectionConfiguration config=new ConnectionConfiguration(HOST,PORT);
                            //额外的配置（开发使用）
                            config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);//明文传输
                            config.setDebuggerEnabled(true);//开启调试模式，查看发送内容
                            //创建连接对象
                            XMPPConnection conn=new XMPPConnection(config);
                            //开始连接
                            conn.connect();
                            //连接成功后开始登录
                            conn.login(account,key);
                            //登录成功，提示
                            ToastUtils.Succe_toast(LoginActivity.this,"Login In Successfully!");
                            //*结束自身*//*
                            finish();
                            //跳转主界面
                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            //保存连接对象,用作后面的操作
                            IMService.conn=conn;
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtils.Error_toast(LoginActivity.this,"Login in Failed!");
                        }
                    }
                });

            }
        });
    }
    /*初始化组件*/
    private void initView() {
        account_in=findViewById(R.id.account_in);
        key_in=findViewById(R.id.key_in);
        et_account=findViewById(R.id.et_account);
        et_key=findViewById(R.id.et_key);
        btn_log=findViewById(R.id.btn_log);
    }
    /*输入框自带核验*/
    private void setInputLenth(){
        account_in.setErrorEnabled(true);
        account_in.setCounterEnabled(true);
        account_in.setCounterMaxLength(16);
        key_in.setCounterMaxLength(22);
        key_in.setCounterEnabled(true);
        key_in.setErrorEnabled(true);
    }
    /*输入文本监听器*/
    private boolean checkInput() {

        account_in.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (account_in.getEditText().getText().toString().trim().length() > 16) {
                    account_in.setError("the length of username is illegal!");
                    FLAG = false;
                } else if (key_in.getEditText().getText().toString().trim().length() > 22) {
                    key_in.setError("the length of password is illegal!");
                    FLAG = false;
                }
                else if (account_in.getEditText().getText().toString().trim().length() <= 16){
                    account_in.setError(null);
                }
                else if (key_in.getEditText().getText().toString().trim().length() <= 22){
                    key_in.setError(null);
                }
            }
        });
        return true;
    }
}
