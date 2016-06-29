package com.androidxx.yangjw.mvpdemo.presenter;

import android.util.Log;

import com.androidxx.yangjw.mvpdemo.handle.ICallBack;
import com.androidxx.yangjw.mvpdemo.model.IUserMedol;
import com.androidxx.yangjw.mvpdemo.model.LoginModel;
import com.androidxx.yangjw.mvpdemo.model.UserModel;
import com.androidxx.yangjw.mvpdemo.tool.LogTool;
import com.androidxx.yangjw.mvpdemo.ui.IUserView;

/**
 * Created by yangjw on 2016/6/29.
 */
public class UserPresenter implements IUserPresenter,ICallBack {

    private IUserMedol userMedol;
    private IUserView userView;

    public UserPresenter(IUserView userView) {
        this.userView = userView;
        userMedol = new LoginModel();
    }


    @Override
    public void login() {
        String username = userView.getUsername();
        String password = userView.getPassword();
        if ("".equals(username)) {
            LogTool.log(UserPresenter.class,"username is empty");
            return ;
        }
        userMedol.login(username,password,this);
    }

    @Override
    public void loginComplete(String msg) {
        userView.loginComplete(msg);
    }
}
