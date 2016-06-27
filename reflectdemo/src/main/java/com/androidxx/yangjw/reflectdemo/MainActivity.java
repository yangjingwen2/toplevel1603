package com.androidxx.yangjw.reflectdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";

    @BindView(R.id.home_content_tv)
    public TextView mContentTv;
    @BindView(R.id.home_submit_btn)
    public Button mSubmitBtn;

    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Knife.bind(this);
        initView();
        mContentTv.setText("依赖注入后的结果-升级版");
        Log.d(TAG, "onCreate: " + name);
    }

    private void initView() {

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}
