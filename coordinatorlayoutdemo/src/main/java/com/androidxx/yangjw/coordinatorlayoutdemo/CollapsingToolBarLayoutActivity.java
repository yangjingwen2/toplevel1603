package com.androidxx.yangjw.coordinatorlayoutdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * CollapsingToolBarLayout的使用
 * 是ToolBar的一个容器，使ToolBar可以进行收起和展开
 * 学习layout_collapseMode属性
 *
 * 1、<CoordinatorLayout><AppBarLayout><CollapsingToolBarLayout><ToolBar></ToolBar></CollapsingToolBarLayout></AppBarLayout></CoordinatorLayout>
 */
public class CollapsingToolBarLayoutActivity extends AppCompatActivity {

    public Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_tool_bar_layout);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        setTitle("标题");
    }
}
