package com.codekun.androidfirstcode.ch9;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.codekun.androidfirstcode.R;

public class MyService extends Service {
    public MyService() {
    }

    private int count = 0;
    private DownloadBinder mDownloadBinder = new DownloadBinder();

    /**
     *
     */
    class DownloadBinder extends Binder {
        private int counter = 0;
        public void startDownload(){

        }
        public int getProgress(){
            return ++counter;
        }
    }

    /**
     * 利用此方法使服务与活动通讯
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mDownloadBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("AndroidFirstCode", "Service onCreate.");

        //创建前台服务
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("这是一个前台服务");
        builder.setContentText("这是一个前台服务2");
        builder.setSmallIcon(R.drawable.simle);
        startForeground(1, builder.build());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("AndroidFirstCode", "Service onStart.");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(500);
                        ++count;
                        if(count > 20){
                            stopSelf();
                            break;
                        }
                        Log.d("AndroidFirstCode", "Count:" + count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("AndroidFirstCode", "Service onDestroy.");
    }
}
