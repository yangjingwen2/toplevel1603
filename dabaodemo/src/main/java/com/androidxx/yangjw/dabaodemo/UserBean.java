package com.androidxx.yangjw.dabaodemo;

import java.io.Serializable;

/**
 * Created by yangjw on 2016/7/5.
 */
public class UserBean implements Serializable{

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
