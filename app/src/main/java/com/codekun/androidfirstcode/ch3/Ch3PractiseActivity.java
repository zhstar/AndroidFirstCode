package com.codekun.androidfirstcode.ch3;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

import java.util.ArrayList;
import java.util.List;

public class Ch3PractiseActivity extends TitleBarActivity {
    private Context mContext;
    private ListView mMsgListView;
    private MsgAdapter adapter;
    private List<Msg> msgs = new ArrayList<Msg>();
    private Button send;
    private EditText msgEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mMsgListView = (ListView)findViewById(R.id.ch3_msg_listView);

        Msg m1 = new Msg("请给我一杯茶", Msg.TYPE_RECEIVED);
        Msg m2 = new Msg("好的", Msg.TYPE_SENT);
        Msg m3 = new Msg("换成咖啡吧", Msg.TYPE_RECEIVED);
        Msg m4 = new Msg("你妹,自己动手。", Msg.TYPE_SENT);

        msgs.add(m1);
        msgs.add(m2);
        msgs.add(m3);
        msgs.add(m4);

        adapter = new MsgAdapter(this, R.layout.layout_ch3_msg_item, msgs);
        mMsgListView.setAdapter(adapter);


        send = (Button)findViewById(R.id.ch3_send_button);
        msgEditText = (EditText)findViewById(R.id.ch3_send_editText);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = msgEditText.getText().toString();
                if (msg.equals("")) {
                    return;
                }
                msgEditText.setText("");
                Msg m = new Msg(msg, Msg.TYPE_SENT);
                msgs.add(m);
                MsgAdapter a = (MsgAdapter) mMsgListView.getAdapter();
                a.notifyDataSetChanged();//刷新消息列表
                mMsgListView.setSelection(msgs.size());//将listview定位到最后一行
            }
        });

        msgEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()){
                        mMsgListView.setSelection(msgs.size());
                    }
                }
            }
        });

    }



    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch3_practise;
    }
}
