package com.androidxx.yangjw.reflectdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    public String name;
    @BindView(R.id.details_content_tv)
    TextView mContentDetailsTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Knife.bind(this);
        mContentDetailsTxt.setText("这是详情界面");
    }
}
