package com.androidxx.yangjw.videoplayerdemo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;

/**
 * 使用SurfaceView结合MediaPlayer完成视屏播放
 */
public class MediaSurfaceActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener,SurfaceHolder.Callback{

    public static final String URL = "http://i.snssdk.com/neihan/video/playback/?video_id=8d58f72376a54c5b913a62b4c777c824&quality=480p&line=0&is_gif=0";
    private SurfaceView mSurfaceView;
    public MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_surface);
        initView();
    }

    private void initView() {
        mSurfaceView = (SurfaceView) findViewById(R.id.media_surfaceview);
        mediaPlayer = new MediaPlayer();


        //第一部：初始化SurfaceView
        setupSurfaceView();
    }

    private void setupSurfaceView() {
        SurfaceHolder holder = mSurfaceView.getHolder();
        holder.addCallback(this);
    }

    /**
     * 配置MediaPlayer
     * added by yangjw at 2016.7.1  for setup mediaplayer
     * 修改的原因
     * modified by yangjw at 2016.7.5 for
     */
    private void setupMediaPlayer(SurfaceHolder holder) {
        Uri uri = Uri.parse(URL);
        try {
            //重置
            mediaPlayer.reset();
            //设置数据源
            mediaPlayer.setDataSource(this,uri);
            //异步准备
            mediaPlayer.prepareAsync();
            //显示图像
            mediaPlayer.setDisplay(holder);
            //监听异步准备结束的动作
            mediaPlayer.setOnPreparedListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 视屏数据准备完毕
     * @param mp
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        //开始播放
        mp.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //将MediaPlayer加载到的图像显示到SurfaceView上
        //第二步：使用mediaPlayer加载视屏数据
        setupMediaPlayer(holder);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //停止视屏播放
        if (mediaPlayer != null
                && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }
}
