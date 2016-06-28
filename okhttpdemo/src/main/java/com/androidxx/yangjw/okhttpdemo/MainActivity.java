package com.androidxx.yangjw.okhttpdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * 介绍OkHttp：
 * 1、Get请求使用
 * 2、Post请求的使用
 * 3、上传和下载
 * 4、缓存
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.index_list);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        switch (position) {
            case 0: //Get请求
                intent.setClass(this,GetActivity.class);
                break;
        }
        startActivity(intent);
    }
}
