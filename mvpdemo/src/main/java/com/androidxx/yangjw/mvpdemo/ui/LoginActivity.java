package com.androidxx.yangjw.mvpdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.androidxx.yangjw.mvpdemo.R;
import com.androidxx.yangjw.mvpdemo.presenter.UserPresenter;

public class LoginActivity extends AppCompatActivity implements IUserView{

    UserPresenter userPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userPresenter = new UserPresenter(this);
    }

    public void click(View view) {
        userPresenter.login();
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void loginComplete(String result) {

    }
}
