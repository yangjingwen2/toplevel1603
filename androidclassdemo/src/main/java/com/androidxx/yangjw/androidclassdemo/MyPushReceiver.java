package com.androidxx.yangjw.androidclassdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by yangjw on 2016/7/8.
 *
 * 接收极光推送的一些动作
 */
public class MyPushReceiver extends BroadcastReceiver {
    private static final String TAG = "androidxx";

    public static final String ACTION = "cn.jpush.android.intent.NOTIFICATION_OPENED";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: " + intent.getAction());
        if (ACTION.equals(intent.getAction())) {
            Intent intent2 = new Intent(context,MainActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);
        }
    }
}
