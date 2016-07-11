package com.androidxx.yangjw.baidudemo;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by yangjw on 2016/7/11.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }
}
