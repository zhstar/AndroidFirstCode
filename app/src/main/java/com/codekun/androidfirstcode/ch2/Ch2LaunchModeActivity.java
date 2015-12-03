package com.codekun.androidfirstcode.ch2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;

public class Ch2LaunchModeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch2_launch_mode);

        TextView tv = (TextView)findViewById(R.id.ch2_launch_mode_textView);

        /*
        <activity
            android:name=".FirstActivity"
            android:launchMode="singleTask"
            android:label="This is FirstActivity" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
         */
        tv.setText(
                "standard:standard是活动默认的启动模式，在不进行显示指定的情况下，" +
                        "所有活动都会自动使用这种活动模式，每当启动一个新的活动，它就会在返回栈中入栈，" +
                        "系统不会在乎这个活动是否已经在返回栈中存在，每次启动都会创建该活动的一个新的实例。\n\n" +
                        "singeTop:当活动的启动模式指定为singeTop，在启动活动时如果发现返回栈的栈顶已经是该活动，" +
                        "则认为可以直接使用它，不会再创建新的活动实例。\n\n" +
                        "singleTask:当活动的启动模式指定为singleTask，每次启动活动时系统首先会在返回栈中检查" +
                        "是否存在该活动的实例，如果发现已经存在则直接使用该实例，关把在这个活动之上的所有活动" +
                        "统统出栈，如果没有发现就会创建一个新的活动实例。\n\n" +
                        "singleInstance:在这种模式下会有一个单独的返回栈来管理这个活动，不管是哪个应用程序来访问"+
                        "这个活动，都共用的同一个返回栈，也就解决了共享活动的实例问题。");

        tv.append("\n\n请参考《第一行代码 2.5节》");

        TextView taskIdTV = (TextView)findViewById(R.id.ch2_task_id_textView);
        taskIdTV.append(String.valueOf(getTaskId()));

    }

}
