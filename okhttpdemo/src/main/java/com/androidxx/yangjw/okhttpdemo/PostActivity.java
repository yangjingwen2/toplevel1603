package com.androidxx.yangjw.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp的Post请求方式
 * 1、同步 execute
 * 2、异步 enqueue
 * 使用步骤：
 * 1、创建OkHttpClient对象
 * 2、创建一个Request对象
 * 3、执行Request，并且获得Responce对象
 *
 */
public class PostActivity extends AppCompatActivity {

    public static final String URL = "http://www.1688wan.com/majax.action?method=searchGift";
    private Button mAsyncBtn;
    private TextView mShowResultTxt;
    private OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initView();

    }

    private void initView() {
        mAsyncBtn = (Button) findViewById(R.id.okhttp_post_async_btn);
        mShowResultTxt = (TextView) findViewById(R.id.okhttp_post_show_result_tv);
        mAsyncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncRequest();
            }
        });
    }

    /**
     * Post的异步请求
     */
    private void asyncRequest() {
        //封装Post请求的参数
        FormBody formBody = new FormBody.Builder()
                .add("key","热血")//添加Post请求的参数
                .build();
        //创建一个Request对象
        Request request = new Request.Builder()
                .url(URL) //网络请求地址
                .post(formBody) //Post请求，并且将Post请求需要的参数封装到FormBody中
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mShowResultTxt.setText(result);
                    }
                });
            }
        });
    }
}
