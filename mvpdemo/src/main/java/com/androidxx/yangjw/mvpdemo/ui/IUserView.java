package com.androidxx.yangjw.mvpdemo.ui;

/**
 * Created by yangjw on 2016/6/29.
 */
public interface IUserView {

    /**
     * 获取用户输入的密码和用户名
     * @return
     */
    public String getUsername();

    public String getPassword();

    public void loginComplete(String result);
}
