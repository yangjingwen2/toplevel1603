package com.androidxx.yangjw.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CacheActivity extends AppCompatActivity {

    public static final String URL = "http://www.1688wan.com/majax.action?method=getWeekll&pageNo=0";
    private Button mCacheBtn;
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
        setupOkHttp();
        mCacheBtn = (Button) findViewById(R.id.cache_btn);
        mCacheBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
    }

    private void setupOkHttp() {
        //参数1：缓存的路径
        //参数2：缓存大小
        File cacheDir = getCacheDir();
        Cache cache = new Cache(cacheDir, 4 * 1024 * 1024);
        okHttpClient = new OkHttpClient.Builder()
                .cache(cache)//执行缓存的大小和地址
                .build();
    }

    private void request() {
        Request request = new Request.Builder()
                .url(URL)
                .build();
        //执行一次
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("androidxx", "onResponse1: " + response.body().string());
            }
        });
        //执行2次
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("androidxx", "onResponse2: " + response.body().string());
            }
        });
    }
}
