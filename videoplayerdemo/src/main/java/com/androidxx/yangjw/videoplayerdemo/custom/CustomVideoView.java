package com.androidxx.yangjw.videoplayerdemo.custom;

import android.content.Context;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.VideoView;

/**
 * Created by yangjw on 2016/7/1.
 */
public class CustomVideoView extends VideoView {
    public CustomVideoView(Context context) {
        this(context,null);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     * 测量当前控件的大小
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        Log.d("androidxx", "onMeasure: " + width);
        //设置自定义的大小
        setMeasuredDimension(width,height);
    }
}
