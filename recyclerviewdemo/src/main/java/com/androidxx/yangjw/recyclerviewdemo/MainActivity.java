package com.androidxx.yangjw.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView的使用步骤
 * 1、导包，v7包下的RecyclerView
 * 2、在布局文件中创建一个RecyclerView
 * 3、Activity中初始化RecyclerView
 *    1、创建一个数据源
 *    2、创建布局管理器
 *    3、创建一个适配器
 *    4、关联适配器
 *
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Integer> datas = new ArrayList<>();
    private MyRecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setupDatas();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        //创建一个布局管理器
          //瀑布流布局管理器
        //参数1：表示列数
        //参数2：表示方向：垂直或者水平
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
         //参数2：列数
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        //参数一：上下文对象
        //参数二：线性布局的方向。垂直或者水平
        //参数三：false表示升序，true表示倒序
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //创建一个适配器
        mRecyclerAdapter = new MyRecyclerAdapter();
        //关联适配器视图
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    /**
     * 初始化数据化
     */
    private void setupDatas() {
        for (int i = 0; i < 5; i++) {
            datas.add(R.drawable.a1);
            datas.add(R.drawable.a2);
            datas.add(R.drawable.a3);
            datas.add(R.drawable.a4);
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.home_recyclerview);
    }

    //创建适配器
    //1、创建一个ViewHodler，并且需要继承RecyclerView.ViewHolder
    //2、创建是一个适配器
    class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }

    //创建了一个Adapter
    class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_view, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.mImageView.setImageResource(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas == null ? 0 : datas.size();
        }
    }
}
