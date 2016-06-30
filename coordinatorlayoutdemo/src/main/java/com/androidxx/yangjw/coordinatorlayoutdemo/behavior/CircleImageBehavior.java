package com.androidxx.yangjw.coordinatorlayoutdemo.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yangjw on 2016/6/30.
 */
public class CircleImageBehavior extends CoordinatorLayout.Behavior<CircleImageView> {
    public CircleImageBehavior() {
    }

    public CircleImageBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    int i = 10;
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
//        float translationY = dependency.getTranslationY();
//        float y = dependency.getY();
//        Log.d("androidxx", translationY +" ::::::  " + y);
//        int height = dependency.getHeight();
//        ViewCompat.offsetLeftAndRight(child,(int)(height - dependency.getY()));
        child.setY(dependency.getY()+10);
        child.setX(dependency.getWidth()/2 - dependency.getY() - child.getWidth()/2);
        return true;
    }
}
