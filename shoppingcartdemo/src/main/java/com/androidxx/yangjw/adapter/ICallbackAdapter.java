package com.androidxx.yangjw.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangjw on 2016/7/9.
 */
public interface ICallbackAdapter {

    View createView(int position,ViewGroup parent);

    void bindView(int position,View view);
}
