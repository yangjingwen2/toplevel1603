package com.androidxx.yangjw.eventdispatchdemo.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by yangjw on 2016/7/7.
 */
public class CustomViewPager extends ViewPager {

    public CustomViewPager(Context context) {
        this(context,null);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //判断是否是第一页
        if (getCurrentItem() != 0) {
            //请求DrawerLayout不拦截事件
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.onTouchEvent(ev);
    }
}
