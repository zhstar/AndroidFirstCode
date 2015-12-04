package com.codekun.androidfirstcode.ch12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codekun.androidfirstcode.ListViewActivity;
import com.codekun.androidfirstcode.R;

public class Ch12MainActivity extends ListViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getListViewId() {
        return R.id.ch12_listView;
    }

    @Override
    protected String[] getItems() {
        return new String[]{
                "光照传感器",
                "加速度传感器",
                "方向传感器"
        };
    }

    @Override
    protected Class[] getActivities() {
        return new Class[]{
                Ch12LightSensorActivity.class,
                Ch12AccelerometerSensorActivity.class,
                Ch12CompassActivity.class
        };
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch12_main;
    }
}
