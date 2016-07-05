package com.androidxx.yangjw.dabaodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidx";
    private UserBean userBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userBean.setUsername("zhangsan");
        Log.d(TAG, "onCreate: " + userBean.getUsername());
    }
}
