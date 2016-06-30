package com.androidxx.yangjw.coordinatorlayoutdemo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RefreshTabActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    public MyAdapter myAdapter;
    public SwipeRefreshLayout mRefreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_tab);
        mRefreshView = (SwipeRefreshLayout) findViewById(R.id.refresh_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter();
        mRecyclerView.setAdapter(myAdapter);

        mRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //执行请求网络，重新获取数据的代码
            }
        });

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public Button mBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            mBtn = (Button) itemView.findViewById(R.id.item_btn);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RefreshTabActivity.this).inflate(R.layout.item_view, null);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mBtn.setText("安妮" + position);
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }
}
