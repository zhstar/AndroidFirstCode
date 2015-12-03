package com.codekun.androidfirstcode.ch8;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

import java.io.File;

public class Ch8NotificationActivity extends TitleBarActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;
    private int mCheckedRadioButtonId;
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch8_notification;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.ch8_create_notify_button).setOnClickListener(this);
        mRadioGroup= (RadioGroup)findViewById(R.id.ch8_radiogroup);
        mRadioGroup.setOnCheckedChangeListener(this);
        mCheckedRadioButtonId = mRadioGroup.getCheckedRadioButtonId();
    }

    int counter = 0;
    @Override
    public void onClick(View v) {
        //获取通知管理器对象
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //通知被点击之后启动的activity
        Intent intent  = new Intent(this, Ch8MainActivity.class);
        //
        /*启动activity的 PendingIntent
        PendingIntent.FLAG_CANCEL_CURRENT:如果当前通知已经存在，则取消然后重新生成一个新通知
        PendingIntent.FLAG_NO_CREATE:
        PendingIntent.FLAG_ONE_SHOT:
        PendingIntent.FLAG_UPDATE_CURRENT:
        通过程序演示，看不出有什么区别，有待验证
        */
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_NO_CREATE);
        switch (mCheckedRadioButtonId){
            case R.id.ch8_flag_cancel_current:
                pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_NO_CREATE);
                break;
            case R.id.ch8_flag_no_create:
                pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_NO_CREATE);
                break;
            case R.id.ch8_flag_one_shot:
                pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_ONE_SHOT);
                break;
            case R.id.ch8_flag_update_current:
                pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                break;
        }

        //声音
        Uri soundUri = Uri.fromFile(new File("/system/media/audio/ringtones/Basic_tone.ogg"));
        //振动
        long[] vibrates = {0, 1000, 1000, 1000};//{延迟播放时间，播放时长，间隔时长，播放时长}


        //创建对象
        Notification notification = null;
        //创建通知对象构建器（使用兼容包）
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("通知标题");
        builder.setContentText("通知内容" + (++counter));
        builder.setTicker("Ticker");//通知第一次到达时显示在状态栏的文本
        builder.setSmallIcon(R.drawable.simle);//图标
        builder.setAutoCancel(true);//触摸之后自动取消
        builder.setContentIntent(pendingIntent);
        builder.setSound(soundUri);//播放一段声音
        builder.setVibrate(vibrates);//设置振动,需要权限
        builder.setLights(Color.BLUE, 1000, 1000);
        notification = builder.build();
        //发送通知
        manager.notify(1, notification);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mCheckedRadioButtonId = group.getCheckedRadioButtonId();
    }
}
