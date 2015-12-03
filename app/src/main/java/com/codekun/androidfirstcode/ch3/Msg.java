package com.codekun.androidfirstcode.ch3;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by kun on 2015/11/18.
 */
public class Msg {
    /**
     * 发送的信息
     */
    public static final int TYPE_SENT = 0;
    /**
     * 接收到的信息
     */
    public static final int TYPE_RECEIVED = 1;

    @IntDef({
            TYPE_SENT,
            TYPE_RECEIVED
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type{}



    /**
     * 信息内容
     */
    private String mText;
    /**
     * 内容类型
     * TYPE_SENT or TYPE_RECEIVED
     */
    private int mType;


    /**
     * 构造一个信息主体对象
     * @param text  信息内容
     * @param type  信息类型
     */
    public Msg(String text, @Type int type){
        mText = text;
        mType = type;
    }

    /**
     * 返回信息内容
     * @return
     */
    public String getText() {
        return mText;
    }

    /**
     * 返回信息类型
     * @return
     */
    public int getType() {
        return mType;
    }


}
