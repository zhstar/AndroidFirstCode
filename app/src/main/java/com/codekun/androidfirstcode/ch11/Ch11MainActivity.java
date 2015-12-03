package com.codekun.androidfirstcode.ch11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codekun.androidfirstcode.ListViewActivity;
import com.codekun.androidfirstcode.R;

public class Ch11MainActivity extends ListViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getListViewId() {
        return R.id.ch11_listView;
    }

    @Override
    protected String[] getItems() {
        return new String[]{
                "找到自己的位置",
                "反向地理编码，看得懂的位置信息",
                "使用百度地图"
        };
    }

    @Override
    protected Class[] getActivities() {
        return new Class[]{
                Ch11LocationActivity.class,
                Ch11ReverseLocationActivity.class
        };
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch11_main;
    }
}
