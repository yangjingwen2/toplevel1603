package com.androidxx.yangjw.qrcodedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import java.io.IOException;

/**
 * 拍照功能
 * 1、创建一个SurfaceView，用来显示照相机实时捕捉的画面
 * 2、获得Camera对象
 * 3、将Camera的画面显示到SurfaceView中，Holder
 */
public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    public SurfaceView mSurfaceView;
    public Camera camera;
    public ImageView mShowImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initView();
    }

    private void initView() {
        mSurfaceView = (SurfaceView) findViewById(R.id.camera_surface_view);
        mShowImageView = (ImageView) findViewById(R.id.camera_show_image);
        mSurfaceView.getHolder().addCallback(this);
    }

    public void click(View view) {
        camera.takePicture(null, null, new Camera.PictureCallback() {
            /**
             *
             * @param data 就是拍照的图像数据
             * @param camera
             */
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                mShowImageView.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //获得一个Camera对象
        camera = Camera.open(0);
        try {
            //将预览的画面通过SurfaceView显示出来
            camera.setPreviewDisplay(holder);
            camera.startPreview();//开始预览
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
    }
}
