package com.androidxx.yangjw.okhttpdemo;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 完成下载
 * 1、创建OkHttpClient对象
 * 2、创建一个Request对象
 * 3、执行Request
 */
public class DownloadActivity extends AppCompatActivity {

    public static final String URL = "http://i3.72g.com/upload/201510/201510261436311061.png";
    private OkHttpClient okHttpClient = new OkHttpClient();
    private ImageView mShowImage;
    private Button mDownloadBtn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();
    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("客官进来坐啊~");
        mShowImage = (ImageView) findViewById(R.id.download_imageview);
        mDownloadBtn = (Button) findViewById(R.id.download_btn);
        mDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download();
            }
        });

    }

    private void download() {
        progressDialog.show();
        Request request = new Request.Builder()
                .url(URL)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while((len = inputStream.read(buffer)) != -1) {
                    baos.write(buffer,0,len);
                    baos.flush();
                }
                inputStream.close();
                byte[] bytes = baos.toByteArray();
                final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                //显示到ImageView中
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        mShowImage.setImageBitmap(bitmap);
                    }
                });
            }
        });

    }
}
