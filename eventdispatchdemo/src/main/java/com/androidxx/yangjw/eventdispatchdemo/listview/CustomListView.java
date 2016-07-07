package com.androidxx.yangjw.eventdispatchdemo.listview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by yangjw on 2016/7/7.
 */
public class CustomListView extends ListView {

    public int itemPosition;
    public float startX;
    public float startY;
    public View childView;
    private boolean islocked;

    public CustomListView(Context context) {
        this(context,null);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float distanceX = startX - ev.getX();
                if (distanceX < -100) {
                    //向右滑动
                    islocked = false;
                    childView.setBackgroundColor(Color.TRANSPARENT);
                    if (distanceX>-200) {
                        ViewCompat.offsetLeftAndRight(childView,5);
                    }
                } else if (distanceX > 100) {
                    //向左滑动
                    childView.setBackgroundColor(Color.RED);
                    TextView textView = (TextView) childView;
                    textView.setText("左左左左左");
                    if (distanceX<200) {
                        ViewCompat.offsetLeftAndRight(childView,-5);
                    }
                    islocked = true;
                }
                break;
            case MotionEvent.ACTION_DOWN:
                //获得起始点坐标
                startX = ev.getX();
                startY = ev.getY();
                //通过坐标获取item的索引
                itemPosition = pointToPosition((int)startX,(int)startY);
                childView = getChildAt(itemPosition);
                break;
            case MotionEvent.ACTION_UP:
//                View view = getChildAt(itemPosition);
//                view.setBackgroundColor(Color.RED);
                break;
        }

        if (islocked) {
            return true;
        }
        return super.onTouchEvent(ev);
    }
}
