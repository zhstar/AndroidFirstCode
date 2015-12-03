package com.codekun.androidfirstcode.ch9;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;

public class LongRunningService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("AndroidFirstCode", "LongRunningService onStart.");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("AndroidFirstCode", "Execute:" + new Date());
            }
        }).start();

        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        long ms = SystemClock.elapsedRealtime() + 60 * 1000; //一分钟执行一次
        manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, ms, pi);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
