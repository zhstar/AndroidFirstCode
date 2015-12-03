package com.codekun.androidfirstcode.ch8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codekun.androidfirstcode.ListViewActivity;
import com.codekun.androidfirstcode.R;

public class Ch8MainActivity extends ListViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch8_main;
    }

    @Override
    protected int getListViewId() {
        return R.id.ch8_listView;
    }

    @Override
    protected String[] getItems() {
        return new String[]{
                "使用通知",
                "接收和发送短信",
                "调用摄像头和相册",
                "播放多媒体文件"
        };
    }

    @Override
    protected Class[] getActivities() {
        return new Class[]{
                Ch8NotificationActivity.class,
                Ch8SMSActivity.class,
                Ch8CameraAndPhotoActivity.class,
                Ch8MediaActivity.class
        };
    }
}
