package com.codekun.androidfirstcode.ch5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

public class Ch5LocalBroadcastActivity extends TitleBarActivity implements View.OnClickListener {

    private LocalBroadcastManager mLocalBroadcastManager;

    private LocalBroadcastReceiver mLocalBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //定义一个过滤器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.codekun.androidfirstcode.ch5.LOCAL_BROADCAST");

        //定义一个本地广播接收器
        mLocalBroadcastReceiver = new LocalBroadcastReceiver();

        //定义一个本地广播管理器
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);

        //个人观点：本地广播应该在第一个活动就实现注册
        //注册本地广播(此广播接收不到其他APP的发送)
        mLocalBroadcastManager.registerReceiver(mLocalBroadcastReceiver, intentFilter);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        findViewById(R.id.ch5_send_local_broadcast_button).setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mLocalBroadcastReceiver);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch5_local_broadcast;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("com.codekun.androidfirstcode.ch5.LOCAL_BROADCAST");
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    class LocalBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "LocalBroadcastReceiver is received.", Toast.LENGTH_SHORT).show();
        }
    }

}
