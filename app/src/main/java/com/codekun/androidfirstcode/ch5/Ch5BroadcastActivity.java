package com.codekun.androidfirstcode.ch5;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.codekun.androidfirstcode.MainActivity;
import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

public class Ch5BroadcastActivity extends TitleBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button standard_btn = (Button)findViewById(R.id.ch5_send_standard_broadcast_button);
        standard_btn.setOnClickListener(this);
        Button order_btn = (Button)findViewById(R.id.ch5_send_order_broadcast_button);
        order_btn.setOnClickListener(this);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch5_broadcast;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.ch5_send_standard_broadcast_button){
            intent = new Intent("com.codekun.androidfirstcode.CH5_BROADCAST_RECEIVER");
            sendBroadcast(intent);
        }else if(v.getId() == R.id.ch5_send_order_broadcast_button){
            intent = new Intent("com.codekun.androidfirstcode.CH5_BROADCAST_RECEIVER");
            sendOrderedBroadcast(intent, null);
        }
    }
}
