package com.androidxx.yangjw.webviewjsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

/**
 * 使用JsBridge自带的WebView
 */
public class MainActivity extends AppCompatActivity {

    public BridgeWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (BridgeWebView) findViewById(R.id.web_view);

        /**
         * 参数一：是一个方法的名称，需要同Web页面上的名称一致
         */
        mWebView.registerHandler("handlerVideo", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.i("android1603", "data from web = " + data);
                function.onCallBack("submitFromWeb exe, response data from Java");
            }
        });
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl("http://app.vmoiver.com/49379?qingapp=app_new");

    }
}
