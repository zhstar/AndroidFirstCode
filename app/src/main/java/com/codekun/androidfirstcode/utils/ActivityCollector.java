package com.codekun.androidfirstcode.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动管理器
 * Created by kun on 2015/11/16.
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();
    public static void add(Activity activity){
        activities.add(activity);
    }
    public static void remove(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for (Activity a : activities){
            if (!a.isFinishing()){
                a.finish();
            }
        }
    }
}
