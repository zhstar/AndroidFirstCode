package com.codekun.androidfirstcode.ch9;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;

public class Ch9ThreadActivity extends AppCompatActivity {

    private Button updateButton;
    private TextView textView;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                textView.setText("更新文本内容");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch9_thread);

        updateButton = (Button)findViewById(R.id.ch9_thread_updateText_button);
        textView = (TextView)findViewById(R.id.ch9_thread_textView);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    }
                }).start();
            }
        });

    }
}
