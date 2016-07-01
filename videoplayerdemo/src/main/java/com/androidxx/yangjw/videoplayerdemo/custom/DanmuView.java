package com.androidxx.yangjw.videoplayerdemo.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yangjw on 2016/7/1.
 * 弹幕View
 */
public class DanmuView extends View {

    public static final int BEGIN = 900;
    public Paint paint;
    private List<Bullet> bulletList ;
    private Random random;
    public int measuredHeight;
    private long count;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //刷新视图
            invalidate();

        }
    };

    public DanmuView(Context context) {
        this(context,null);
    }

    public DanmuView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DanmuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        random = new Random();
        bulletList = new ArrayList<>();
        paint = new Paint();
        paint.setTextSize(30);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (count % 5 == 0) {
            int baseline = random.nextInt(measuredHeight);
            //创建子弹
            if (count % 20 == 0) {
                Bullet bullet = new Bullet("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈~~", BEGIN, baseline);
                bulletList.add(bullet);
            } else {
                Bullet bullet = new Bullet("这放的什么东西~~", BEGIN, baseline);
                bulletList.add(bullet);
            }
        }

        //循环集合
        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet1 = bulletList.get(i);
            bullet1.move(canvas,paint);
            int startX = bullet1.getStartX();
            if (startX < -100) {
                bulletList.remove(bullet1);
            }
        }
        count++;
        mHandler.sendEmptyMessageDelayed(0,100);

    }

    /**
     * 子弹
     */
    class Bullet {
        private String text;
        private int startX;
        private int baseline;
        private int speed = 10;

        public Bullet(String text, int startX, int baseline) {
            this.text = text;
            this.startX = startX;
            this.baseline = baseline;
            int length = text.length();
            if (length >= 20) {
                speed = 20;
            } else if (length >= 30) {
                speed = 40;
            }
        }

        public int getStartX() {
            return startX;
        }

        public void move(Canvas canvas, Paint paint) {
            startX -= speed;
            canvas.drawText(this.text,startX,baseline,paint);
        }
    }
}
