package com.androidxx.yangjw.adapter;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by yangjw on 2016/7/9.
 */
public abstract class AbstractBaseAdapter extends BaseAdapter {

    private List<?> datas ;

    public List<?> getDatas() {
        return datas;
    }

    public void setDatas(List<?> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
