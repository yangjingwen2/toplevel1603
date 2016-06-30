package com.androidxx.yangjw.coordinatorlayoutdemo;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * AppBarLayout 必须放在CoordinatorLayout中
 * layout_scrollFlags
 *
 */
public class AppBarActivity extends AppCompatActivity {

    public AppBarLayout mAppBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        //监听AppBarLayout偏移量的变化
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("androidxx", "onOffsetChanged: " + verticalOffset);
            }
        });
    }
}
