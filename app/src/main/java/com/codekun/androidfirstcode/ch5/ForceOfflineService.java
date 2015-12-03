package com.codekun.androidfirstcode.ch5;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.IBinder;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.codekun.androidfirstcode.MainActivity;
import com.codekun.androidfirstcode.utils.ActivityCollector;

/**
 * Created by kun on 2015/11/20.
 */
public class ForceOfflineService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final Context context = getApplicationContext();

        Toast.makeText(context, "ForceOfflineService is received.", Toast.LENGTH_SHORT).show();

        //真机测试未成功
        //弹出提示框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("下线通知");
        builder.setMessage("你的账号已经在其他地方登录。");

        /*TextView textView = new TextView(context);
        textView.setText(Html.fromHtml(
                "<b>第二种实现链接的方式：</b>" +
                        "<a href='http://www.baidu.com'>连接到度娘</a>"
        ));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        builder.setView(textView);*/

        builder.setCancelable(false);//不可以使用返回键退出当前活动
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //结束所有活动
                ActivityCollector.finishAll();
                //重新启动第一个活动
                Intent intent1 = new Intent(context, MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
            }
        });

        AlertDialog dialog = builder.create();

        //Android:These windows are always on top of application windows.
        //需要设置AlertDialog的类型，保证在广播接收器中可以正常弹出
        //注意需要权限: android.permission.SYSTEM_ALERT_WINDOW
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
