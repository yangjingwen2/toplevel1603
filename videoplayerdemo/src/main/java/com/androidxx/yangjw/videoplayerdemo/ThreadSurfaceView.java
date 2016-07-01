package com.androidxx.yangjw.videoplayerdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by yangjw on 2016/7/1.
 */
public class ThreadSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    public Paint paint;
    private boolean isStart;
    private int i;

    public ThreadSurfaceView(Context context) {
        this(context,null);
    }

    public ThreadSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ThreadSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }


    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        isStart = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isStart) {
                    Canvas canvas = holder.lockCanvas();
                    canvas.drawText("这是在线程中绘制的文字",10 + i*10,60*i,paint);
                    holder.unlockCanvasAndPost(canvas);
                    try {
                        Thread.sleep(1000);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("androidxx", "run: -------- " );
                }
            }
        }).start();
    }

    /**
     * 当Surface发生改变的回调此方法
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * 当Surface销毁时回调此方法
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
            isStart = false;
    }
}
