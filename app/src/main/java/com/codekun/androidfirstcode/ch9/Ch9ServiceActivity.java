package com.codekun.androidfirstcode.ch9;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.codekun.androidfirstcode.R;


public class Ch9ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private MyService.DownloadBinder mDownloadBinder;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (MyService.DownloadBinder)service;
            mDownloadBinder.startDownload();
            mDownloadBinder.getProgress();
            Log.d("AndroidFirstCode", "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("AndroidFirstCode", "onServiceDisconnected");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch9_service);

        findViewById(R.id.ch9_service_start_button).setOnClickListener(this);
        findViewById(R.id.ch9_service_stop_button).setOnClickListener(this);
        findViewById(R.id.ch9_service_bind_button).setOnClickListener(this);
        findViewById(R.id.ch9_service_unbind_button).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(Ch9ServiceActivity.this, MyService.class);
        switch (v.getId()){
            case R.id.ch9_service_start_button:
                startService(i);
                break;
            case R.id.ch9_service_stop_button:
                stopService(i);
                break;
            case R.id.ch9_service_bind_button:
                bindService(i, mServiceConnection, Service.BIND_AUTO_CREATE);
                /*if(mDownloadBinder != null){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while(true){
                                Log.d("AndroidFirstCode", "Progress:" + mDownloadBinder.getProgress());
                            }
                        }
                    }).start();
                }*/
                break;
            case R.id.ch9_service_unbind_button:
                stopService(i);
                unbindService(mServiceConnection);
                break;
        }
    }
}
