package com.androidxx.yangjw.coordinatorlayoutdemo;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class IndexActivity extends AppCompatActivity {

    public ListView mListView;
    private String[] strs = {"Coordinator"
            ,"custom behavior"
            ,"appbarlayout"
            ,"CollapsingToolBarLayout"
            ,"一个Demo而已"
            ,"SnackBar的基本使用"
            ,"新的输入框"
            ,"平移动画"
            ,"刷新控件"};
    public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mListView = (ListView) findViewById(R.id.index_list);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,strs);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(IndexActivity.this,MainActivity.class);
                        break;
                    case 1:
                        intent.setClass(IndexActivity.this,CustomBehaviorActivity.class);
                        break;
                    case 2:
                        intent.setClass(IndexActivity.this,AppBarActivity.class);
                        break;
                    case 3:
                        intent.setClass(IndexActivity.this,CollapsingToolBarLayoutActivity.class);
                        break;
                    case 4:
                        intent.setClass(IndexActivity.this,DetailsActivity.class);
                        break;
                    case 5:
                        intent.setClass(IndexActivity.this,SnackBarActivity.class);
                        break;
                    case 6:
                        intent.setClass(IndexActivity.this,EditTextActivity.class);
                        break;
                    case 7:
                        intent.setClass(IndexActivity.this,AnimActivity.class);
                        break;
                    case 8:
                        intent.setClass(IndexActivity.this,RefreshTabActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}
