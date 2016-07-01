package com.androidxx.yangjw.refreshdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public PullToRefreshListView mListView;
    private List<String> datas = new ArrayList<>();
    public ArrayAdapter<String> adapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //刷新完毕
            mListView.onRefreshComplete();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_listview);
        setupdatas();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,datas);
        mListView.setLastUpdatedLabel("上次刷新：2016-7-1");
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        ListView listView = mListView.getRefreshableView();
        listView.setAdapter(adapter);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0);
                    }
                }).start();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

    }

    private void setupdatas() {
        for (int i = 0; i < 30; i++) {
            datas.add("ITEM" + i);
        }
    }
}
