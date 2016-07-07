package com.androidxx.yangjw.eventdispatchdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by yangjw on 2016/7/7.
 */
public class CustomButton extends Button {

    private static final String TAG = "androidxx";

    public CustomButton(Context context) {
        this(context,null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 时间分发
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "Button---->dispatchTouchEvent: ");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "Button---->dispatchTouchEvent:   ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "Button---->dispatchTouchEvent:   ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event);
//        return false;
    }


    /**
     * 时间消费
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "Button---->onTouchEvent: ");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "Button---->onTouchEvent:   ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "Button---->onTouchEvent:   ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
//        return true;//表示当前事件由按钮消费
    }
}
