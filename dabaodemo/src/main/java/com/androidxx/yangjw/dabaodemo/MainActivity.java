package com.androidxx.yangjw.dabaodemo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidx";
    private UserBean userBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userBean = new UserBean();
        userBean.setUsername("zhangsan");
        Log.d(TAG, "onCreate: " + userBean.getUsername());

        //获得meta-data标签中的value
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName()
                    , PackageManager.GET_META_DATA);
            Object channel = applicationInfo.metaData.get("CHANNEL");
            Log.d(TAG, "onCreate: channel===" + channel);
            if ("_360".equals(channel)) {
                //360
                Toast.makeText(MainActivity.this, "这是360账户登录的逻辑", Toast.LENGTH_SHORT).show();
            } else if ("xiaomi".equals(channel)) {
                Toast.makeText(MainActivity.this, "这是小米登陆的逻辑", Toast.LENGTH_SHORT).show();
            } else if ("huawei".equals(channel)) {
                Toast.makeText(MainActivity.this, "华为", Toast.LENGTH_SHORT).show();
            }
//            Log.d(TAG, "onCreate: " + name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
