package com.androidxx.yangjw.mvpdemo.model;

import com.androidxx.yangjw.mvpdemo.handle.ICallBack;

/**
 * Created by yangjw on 2016/6/29.
 */
public interface IUserMedol {
    /**
     * 登陆
     * @param username
     * @param password
     */
    public void login(String username, String password, ICallBack callBack);
}
