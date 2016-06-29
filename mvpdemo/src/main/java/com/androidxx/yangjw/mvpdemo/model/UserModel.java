package com.androidxx.yangjw.mvpdemo.model;

import android.util.Log;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.androidxx.yangjw.mvpdemo.config.URL;
import com.androidxx.yangjw.mvpdemo.handle.ICallBack;
import com.androidxx.yangjw.mvpdemo.tool.LogTool;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangjw on 2016/6/29.
 */
public class UserModel implements IUserMedol{

    @Override
    public void login(String username, String password,final ICallBack callBack) {
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        OkHttpTool.newInstance().post(map).start(URL.LOGIN_URL)
                .callback(new IOKCallBack() {
                    @Override
                    public void success(String result) {
                        LogTool.log(UserModel.class,result);
                        try {
                            JSONObject jsonObj = new JSONObject(result);
                            String msg = jsonObj.getString("msg");
                            callBack.loginComplete(msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
