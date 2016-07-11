package com.androidxx.yangjw.baidudemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;

/**
 * 使用百度地图功能
 * 说明：
 * 当百度地图不用的使用即使回收和暂停，分别调用onResume、onPause、onDestroy完成回收和暂停
 */
public class MapActivity extends AppCompatActivity {

    public TextureMapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMapView = (TextureMapView) findViewById(R.id.map_view);
        Intent intent = getIntent();
        double lat = intent.getDoubleExtra("lat",30.589724);
        double lon = intent.getDoubleExtra("", 114.317005);
        //设置地图新的中心点
        //缩放级别，从3--21，数字越大缩放的比例越大
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(new LatLng(lat, lon),16);
        BaiduMap mBaiduMap = mMapView.getMap();//BaiduMap是设置地图的一些属性的

        mBaiduMap.setMapStatus(mapStatusUpdate);
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);

        //创建InfoWindow展示的view
        Button button = new Button(getApplicationContext());
        button.setText("香格里拉酒店");
        button.setBackgroundResource(R.drawable.popup);
//定义用于显示该InfoWindow的坐标点
        LatLng pt = new LatLng(lat, lon);
//创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(button, pt, -47);
//显示InfoWindow
        mBaiduMap.showInfoWindow(mInfoWindow);

        //定义Maker坐标点
//        LatLng point = new LatLng(39.963175, 116.400244);
//构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(pt)
                .icon(bitmap);
//在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
}
