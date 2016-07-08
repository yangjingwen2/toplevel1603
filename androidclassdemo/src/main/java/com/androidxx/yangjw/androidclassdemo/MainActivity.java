package com.androidxx.yangjw.androidclassdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androidxx.yangjw.R;

public class MainActivity extends BaseAcivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "sssssss", Toast.LENGTH_SHORT).show();
            }
        }).start();
    }
}
