package com.codekun.androidfirstcode;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.codekun.androidfirstcode.ch10.Ch10MainActivity;
import com.codekun.androidfirstcode.ch11.Ch11MainActivity;
import com.codekun.androidfirstcode.ch12.Ch12MainActivity;
import com.codekun.androidfirstcode.ch2.Ch2MainActivity;
import com.codekun.androidfirstcode.ch3.Ch3MainActivity;
import com.codekun.androidfirstcode.ch4.Ch4MainActivity;
import com.codekun.androidfirstcode.ch5.Ch5MainActivity;
import com.codekun.androidfirstcode.ch5.ForceOfflineBroadcastReceiver;
import com.codekun.androidfirstcode.ch6.Ch6MainActivity;
import com.codekun.androidfirstcode.ch7.Ch7MainActivity;
import com.codekun.androidfirstcode.ch8.Ch8MainActivity;
import com.codekun.androidfirstcode.ch9.Ch9MainActivity;
import com.codekun.androidfirstcode.core.TitleBarActivity;
import com.codekun.androidfirstcode.adapters.CustomListAdapter;


public class MainActivity extends TitleBarActivity {

    //目录
    private String[] mItems = new String[]{
            "第2章 先从看得到的入手，探究活动",
            "第3章 软件也要拼脸蛋，UI开发的点点滴滴",
            "第4章 手机平板要兼顾，探究碎片",
            "第5章 全局大喇叭，详解广播机制",
            "第6章 数据存储全方案，详解持久化技术",
            "第7章 跨程序共享数据，探究内容提供器",
            "第8章 丰富你的程序，运用手机多媒体",
            "第9章 后台默默的劳动者，探究服务",
            "第10章 看看精彩的世界，使用网络技术",
            "第11章 Android特色开发，基于位置的服务",
            "第12章 Android特色开发，使用传感器",
            "第13章 继续进阶，你还应该掌握的高级技巧",
            "第14章 进入实战，开发酷欧天气",
            "第15章 最后一步，将应用发布到"
    };

    //每个目录对象的Activity
    private Class[] mActivities = new Class[]{
            Ch2MainActivity.class,
            Ch3MainActivity.class,
            Ch4MainActivity.class,
            Ch5MainActivity.class,
            Ch6MainActivity.class,
            Ch7MainActivity.class,
            Ch8MainActivity.class,
            Ch9MainActivity.class,
            Ch10MainActivity.class,
            Ch11MainActivity.class,
            Ch12MainActivity.class
    };

    private ListView mListView;
    private Context mContext;

    private LocalBroadcastManager mLocalBroadcastManager;
    private ForceOfflineBroadcastReceiver mForceOfflinReceiver;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mContext = this;

        setTitle(R.string.app_name);

        if (getTitleBar() != null){
            getTitleBar().setLeftBtnVisibility(View.GONE);
        }


        mListView = (ListView)findViewById(R.id.main_menu_listView);
        mListView.setAdapter(new CustomListAdapter(this, R.layout.list_item, mItems));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= mActivities.length) {
                    Toast.makeText(mContext, mItems[position] + "\n未实功能!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(mContext, mActivities[position]);
                Bundle bundle = new Bundle();
                bundle.putString("title", mItems[position]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //注册一个本地广播接收器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ForceOfflineBroadcastReceiver.ACTION_FORCE_OFFLINE);

        mForceOfflinReceiver = new ForceOfflineBroadcastReceiver();

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mLocalBroadcastManager.registerReceiver(mForceOfflinReceiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mForceOfflinReceiver);
    }


}
