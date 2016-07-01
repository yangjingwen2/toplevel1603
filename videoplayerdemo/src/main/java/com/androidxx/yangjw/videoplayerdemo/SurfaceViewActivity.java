package com.androidxx.yangjw.videoplayerdemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * SurfaceView介绍
 * 主要用来做一些耗时的界面绘制
 * 步骤：
 * 1、初始化SurfaceView
 * 2、呼叫秘书 （Holder）:获得Holder对象
 * 3、等待秘书到来 ： 等待Holder初始化完毕
 * 4、分配任务
 * 5、秘书做任务
 * 6、将做完的事情结果反馈给SurfaceView
 */
public class SurfaceViewActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    public SurfaceView mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        initView();
    }

    private void initView() {
        mSurfaceView = (SurfaceView) findViewById(R.id.surface_view);
        SurfaceHolder holder = mSurfaceView.getHolder();
        //等待Holder初始化完毕
        holder.addCallback(this);
    }

    /**
     * Holder如果初始化好了，回调此方法
     * @param holder  秘书：Holder初始化完毕
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
            //秘书开始做事
        //任务：画一个园
        //获得画布
        Canvas canvas = holder.lockCanvas();
        //创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);//消除锯齿
        //在画布上画一个圆
        canvas.drawCircle(200,200,150,paint);
        //将Canvas显示到SurfaceView上
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
