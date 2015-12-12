package com.codekun.common.utils;

import android.util.Log;

/**
 * 定制自己的调试工具
 * Created by kun on 2015/12/4.
 */
public class CKLog {

    public static final int NONE = 0;
    public static final int ERROR = 1;
    public static final int WARN = 2;
    public static final int INFO = 3;
    public static final int DEBUG = 4;
    public static final int VERBOSE = 5;

    /**
     * 手动修改
     */
    public static final int LEVEL = DEBUG;

    public static void v(String tag, String msg) {
        if (LEVEL >= VERBOSE) {
            Log.v(tag, msg);
        }
    }
    public static void d(String tag, String msg) {
        if (LEVEL >= DEBUG) {
            Log.d(tag, msg);
        }
    }
    public static void i(String tag, String msg) {
        if (LEVEL >= INFO) {
            Log.i(tag, msg);
        }
    }
    public static void w(String tag, String msg) {
        if (LEVEL >= WARN) {
            Log.w(tag, msg);
        }
    }
    public static void e(String tag, String msg) {
        if (LEVEL >= ERROR) {
            Log.e(tag, msg);
        }
    }
}
