package com.androidxx.yangjw.videoplayerdemo.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.androidxx.yangjw.videoplayerdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjw on 2016/5/18.
 * url：androidxx.cn
 * desc：TODO
 */
public class PlaneView extends View {
    private Paint mPaint;
    private Bitmap bitmap;
    private float planeX;
    private float planeY;
    private Context context;
    private int count;

    private List<Bullets> bulletArray = new ArrayList<>();

    public PlaneView(Context context) {
        this(context, null);
    }

    public PlaneView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaneView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.myplane);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                planeX = event.getX();
                planeY = event.getY();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,planeX-33,planeY-40,mPaint);

        if (count % 5 == 0) {
            Bullets bullet = new Bullets(context, planeX - 33, planeY);
            bulletArray.add(bullet);
        }

        for (int i = 0; i < bulletArray.size(); i++) {
            Bullets bullets = bulletArray.get(i);
            bullets.move(canvas,mPaint);
            if (bullets.y < 0) {
                bulletArray.remove(i);
            }
        }
        count ++;
        invalidate();//刷新试图
        //postInvalidate();//在工作线程中更新视图
    }
}
