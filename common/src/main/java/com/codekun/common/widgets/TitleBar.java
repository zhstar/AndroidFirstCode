package com.codekun.common.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codekun.common.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * �自定义 标题栏(TitleBar)
 * Created by kun on 2015/11/13.
 */
public class TitleBar extends LinearLayout {

    @IntDef({
            View.GONE,
            View.VISIBLE,
            View.INVISIBLE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility{}

    protected Context mContext;
    protected int mLayoutId;
    protected ImageButton mLeftBtn;
    protected ImageButton mRightBtn;
    protected TextView mTitleView;

    /**
     *
     * @param context
     */
    public TitleBar(Context context) {
        super(context);
        mContext = context;
        mLayoutId = R.layout.titlebar;
        initWidgets();
    }

    /**
     *
     * @param context
     * @param layoutId  布局文件
     */
    public TitleBar(Context context, int layoutId){
        super(context);
        mContext = context;
        mLayoutId = layoutId;
        initWidgets();
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mLayoutId = R.layout.titlebar;

        initWidgets();

        // values/attrs.xml �� TitleBar
        /*
        <?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="TitleBar">
        <attr name="leftBtn_src" format="reference" />
        <attr name="leftBtn_visibility">
            <flag name="gone" value="0" />
            <flag name="visible" value="1" />
            <flag name="invisible" value="2" />
        </attr>
        <attr name="rightBtn_src" format="reference" />
        <attr name="rightBtn_visibility">
            <flag name="gone" value="0" />
            <flag name="visible" value="1" />
            <flag name="invisible" value="2" />
        </attr>
        <attr name="title_text" format="reference|string" />
    </declare-styleable>
</resources>
        */
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);

        //�设置标题文本�
        setTitle(a.getString(R.styleable.TitleBar_title_text));
        //�设置左边按钮
        setLeftBtn(a.getResourceId(R.styleable.TitleBar_leftBtn_src, 0));
        //�设置右边按钮
        setRightBtn(a.getResourceId(R.styleable.TitleBar_rightBtn_src, 0));

        int leftBtnVisibleValue = a.getInt(R.styleable.TitleBar_leftBtn_visibility, 1);
        switch (leftBtnVisibleValue){
            case 0:
                setLeftBtnVisibility(View.GONE);
                break;
            case 1:
                setLeftBtnVisibility(View.VISIBLE);
                break;
            case 2:
                setLeftBtnVisibility(View.INVISIBLE);
                break;
        }

        int rightBtnVisibleValue = a.getInt(R.styleable.TitleBar_rightBtn_visibility, 0);
        switch (rightBtnVisibleValue){
            case 0:
                setRightBtnVisibility(View.GONE);
                break;
            case 1:
                setRightBtnVisibility(View.VISIBLE);
                break;
            case 2:
                setRightBtnVisibility(View.INVISIBLE);
                break;
        }

        a.recycle();

    }

    protected void initWidgets(){
        LayoutInflater.from(mContext).inflate(mLayoutId, this);
        //�等效以上代码
        //LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflater.inflate(R.layout.titlebar, this);


        //�左边按钮
        mLeftBtn = (ImageButton)findViewById(R.id.titlebar_leftBtn);
        //�右边接钮
        mRightBtn = (ImageButton)findViewById(R.id.titlebar_rightBtn);
        //�文本框
        mTitleView = (TextView)findViewById(R.id.titlebar_title);

        if (mLeftBtn == null || mRightBtn == null || mTitleView == null){

        }


    }

    /**
     * �设置标题文本
     * @param text
     */
    public void setTitle(String text)  {
        mTitleView.setText(text);
    }

    /**
     * ��设置标题文本����
     * @param resId
     */
    public void setTitle(int resId)  {
        mTitleView.setText(resId);
    }

    /**
     * �设置左边按钮图片
     * @param resId
     */
    public void setLeftBtn(int resId){
        mLeftBtn.setImageResource(resId);
    }

    /**
     * 设置右边按钮图片
     * @param resId
     */
    public void setRightBtn(int resId){
        mRightBtn.setImageResource(resId);
    }

    /**
     * 设置左边按钮可见性
     * @param visibility
     * @see {#link View#setVisibility()}
     */
    public void setLeftBtnVisibility(@Visibility int visibility){
        mLeftBtn.setVisibility(visibility);
    }

    /**
     * �设置右边按钮可见性
     * @param visibility
     */
    public void setRightBtnVisibility(@Visibility int visibility){
        mRightBtn.setVisibility(visibility);
    }

    /**
     * 设置左边按钮点击监听��
     * @param listener
     */
    public void setLeftBtnOnClickListener(OnClickListener listener){
        mLeftBtn.setOnClickListener(listener);
    }

    /**
     * 设置右边按钮点击监听��
     * @param listener
     */
    public void setRightBtnOnClickListener(OnClickListener listener){
        mRightBtn.setOnClickListener(listener);
    }

}
