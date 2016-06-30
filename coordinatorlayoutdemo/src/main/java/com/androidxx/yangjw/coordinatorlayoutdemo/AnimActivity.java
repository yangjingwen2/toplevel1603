package com.androidxx.yangjw.coordinatorlayoutdemo;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AnimActivity extends AppCompatActivity {

    public ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        mImageView = (ImageView) findViewById(R.id.anim1_imageview);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimActivity.this,Anim2Activity.class);
                /*
                参数二：进行平滑移动的图片
                参数三：图片对应的名称
                 */
                ActivityOptions activityOptions =
                        ActivityOptions.makeSceneTransitionAnimation(AnimActivity.this, v, "share");
                startActivity(intent,activityOptions.toBundle());
            }
        });
    }
}
