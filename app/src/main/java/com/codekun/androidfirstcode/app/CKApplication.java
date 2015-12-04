package com.codekun.androidfirstcode.app;

import android.app.Application;
import android.content.Context;

/**
 * 获取全局Context对象
 * 修改AndroidManifest.xml文件
 * <application
    android:name="com.codekun.androidfirstcode.app.CKApplication"
    ... />
    </application>
 * Created by kun on 2015/12/4.
 */
public class CKApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
    public static Context getContext(){
        return mContext;
    }
}
