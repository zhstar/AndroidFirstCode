package com.codekun.androidfirstcode.ch8;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codekun.androidfirstcode.R;

public class Ch8SMSActivity extends AppCompatActivity {

    private TextView fromTextView;
    private TextView contentTextView;

    private MessageReceiver smsReceiver;

    private EditText toEditText;
    private EditText contentEditText;
    private Button sendButton;

    private SendStatusReceiver mSendStatusReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch8_sms);
        fromTextView = (TextView)findViewById(R.id.ch8_sms_sender);
        contentTextView = (TextView)findViewById(R.id.ch8_sms_content);

        //注册广播
        IntentFilter sendSMSIntentFilter = new IntentFilter();
        sendSMSIntentFilter.addAction("com.codekun.androidfirstcode.ch8.SEND_SMS_RECEIVED");
        mSendStatusReceiver = new SendStatusReceiver();
        registerReceiver(mSendStatusReceiver, sendSMSIntentFilter);


        toEditText = (EditText)findViewById(R.id.ch8_sms_to_textView);
        contentEditText=(EditText)findViewById(R.id.ch8_sms_content_textView);
        sendButton = (Button)findViewById(R.id.ch8_sms_send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.codekun.androidfirstcode.ch8.SEND_SMS_RECEIVED");
                PendingIntent pi = PendingIntent.getBroadcast(Ch8SMSActivity.this, 0, i, 0);
                String address = toEditText.getText().toString();
                String content = contentEditText.getText().toString();
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(address, null, content, pi, null);
            }
        });

        /*
        <!-- Ch8:接收短信权限-->
        <uses-permission android:name="android.permission.RECEIVE_SMS" />
         */
        //注册广播接收器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        //1
        //拦截短信:设置优先级
        intentFilter.setPriority(100);
        smsReceiver = new MessageReceiver();

        registerReceiver(smsReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(smsReceiver);
        unregisterReceiver(mSendStatusReceiver);
    }

    /**
     * 短信接收器
     */
    class MessageReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < messages.length; ++i){
                messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
            }
            //获取发送方号码
            String addressNumber = messages[0].getOriginatingAddress();
            //获取信息内容
            String content = "";
            for (SmsMessage m : messages){
                content += m.getMessageBody();
            }

            fromTextView.setText(addressNumber);
            contentTextView.setText(content);

            //2
            //拦截短信：禁止广播
            abortBroadcast();

        }
    }

    /**
     * 短信发送状态接收器
     */
    class SendStatusReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if(getResultCode() == RESULT_OK){
                Toast.makeText(context, "短信发送成功", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "短信发送失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
