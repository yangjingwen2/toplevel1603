package com.androidxx.yangjw.coordinatorlayoutdemo;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CustomBehaviorActivity extends AppCompatActivity implements View.OnClickListener{

    public ImageView mImageView;
    public ImageView mImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_behavior);
        mImageView = (ImageView) findViewById(R.id.behavior_imageview);
        mImageView2 = (ImageView) findViewById(R.id.behavior_imageview2);
        mImageView.setOnClickListener(this);
        mImageView2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ViewCompat.offsetTopAndBottom(v,10);
    }
}
