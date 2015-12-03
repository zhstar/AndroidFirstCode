package com.codekun.androidfirstcode.ch9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.codekun.androidfirstcode.R;

public class Ch9LongRunningActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch9_long_running);

        findViewById(R.id.ch9_service2_start_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(Ch9LongRunningActivity.this, LongRunningService.class);
        startService(i);
    }
}
