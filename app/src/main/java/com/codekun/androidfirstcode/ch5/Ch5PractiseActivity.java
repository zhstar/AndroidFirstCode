package com.codekun.androidfirstcode.ch5;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Bundle;
import android.view.View;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

public class Ch5PractiseActivity extends TitleBarActivity implements View.OnClickListener {


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch5_practise;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.ch5_force_offline_button).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ForceOfflineBroadcastReceiver.ACTION_FORCE_OFFLINE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


}
