package com.androidxx.yangjw.okhttplibrarydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://www.1688wan.com/majax.action?method=bdxqs&pageNo=0";
    public static final String URL_SEARCH = "http://www.1688wan.com/majax.action?method=searchGift";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("key","热血");
        OkHttpTool.newInstance()
                .start(URL_SEARCH)
                .post(stringStringHashMap)
                .callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
