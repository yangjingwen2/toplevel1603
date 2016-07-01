package com.androidxx.yangjw.videoplayerdemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.vov.vitamio.widget.VideoView;

/**
 * Vitamio的基本使用
 */
public class VitamioActivity extends AppCompatActivity {

    public static final String URI = "http://i.snssdk.com/neihan/video/playback/?video_id=8d58f72376a54c5b913a62b4c777c824&quality=480p&line=0&is_gif=0";
    public VideoView mVitamioView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamio);
        initView();
    }

    private void initView() {
        mVitamioView = (VideoView) findViewById(R.id.vitamio_videoview);

        //指定数据源
        Uri uri = Uri.parse(URI);
        mVitamioView.setVideoURI(uri);
        mVitamioView.start();
    }
}
