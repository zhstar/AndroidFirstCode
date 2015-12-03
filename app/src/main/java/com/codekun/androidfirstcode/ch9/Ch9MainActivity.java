package com.codekun.androidfirstcode.ch9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codekun.androidfirstcode.ListViewActivity;
import com.codekun.androidfirstcode.R;

public class Ch9MainActivity extends ListViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getListViewId() {
        return R.id.ch9_listView;
    }

    @Override
    protected String[] getItems() {
        return new String[]{
                "Android多线程编程",
                "服务的基本用法",
                "服务的生命周期",
                "服务的最佳实践——后台执行的定时任务"
        };
    }

    @Override
    protected Class[] getActivities() {
        return new Class[]{
                Ch9ThreadActivity.class,
                Ch9ServiceActivity.class,
                Ch9ServiceActivity.class,
                Ch9LongRunningActivity.class
        };
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch9_main;
    }
}
