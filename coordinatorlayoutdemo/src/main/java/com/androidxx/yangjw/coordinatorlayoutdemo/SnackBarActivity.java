package com.androidxx.yangjw.coordinatorlayoutdemo;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 *
 */
public class SnackBarActivity extends AppCompatActivity {

    public CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
    }

    public void click(View view) {
        //SnackBar 相当于Toast
        //参数一：是一个父容器，最好是协调者布局. 不能使用ScrollView作为Snackbar的第一个参数
        //参数二：显示的文本
        //参数三：显示的时间
        //
        Snackbar.make(mCoordinatorLayout,"这是文本",Snackbar.LENGTH_LONG)
                .setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            //
                    }
                })
                .show();
    }
}
