package com.androidxx.yangjw.imdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mNameEdt;
    private Button mAddBtn;
    private ListView mFriendListView;
    private List<String> usernames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        initView();
    }

    private void initView() {
        mNameEdt = (EditText) findViewById(R.id.friends_name_edt);
        mAddBtn = (Button) findViewById(R.id.friends_add_btn);
        mFriendListView = (ListView) findViewById(R.id.friends_list_lv);
        mFriendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (usernames != null) {
                    String name = usernames.get(position);
                    Intent intent = new Intent(FriendsActivity.this,ChatActivity.class);
                    intent.putExtra("username",name);
                    startActivity(intent);
                }
            }
        });


        mAddBtn.setOnClickListener(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //获得好友列表
                    usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(FriendsActivity.this,android.R.layout.simple_list_item_1,usernames);
                            mFriendListView.setAdapter(arrayAdapter);
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //此方法为异步方法
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                Log.d("androidxx", "onSuccess: exit");

            }

            @Override
            public void onProgress(int progress, String status) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onError(int code, String message) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void onClick(View v) {
        final String name = mNameEdt.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //参数为要添加的好友的username和添加理由
                try {
                    EMClient.getInstance().contactManager().addContact(name, "没有原因");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(FriendsActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(FriendsActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }
}
