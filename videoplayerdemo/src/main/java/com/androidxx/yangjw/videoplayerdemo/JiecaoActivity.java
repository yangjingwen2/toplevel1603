package com.androidxx.yangjw.videoplayerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class JiecaoActivity extends AppCompatActivity {

    public static final String URL="http://i.snssdk.com/neihan/video/playback/?video_id=8d58f72376a54c5b913a62b4c777c824&quality=480p&line=0&is_gif=0";
    public static final String URL2="http://player.youku.com/player.php/sid/XMTYyNDU2NTUzMg==/v.swf";
    public JCVideoPlayerStandard mJcVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiecao);
        mJcVideoView = (JCVideoPlayerStandard) findViewById(R.id.jiecao_videoview);
        /**
         * 参数一：视屏的地址
         * 参数二：视频的标题
         */
        mJcVideoView.setUp(URL2,"绝情谷");
    }
}
