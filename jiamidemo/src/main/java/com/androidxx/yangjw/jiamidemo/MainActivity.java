package com.androidxx.yangjw.jiamidemo;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    private String content = "abcdefg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.base64:
                base64Method();
                break;
            case R.id.md5:
//                md5Method();
                md5File();
                break;
        }
    }

    /**
     * MD5加密
     */
    private void md5Method() {
        try {
            //获取一个MD5的加密工具类
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //将明文放到update方法中进行加密
            messageDigest.update(content.getBytes());
            //获取加密后的密文
            byte[] digest = messageDigest.digest();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                builder.append(Integer.toHexString(Math.abs(digest[i])));
            }
            Log.d(TAG, "md5Method: " + builder.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Base64加密
     * 一般用于需要加密，但是又不太重要的信息
     */
    private void base64Method() {
        //加密
        byte[] decode = Base64.encode(content.getBytes(), Base64.DEFAULT);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < decode.length; i++) {
            stringBuilder.append(decode[i]);
        }
        Log.d(TAG, "base64Method: " + stringBuilder.toString());

        //解密
        byte[] encode = Base64.decode(decode, Base64.DEFAULT);
        Log.d(TAG, "base64Method: " + new String(encode));
    }

    private void md5File() {
        try {
            InputStream inputStream = getAssets().open("file.txt");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer,0,len);
            }
            byte[] bytes = baos.toByteArray();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            //将密文转换成16进制
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                builder.append(Integer.toHexString(Math.abs(digest[i])));
            }
            Log.d(TAG, "使用MD5加密文件：md5file: " + builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
