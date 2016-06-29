package com.androidxx.yangjw.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SwitchActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    public MyAdapter myAdapter;
    private List<Integer> datas = new ArrayList<>();

    private boolean isList = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        initView();
        setupRecyclerView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.switch_recyclerview);
    }

    private void setupRecyclerView() {
        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //创建适配器
        myAdapter = new MyAdapter();
        mRecyclerView.setAdapter(myAdapter);
        //加载数据
        loadData();
    }

    private void loadData() {
        datas.add(R.drawable.a1);
        datas.add(R.drawable.a2);
        datas.add(R.drawable.a3);
        datas.add(R.drawable.a4);
        myAdapter.notifyDataSetChanged();
    }

    public void click(View view) {
        if (isList) {
            isList = false;
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        } else {
            isList = true;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(linearLayoutManager);
        }

    }

    //创建ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mItemImage;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemImage = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }

    //创建适配器
    class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        public static final int HEADER_VIEW = 0;
        //参数：item的索引
        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return HEADER_VIEW;
            }
            return position;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == HEADER_VIEW) {
                View headerView = LayoutInflater.from(SwitchActivity.this).inflate(R.layout.header_view, parent,false);
                return new ViewHolder(headerView);
            }
            View view = LayoutInflater.from(SwitchActivity.this).inflate(R.layout.item_view, null);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (position != 0) {
                 holder.mItemImage.setImageResource(datas.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return datas == null ? 0 :datas.size();
        }
    }
}
