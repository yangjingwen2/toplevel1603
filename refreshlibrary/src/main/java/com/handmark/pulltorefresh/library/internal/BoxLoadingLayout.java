package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;

/**
 * Created by yangjw on 2016/7/1.
 */
public class BoxLoadingLayout extends LoadingLayout {

    private AnimationDrawable animationDrawable;
    public BoxLoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
        mHeaderImage.setImageResource(R.drawable.box_anim_list);
        animationDrawable = (AnimationDrawable) mHeaderImage.getDrawable();
    }

    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.box_01;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {

    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {

    }

    @Override
    protected void pullToRefreshImpl() {

    }

    /**
     * 正在刷新
     */
    @Override
    protected void refreshingImpl() {
        animationDrawable.start();
    }

    @Override
    protected void releaseToRefreshImpl() {

    }

    /**
     * 刷新结束后需要重置动画
     */
    @Override
    protected void resetImpl() {
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        mHeaderImage.clearAnimation();
    }
}
