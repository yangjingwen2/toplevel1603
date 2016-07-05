package com.androidxx.yangjw.imdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{

    public ListView mListView;
    public EditText mMsgEdt;
    public Button mSendBtn;

    private List<String> msgList = new ArrayList<>();
    public ChatAdapter chatAdapter;
    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        username = getIntent().getStringExtra("username");
        initView();
        receiveMsg();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.chat_list_lv);
        mMsgEdt = (EditText) findViewById(R.id.chat_msg_edt);
        mSendBtn = (Button) findViewById(R.id.chat_send_btn);
        mSendBtn.setOnClickListener(this);
        setupListView();
    }

    private void setupListView() {
        //适配器+List
        chatAdapter = new ChatAdapter();
        mListView.setAdapter(chatAdapter);
    }

    private void sendMsg() {
        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
        String msg = mMsgEdt.getText().toString();
        EMMessage message = EMMessage.createTxtSendMessage(msg, username);
        //发送消息
        EMClient.getInstance().chatManager().sendMessage(message);

        msgList.add(msg);
        chatAdapter.notifyDataSetChanged();
    }

    private void receiveMsg() {
        EMClient.getInstance().chatManager().addMessageListener(msgListener);

    }

    @Override
    public void onClick(View v) {
            sendMsg();
    }

    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            //收到消息
            String contents = messages.get(0).getBody().toString();
            msgList.add(contents);
            chatAdapter.notifyDataSetChanged();
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
        }

        @Override
        public void onMessageReadAckReceived(List<EMMessage> messages) {
            //收到已读回执
        }

        @Override
        public void onMessageDeliveryAckReceived(List<EMMessage> message) {
            //收到已送达回执
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
        }
    };

    class ChatAdapter extends BaseAdapter {

        @Override
        public int getItemViewType(int position) {
            if (position % 2 == 1) {
                return 1;
            }
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getCount() {
            return msgList == null ? 0 : msgList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder viewHolder = null;
            if (view == null) {
                if (getItemViewType(position) == 1) {
                    view = LayoutInflater.from(ChatActivity.this).inflate(R.layout.left_item_view,null);
                    viewHolder = new ViewHolder(view);
                } else {
                    view = LayoutInflater.from(ChatActivity.this).inflate(R.layout.right_item_view,null);
                    viewHolder = new ViewHolder(view);
                }
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.msgTxt.setText(msgList.get(position));
            return view;
        }

        class ViewHolder {
            public TextView msgTxt;
            public ViewHolder(View view) {
                view.setTag(this);
                msgTxt = (TextView) view.findViewById(R.id.item_msg);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //记得在不需要的时候移除listener，如在activity的onDestroy()时
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }
}
