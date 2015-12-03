package com.codekun.androidfirstcode.ch5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.codekun.androidfirstcode.MainActivity;

/**
 * Created by kun on 2015/11/20.
 */
public class StandardBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "StandardBroadcastReceiver is received.", Toast.LENGTH_SHORT).show();
        //启动其他活动
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
