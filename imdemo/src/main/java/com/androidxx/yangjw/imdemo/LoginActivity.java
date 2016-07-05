package com.androidxx.yangjw.imdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public EditText mUsernameEdt;
    public EditText mPasswordEdt;
    public Button mSubmitBtn;
    public Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mUsernameEdt = (EditText) findViewById(R.id.login_username_edt);
        mPasswordEdt = (EditText) findViewById(R.id.login_password_edt);
        mSubmitBtn = (Button) findViewById(R.id.login_submit_btn);
        mRegisterBtn = (Button) findViewById(R.id.login_register_btn);
        mSubmitBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_submit_btn:
                signIn();
                break;
            case R.id.login_register_btn:
                register();
                break;
        }
    }

    //完成注册的功能
    private void register() {
        final String username = mUsernameEdt.getText().toString();
        final String password = mPasswordEdt.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //注册失败会抛出HyphenateException
                try {
                    EMClient.getInstance().createAccount(username, password);//同步方法
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "注册失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    //完成登陆的逻辑
    private void signIn() {
        String username = mUsernameEdt.getText().toString();
        String password = mPasswordEdt.getText().toString();
        //环信的登陆
        EMClient.getInstance().login(username, password, new EMCallBack() {
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //登录成功
                        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,FriendsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

            }

            @Override
            public void onError(final int i,final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //登陆失败
                        Toast.makeText(LoginActivity.this, "失败：" + i + ":::" + s, Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onProgress(int i, String s) {
                //正在登陆
            }
        });
    }
}
