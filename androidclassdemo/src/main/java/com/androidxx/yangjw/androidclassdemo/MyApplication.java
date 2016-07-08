package com.androidxx.yangjw.androidclassdemo;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by yangjw on 2016/7/8.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);//正式上线，此句代码应该删掉，或者设置为false
    }
}
