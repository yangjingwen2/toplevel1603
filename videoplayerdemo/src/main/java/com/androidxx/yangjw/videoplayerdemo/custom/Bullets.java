package com.androidxx.yangjw.videoplayerdemo.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.androidxx.yangjw.videoplayerdemo.R;

/**
 * Created by yangjw on 2016/5/18.
 * url：androidxx.cn
 * desc：子弹类
 */
public class Bullets {

    public float x,y;
    private Bitmap bitmap;

    public Bullets(Context context,float x, float y) {
        this.x = x;
        this.y = y;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);
    }

    public void move(Canvas canvas,Paint paint) {
        y -= 10;
        canvas.drawBitmap(bitmap,x,y,paint);
    }


}
