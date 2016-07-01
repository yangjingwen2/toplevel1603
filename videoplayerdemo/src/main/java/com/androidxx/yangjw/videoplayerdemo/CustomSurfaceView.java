package com.androidxx.yangjw.videoplayerdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by yangjw on 2016/7/1.
 *
 * 1、获得一个Holder
 * 2、等待SurfaceView中的Holder初始化完毕，SurfaceHolder.Callback
 * 3、在onCreateSurface方法中开始完成绘制任务
 *    绘制获得画布lockCanvas，提交画布unLockCanvasAndPost
 *
 */
public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback{


    public Paint mPaint;

    public CustomSurfaceView(Context context) {
        this(context,null);
    }

    public CustomSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas canvas = holder.lockCanvas();
        canvas.drawCircle(300,300,200,mPaint);
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
