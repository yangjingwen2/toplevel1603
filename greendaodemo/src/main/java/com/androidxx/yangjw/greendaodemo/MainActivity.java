package com.androidxx.yangjw.greendaodemo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidxx.yangjw.dao.DaoMaster;
import com.androidxx.yangjw.dao.DaoSession;
import com.androidxx.yangjw.dao.user;
import com.androidxx.yangjw.dao.userDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        //配置数据库的名称以及数据库的版本号
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this,"android1603",null);
        //获得数据库对象
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
        //创建一个DaoMaster用来获取UserDao对象，UserDao是用来操作数据库的
        DaoMaster daoMaster = new DaoMaster(readableDatabase);
        DaoSession daoSession = daoMaster.newSession();
        userDao userDao = daoSession.getUserDao();
        //操作数据库
        insert(userDao);
        select(userDao);
    }

    /**
     * 插入数据到数据库的表中
     * @param userDao
     */
    private void insert(userDao userDao) {
        user user = new user();
        user.setName("zhangsan");
        user.setAge(23);
        userDao.insert(user);
    }

    private void select(userDao userDao) {
        List<user> users = userDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            Log.d(TAG, "select: " + users.get(i).getName() + "--" + users.get(i).getAge());
        }
    }
}
