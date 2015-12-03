package com.codekun.androidfirstcode.ch5;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.adapters.CustomListAdapter;
import com.codekun.androidfirstcode.core.TitleBarActivity;

public class Ch5MainActivity extends TitleBarActivity {

    private String[] mItems =new String[]{
            "动态注册监听网络变化",
            "静态注册实现开机启动",
            "发送自定义广播",
            "使用本地广播",
            "练习，强行离线广播"
    };
    private Class[] mActivities = new Class[]{
            Ch5NetworkChangeActivity.class,
            Ch5NetworkChangeActivity.class,
            Ch5BroadcastActivity.class,
            Ch5LocalBroadcastActivity.class,
            Ch5PractiseActivity.class
    };
    private ListView mListView;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        mListView = (ListView)findViewById(R.id.ch5_listView);
        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.list_item, mItems);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= mActivities.length) {
                    Toast.makeText(mContext, mItems[position] + "\n未完成", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(mContext, mActivities[position]);
                Bundle b = new Bundle();
                b.putString("title", mItems[position]);
                i.putExtras(b);
                startActivity(i);
            }
        });

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch5_main;
    }
}
