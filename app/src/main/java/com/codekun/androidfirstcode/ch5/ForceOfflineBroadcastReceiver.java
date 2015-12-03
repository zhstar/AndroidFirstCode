package com.codekun.androidfirstcode.ch5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 强行离线播放接收器
 * 注意：此收接需要注册为本地广播, 并且在第一个活动里实现注册及反注册
 * Created by kun on 2015/11/20.
 */
public class ForceOfflineBroadcastReceiver extends BroadcastReceiver {

    /**
     * 当前接收器的 action
     */
    public static final String ACTION_FORCE_OFFLINE = "com.codekun.android.ACTION_FORCE_OFFLINE";

    @Override
    public void onReceive(final Context context, Intent intent) {
        Toast.makeText(context, "ForceOfflineBroadcastReceiver is received.", Toast.LENGTH_SHORT).show();
        //启动一个服务，让服务去显示提示框
        Intent i = new Intent(context, ForceOfflineService.class);
        context.startService(i);

        /*
        http://www.itmmd.com/201501/442.html

        AlertDialog 是UI操作，需要放到主线程里面弹出对话框，BroadcastReceiver 运行的线程是非主线程，
        不能进行展示，如果你想实现这种功能，可以让 BroadcastReceiver 给 主线程发信息，主线程接收后在弹出 AlertDialog 即可.

       BroadcastReceiver 接收后直接被系统kill掉，你在  BroadcastReceiver 弹出对话框后，
       onReceive 及接收，它就会被kill，就出现崩溃的问题了

         */
        /*//弹出提示框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("下线通知");
        builder.setMessage("你的账号已经在其他地方登录。");
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
        */

    }
}
