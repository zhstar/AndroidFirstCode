package com.codekun.androidfirstcode.core;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.codekun.androidfirstcode.widgets.TitleBar;

/**
 * 包含量自定义 标题栏(TitleBar) 的Activity
 * Created by kun on 2015/11/13.
 */
public class TitleBarActivity extends BaseActivity{

    private LinearLayout rootLayout;
    // 自定义的标题栏
    private TitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //子活动可能需要在布局文件中加入 TitleBar 组件
        /*mTitleBar = (TitleBar)findViewById(getTitleBarId());
        if(mTitleBar != null){
            mTitleBar.setLeftBtnOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onClickLeftBtn(v);
                }
            });
            mTitleBar.setRightBtnOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickRightBtn(v);
                }
            });
        }*/

        rootLayout = new LinearLayout(this);
        rootLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(rootLayout);
        mTitleBar = new TitleBar(this);
        mTitleBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        rootLayout.addView(mTitleBar);

        if (mTitleBar != null) {
            mTitleBar.setLeftBtnOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onClickLeftBtn(v);
                }
            });
            mTitleBar.setRightBtnOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickRightBtn(v);
                }
            });
        }

        LayoutInflater.from(this).inflate(getLayoutResourceId(), rootLayout);


        Intent intent = getIntent();
        if(intent != null){
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                String title = bundle.getString("title");
                setTitle(title);
            }
        }

    }


    /**
     * 标题栏左边按钮点击回调函数
     * @param v
     */
    protected void onClickLeftBtn(View v){
        finish();
    }
    /**
     * 标题栏右边按钮点击回调函数
     * @param v
     */
    protected void onClickRightBtn(View v){

    }

    /**
     * 设置标题栏文本
     * @param title
     */
    @Override
    public void setTitle(CharSequence title) {
        if(mTitleBar != null){
            mTitleBar.setTitle(title.toString());
        }
    }

    /**
     * 设置标题栏文本
     * @param titleId
     */
    @Override
    public void setTitle(int titleId) {
        if(mTitleBar != null){
            mTitleBar.setTitle(titleId);
        }
    }

    /**
     * 布局文件ID
     * @return
     */
    @Override
    protected int getLayoutResourceId() {
        return 0;
    }

    /**
     * 自定义标题栏的ID
     * @deprecated
     * @return
     */
    protected int getTitleBarId(){
        return 0;
    }

    /**
     *
     * @return
     */
    public TitleBar getTitleBar() {
        return mTitleBar;
    }


}
