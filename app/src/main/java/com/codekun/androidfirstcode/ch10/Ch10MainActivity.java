package com.codekun.androidfirstcode.ch10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codekun.androidfirstcode.ListViewActivity;
import com.codekun.androidfirstcode.R;

public class Ch10MainActivity extends ListViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getListViewId() {
        return R.id.ch10_listView;
    }

    @Override
    protected String[] getItems() {
        return new String[]{
                "WebView的用法",
                "使用HTTP协议访问网络",
                "解析XML格式数据",
                "解析JSON格式数据",
                "网络编程的最佳实践"
        };
    }

    @Override
    protected Class[] getActivities() {
        return new Class[]{
                Ch10WebViewActivity.class,
                Ch10HttpActivity.class,
                Ch10XMLActivity.class,
                Ch10JSONActivity.class
        };
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch10_main;
    }
}
