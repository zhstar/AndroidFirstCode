package com.codekun.common.core;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;

import com.codekun.common.utils.ActivityCollector;
import com.codekun.common.utils.CKLog;

/**
 * ����ActionBar��Activity,����Activity�Ļ���
 * Created by kun on 2015/11/13.
 */
public abstract class BaseActivity extends Activity {

    /**
     * 返回布局文件ID
     * @return
     */
    protected abstract int getLayoutResourceId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏系统自动的标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(getLayoutResourceId());

        CKLog.d("codekun.com", "当前类名：" + getClass().getSimpleName());

        DisplayMetrics dm = getResources().getDisplayMetrics();
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;
       // CKLog.d("codekun.com", "xdpi：" + xdpi + ", ydpi:" + ydpi);

        ActivityCollector.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.remove(this);
    }
}
