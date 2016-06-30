package com.androidxx.yangjw.coordinatorlayoutdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class IndexActivity extends AppCompatActivity {

    public ListView mListView;
    private String[] strs = {"Coordinator","custom behavior"};
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
                }
                startActivity(intent);
            }
        });
    }
}
