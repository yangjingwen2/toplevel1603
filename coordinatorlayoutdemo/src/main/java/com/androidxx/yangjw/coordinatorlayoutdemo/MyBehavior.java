package com.androidxx.yangjw.coordinatorlayoutdemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by yangjw on 2016/6/30.
 */
public class MyBehavior extends CoordinatorLayout.Behavior<View> {

    public MyBehavior() {
    }

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 表示当前指定的控件依赖于另一个控件
     * @param parent
     * @param child 指定此行为的控件
     * @param dependency 依赖的控件
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof CustomImageView;
    }

    /**
     * 当依赖的View发生变化的时候，指定此行为的View也做出某种变化
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

//        float dependencyY = dependency.getTranslationY();
        //改变View垂直方向的偏移量
        ViewCompat.offsetTopAndBottom(child,10);

        return true;
    }
}
