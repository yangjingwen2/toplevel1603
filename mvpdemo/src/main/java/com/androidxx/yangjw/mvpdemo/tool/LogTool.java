package com.androidxx.yangjw.mvpdemo.tool;

import android.util.Log;

/**
 * Created by yangjw on 2016/6/29.
 */
public class LogTool {

    private static final String TAG = "androidxx";
    private static boolean debug = true;

    public static void log(Class clazz, String log) {
        if (debug) {
            Log.d(TAG, clazz.toString() + "-->" + log);
        }
    }
}
