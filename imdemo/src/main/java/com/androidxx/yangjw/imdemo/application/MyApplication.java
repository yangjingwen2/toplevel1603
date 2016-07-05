package com.androidxx.yangjw.imdemo.application;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

/**
 * Created by yangjw on 2016/7/5.
 * 一个应用程序有且仅有一个Application，代表的就是当前APP的全局环境
 * Application会在所有的activity执行之前执行
 * 在androidmanifest。xml文件中进行配置
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
//        options.setAcceptInvitationAlways(false);

//初始化
        EMClient.getInstance().init(this, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }
}
