package com.androidxx.yangjw.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class InflaterActivity extends AppCompatActivity {

    public RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflater);
        layout = (RelativeLayout) findViewById(R.id.root_view);
        LayoutInflater.from(this).inflate(R.layout.header_view,layout,true);

    }
}
