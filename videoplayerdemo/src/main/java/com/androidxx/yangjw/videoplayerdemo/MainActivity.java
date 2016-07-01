package com.androidxx.yangjw.videoplayerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * 目录
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public ListView mIndexListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIndexListView = (ListView) findViewById(R.id.index_list_lv);
        mIndexListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(this,VideoViewActivity.class);
                break;
            case 1:
                intent.setClass(this,FullScreenVideoActivity.class);
                break;
            case 2:
                intent.setClass(this,SurfaceViewActivity.class);
                break;
            case 3:
                intent.setClass(this,CustomSurfaceViewActivity.class);
                break;
         }
        startActivity(intent);
    }
}
