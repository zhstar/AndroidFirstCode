package com.codekun.androidfirstcode.ch3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;


public class Ch3ProgressBarActivity extends TitleBarActivity {

    private int mProgressMax = 100;
    private ProgressBar mProgressBar;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what > 100 || msg.what < 0){
                mProgressBar.setProgress(0);
            }else{
                mProgressBar.setProgress(msg.what);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressBar = (ProgressBar)findViewById(R.id.ch3_progressBar);
        mProgressBar.setMax(mProgressMax);

        Button start_btn = (Button)findViewById(R.id.ch3_startProgressBar_button);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new ProgressThread()).start();
            }
        });

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch3_progress_bar;
    }


    class ProgressThread implements Runnable {
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(100);
                    int p = mProgressBar.getProgress();
                    p+=5;
                    Message msg = new Message();
                    msg.what = p;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
