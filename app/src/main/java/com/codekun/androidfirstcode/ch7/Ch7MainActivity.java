package com.codekun.androidfirstcode.ch7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codekun.androidfirstcode.ListViewActivity;
import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

public class Ch7MainActivity extends ListViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getListViewId() {
        return R.id.ch7_listView;
    }

    @Override
    protected String[] getItems() {
        String[] items = new String[]{
                "访问其他程序中的数据(读取通讯录)",
                "创建自己的内容提供器"
        };
        return items;
    }

    @Override
    protected Class[] getActivities() {
        Class[] activities = new Class[]{
                Ch7ReadContactsActivity.class,
                Ch7CustomProviderActivity.class
        };
        return activities;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch7_main;
    }
}
