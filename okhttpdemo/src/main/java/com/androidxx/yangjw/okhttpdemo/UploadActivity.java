package com.androidxx.yangjw.okhttpdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 上传
 * 特殊的Post请求
 *
 * 本案例的流程：
 * 1、打开系统相册，选择一张图片
 * 2、将选择的图片上传到服务器
 */
public class UploadActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String UPLOAD_URL = "http://10.6.151.93:8080/androidxx/upload.do";
    private ImageView mImageView;
    private Button mImageBtn;
    private Button mSubmitBtn;
    private Uri imageUri;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        initView();
    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("客官进来坐啊~");
        mImageView = (ImageView) findViewById(R.id.upload_show_image);
        mImageBtn = (Button) findViewById(R.id.upload_image_btn);
        mSubmitBtn = (Button) findViewById(R.id.upload_submit_btn);
        mImageBtn.setOnClickListener(this);
        mSubmitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upload_image_btn:
                chooseImage();
                break;
            case R.id.upload_submit_btn:
                try {
                    upload();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void upload() throws IOException {
        progressDialog.show();
        //----------------------------------------------------------------------
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        //参数一：上传文件的key
        //参数二：上传到服务器之后的文件名称
        //参数三：要上传的文件
        MediaType mediaType = MediaType.parse("image/*");

        //获得图片的字节流
        InputStream inputStream = getContentResolver().openInputStream(imageUri);

        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            baos.write(buffer,0,len);
            baos.flush();
        }
        byte[] bytes = baos.toByteArray();
        builder.addFormDataPart("upload","a123.jpg",MultipartBody.create(mediaType,bytes));
        builder.addFormDataPart("upload","aaaaaa.jpg",MultipartBody.create(mediaType,bytes));
        MultipartBody multipartBody = builder.build();

        //----------------------------------------------------------------------------

        Request request = new Request.Builder()
                .url(UPLOAD_URL)
                .post(multipartBody) //
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("androidxx", "onResponse: ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                });
            }
        });
    }

    /**
     * 打开系统相册，选择图片
     */
    private void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return ;
        }
        //获得系统返回的图片
        imageUri = data.getData();
        //通过Uri获得系统的图片
        try {
            //图片的流
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            mImageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
