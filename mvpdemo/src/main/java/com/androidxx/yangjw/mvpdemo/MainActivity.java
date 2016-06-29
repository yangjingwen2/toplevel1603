package com.androidxx.yangjw.mvpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidxx.yangjw.mvpdemo.presenter.IUserPresenter;
import com.androidxx.yangjw.mvpdemo.presenter.UserPresenter;
import com.androidxx.yangjw.mvpdemo.ui.IUserView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IUserView{

    @BindView(R.id.login_username_edt)
    EditText mUsernameEdit;
    @BindView(R.id.login_password_edt)
    EditText mPasswordEdit;

    IUserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userPresenter = new UserPresenter(this);
    }

    @OnClick(R.id.login_submit_btn)
    public void signIn(View view) {
        userPresenter.login();
    }

    @Override
    public String getUsername() {
        return mUsernameEdit.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordEdit.getText().toString();
    }

    @Override
    public void loginComplete(String result) {
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
    }
}
