package com.androidxx.yangjw.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangjw on 2016/7/9.
 */
public class ConvertAdapter extends AbstractBaseAdapter {

    private ICallbackAdapter callbackAdapter;

    public ConvertAdapter(ICallbackAdapter callbackAdapter) {
        this.callbackAdapter = callbackAdapter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = callbackAdapter.createView(position,parent);
        } else {
            callbackAdapter.bindView(position,convertView);
        }
        return view;
    }
}
