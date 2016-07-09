package com.androidxx.yangjw.shoppingcartdemo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.androidxx.yangjw.shoppingcart.dao.DaoMaster;
import com.androidxx.yangjw.shoppingcart.dao.DaoSession;
import com.androidxx.yangjw.shoppingcart.dao.ShoppingCartDao;

/**
 * Created by yangjw on 2016/7/9.
 */
public class BaseActivity extends AppCompatActivity{
    protected ShoppingCartDao mShoppingCartDao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupGreenDao();
    }

    private void setupGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "androidxx", null);
        SQLiteDatabase readableDatabase = devOpenHelper.getReadableDatabase();
        DaoMaster daoMaster = new DaoMaster(readableDatabase);
        DaoSession daoSession = daoMaster.newSession();
        mShoppingCartDao = daoSession.getShoppingCartDao();
    }
}
