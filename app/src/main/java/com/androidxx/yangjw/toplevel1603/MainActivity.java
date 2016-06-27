package com.androidxx.yangjw.toplevel1603;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mHomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //这是MainActivity，是程序的入口
        mHomeText = (TextView) findViewById(R.id.home_textview);
        mHomeText.setText("这是Git");

    }
}
