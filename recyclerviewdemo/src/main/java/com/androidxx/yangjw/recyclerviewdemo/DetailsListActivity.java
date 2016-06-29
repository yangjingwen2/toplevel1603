package com.androidxx.yangjw.recyclerviewdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.androidxx.yangjw.recyclerviewdemo.line.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;

/**
 * 点击按钮新增数据，通知适配器刷新试图
 *
 */
public class DetailsListActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    private Button mButton;
    private RecyclerView mRecyclerView;
    private List<Integer> datas = new ArrayList<>();
    public MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_list);
        initView();
        setupRecyclerView();
    }

    private void loadDatas() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "run: 正在执行loadDatas方法");
                datas.add(R.drawable.a1);
                datas.add(R.drawable.a2);
                datas.add(R.drawable.a3);
                datas.add(R.drawable.a3);
                datas.add(R.drawable.a3);
                datas.add(R.drawable.a3);
                datas.add(R.drawable.a3);
                datas.add(R.drawable.a3);
                datas.add(R.drawable.a3);
                datas.add(R.drawable.a3);
                datas.add(R.drawable.a3);
                datas.add(R.drawable.a3);
                datas.add(R.drawable.a3);
                datas.add(R.drawable.a3);
                datas.add(R.drawable.a3);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        myAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void setupRecyclerView() {

        Log.d(TAG, "setupRecyclerView: 这是执行loadDatas方法之后");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        HorizontalDividerItemDecoration build = new HorizontalDividerItemDecoration
                .Builder(this)
                .color(Color.WHITE)
                .build();
        mRecyclerView.addItemDecoration(build);
        myAdapter = new MyAdapter();
        //给所有的Item加上动画
        ScaleInAnimationAdapter slideInRightAnimationAdapter = new ScaleInAnimationAdapter (myAdapter);
        mRecyclerView.setAdapter(slideInRightAnimationAdapter);

        //加载网络数据
        loadDatas();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.details_add_btn);
        mRecyclerView = (RecyclerView) findViewById(R.id.details_list_rv);
        //参数true：表示RecyclerView保持初始化的高度不变，false，表示高度随数据的增加或者减少而发生变化
        //此方法生效的条件是使用notifyItemXXXX这样的方法。如果使用notifyDataSetChange方法则setHasFixedSize方法失效
        mRecyclerView.setHasFixedSize(true);
        //设置动画
        //OvershootInterpolator插补器
        mRecyclerView.setItemAnimator(new SlideInRightAnimator(new OvershootInterpolator(1f)));
        mRecyclerView.getItemAnimator().setAddDuration(500);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas.add(1,R.mipmap.ic_launcher);
                //通知适配器刷新视图
                //更新指定位置的Item视图
                myAdapter.notifyItemInserted(1);
            }
        });
    }

    //1、ViewHolder
    //2、Adatper
    class ViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView mItemImage;

        public ViewHodler(View itemView) {
            super(itemView);
            mItemImage = (ImageView) itemView.findViewById(R.id.item_image);
            mItemImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            try {
                int position = Integer.parseInt(v.getTag().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    class MyAdapter extends RecyclerView.Adapter<ViewHodler> {

        @Override
        public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(DetailsListActivity.this).inflate(R.layout.item_view, null);
            return new ViewHodler(view);
        }

        @Override
        public void onBindViewHolder(ViewHodler holder, int position) {
            holder.mItemImage.setTag(position);
            holder.mItemImage.setImageResource(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas == null ? 0 : datas.size();
        }
    }
}
