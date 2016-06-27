package com.androidxx.yangjw.reflectdemo;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by yangjw on 2016/6/27.
 */
public class Knife {

    public static void bind(Activity activity) {
        Class<? extends Activity> activityClass = activity.getClass();
        //获得Activity中所有的属性
        Field[] fields = activityClass.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            //获取用BindView标签的熟悉
            BindView annotation = fields[i].getAnnotation(BindView.class);
            if (annotation != null) {
                try {
                    View view = activity.findViewById(annotation.value());
                    fields[i].set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }


        }

    }
}
