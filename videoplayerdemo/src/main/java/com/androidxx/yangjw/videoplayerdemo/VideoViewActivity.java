package com.androidxx.yangjw.videoplayerdemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * 注意：播放网络视频，需要设置网络权限
 * 能播放的视屏格式：
 * mp4、3gp
 * 使用步骤：
 * 1、初始化VideoView
 * 2、设置视屏播放的地址：可以是网络地址、可以是本地地址
 * 3、start方法，开始视屏播放
 */
public class VideoViewActivity extends AppCompatActivity {

    public static final String VIDEO_URL = "http://i.snssdk.com/neihan/video/playback/?video_id=8d58f72376a54c5b913a62b4c777c824&quality=480p&line=0&is_gif=0";
    public VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        initView();
        //设置视屏播放的地址
        setupDatas();
    }

    private void setupDatas() {
        //设置视屏播放的地址
        Uri uri = Uri.parse(VIDEO_URL);
        mVideoView.setVideoURI(uri);
        //设置一个控制条
        MediaController mediaController = new MediaController(this);
        mVideoView.setMediaController(mediaController);
        //开始播放
        mVideoView.start();
    }

    private void initView() {
        mVideoView = (VideoView) findViewById(R.id.video_view);
    }
}
