package com.androidxx.yangjw.videoplayerdemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.androidxx.yangjw.videoplayerdemo.custom.CustomVideoView;

public class FullScreenVideoActivity extends AppCompatActivity {

    public static final String URL = "http://i.snssdk.com/neihan/video/playback/?video_id=8d58f72376a54c5b913a62b4c777c824&quality=480p&line=0&is_gif=0";
    public CustomVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_video);
        initView();
    }

    private void initView() {
        mVideoView = (CustomVideoView) findViewById(R.id.custom_video_view);
        //设置视屏播放地址
        Uri uri = Uri.parse(URL);
        mVideoView.setVideoURI(uri);
        //start方法开始播放
        mVideoView.start();
    }
}
