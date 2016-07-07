package com.androidxx.yangjw.eventdispatchdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.androidxx.yangjw.eventdispatchdemo.listview.ListViewActivity;
import com.androidxx.yangjw.eventdispatchdemo.viewpager.ViewPagerDrawerEventActivity;

/**
 * 事件分发
 * 1、button。自定义Button
 * 2、Linearlayout: 自定义Linearlayout
 * 3、将上面的2个控件放在布局文件中
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
    }
    /**
     * 事件分发
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "MainActivity----->dispatchTouchEvent:  ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "MainActivity----->dispatchTouchEvent:  ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
//        return false;
    }

    /**
     * 事件消费
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MainActivity----->onTouchEvent: ");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "MainActivity----->onTouchEvent:   ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "MainActivity----->onTouchEvent:   ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }
}
