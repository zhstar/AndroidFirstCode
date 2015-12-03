package com.codekun.androidfirstcode.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.codekun.androidfirstcode.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义 圆角 ImageView 组件
 *
 * {@link <a href='http://blog.csdn.net/lmj623565791/article/details/41967509'>Demo</a>}
 * Created by kun on 2015/11/17.
 */
public class RoundImageView extends ImageView {

    private static final String STATE_INSTANCE= "state_instance";
    private static final String STATE_TYPE = "state_type";
    private static final String STATE_BORDERRADIUS = "state_borderradius";

    /**
     * 圆型
     */
    public static final int TYPE_CIRCLE = 0;
    /**
     * 圆角
     */
    public static final int TYPE_ROUND = 1;

    @IntDef({
            TYPE_CIRCLE,
            TYPE_ROUND
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type{}

    /**
     * 默认圆角大小
     */
    private static final int BORDER_RADIUS_DEFAULT = 10;

    /**
     * 组件类型
     * 如 RoundImageView#TYPE_CIRCLE 为圆型， RoundImageView#TYPE_ROUND为圆角
     */
    private int mType;

    /**
     * 视图(View)的宽
     */
    private int mWidth;

    /**
     * 圆角半径
     */
    private int mRadius;

    /**
     * 圆角大小
     */
    private int mBorderRadius;

    /**
     * 绘制图形时的矩阵对象
     */
    private Matrix mMatrix;

    /**
     * 绘制图形的Paint
     */
    private Paint mPaint;

    /**
     * 渲染图形
     */
    private BitmapShader mBitmapShader;

    private RectF mRoundRect;

    /**
     * 自定义圆角ImageView组件
     <code>
     RoundImageView iv = new RoundImageView(mContext);
     iv.setImageResource(R.drawable.koala);
     iv.setType(RoundImageView.TYPE_CIRCLE);
     iv.setBorderRadius(20);
     </code>

     * @param context
     */
    public RoundImageView(Context context) {
        super(context);
        init();
    }

    /**
     * 自定义圆角ImageView组件
     * values/attrs.xml
     <code>
     <declare-styleable name="RoundImageView">
        <attr name="borderRadius" format="dimension" />
        <attr name="type">
            <flag name="circle" value="0" />
            <flag name="round" value="1" />
        </attr>
     </declare-styleable>
     </code>

     * @param context
     * @param attrs
     */
    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);

        int d = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, BORDER_RADIUS_DEFAULT, getResources().getDisplayMetrics());
        mBorderRadius = a.getDimensionPixelSize(R.styleable.RoundImageView_borderRadius, d);
        mType = a.getInt(R.styleable.RoundImageView_type, TYPE_CIRCLE);//默认为圆型

        a.recycle();
    }

    public int dp2px(int dpVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    public int getType() {
        return mType;
    }

    public void setType(@Type int type) {
        if(mType != type){
            mType = type;
            requestLayout();
        }
    }

    /**
     * 返回的是一个px值
     * @return
     */
    public int getBorderRadius() {
        return mBorderRadius;
    }

    public void setBorderRadius(int borderRadius) {
        int pxVal = dp2px(borderRadius);
        if (mBorderRadius != pxVal){
            mBorderRadius = pxVal;
            invalidate();
        }
    }

    /*
    状态的存储与恢复
    当然了，如果内存不足，而恰好我们的Activity置于后台，不幸被重启，
    或者用户旋转屏幕造成Activity重启，我们的View应该也能尽可能的去保存自己的属性。
     */
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(STATE_TYPE, mType);
        bundle.putInt(STATE_BORDERRADIUS, mBorderRadius);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle){
            Bundle bundle = (Bundle)state;
            super.onRestoreInstanceState(bundle.getParcelable(STATE_INSTANCE));
            mType = bundle.getInt(STATE_TYPE);
            mBorderRadius = bundle.getInt(STATE_BORDERRADIUS, BORDER_RADIUS_DEFAULT);
        }else{
            super.onRestoreInstanceState(state);
        }
    }
    //

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mType == TYPE_CIRCLE){
            mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
            mRadius = mWidth /2;
            setMeasuredDimension(mWidth, mWidth);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null){
            return;
        }
        initPaint();
        if (mType == TYPE_CIRCLE){
            canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
        }else if(mType == TYPE_ROUND){
            canvas.drawRoundRect(mRoundRect, mBorderRadius, mBorderRadius, mPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mType == TYPE_ROUND){
            mRoundRect = new RectF(0, 0, getWidth(), getHeight());
        }
    }

    private void init(){
        mMatrix = new Matrix();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//消除锯齿
    }

    private void initPaint(){
        Drawable drawable = getDrawable();
        if (drawable == null){
            return;
        }
        Bitmap bmp = drawableToBitmap(drawable);
        //将bmp作为着色器，就是在指定区域内绘制bmp
        mBitmapShader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        if (mType == TYPE_CIRCLE){
            int bSize = Math.min(bmp.getWidth(), bmp.getHeight());
            scale = mWidth * 1.0f / bSize;
        }else if (mType == TYPE_ROUND){
            //如果图片的宽或高与view的宽高不匹配，计算出需要缩放的比例，缩放后的图片宽高，
            //一定要大于我们view的宽高，所以我们这里取最大值
            scale = Math.max(getWidth() * 1.0f / bmp.getWidth(), getHeight() * 1.0f / bmp.getHeight());
        }
        //shaper的变换矩阵，我们这里主要用于放大或缩小
        mMatrix.setScale(scale, scale);
        //设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(mBitmapShader);
    }

    /**
     * 转化 Drawable为Bitmap
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitmap(Drawable drawable){
        if (drawable instanceof BitmapDrawable){
            BitmapDrawable bd = (BitmapDrawable)drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicWidth();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bmp;
    }


}
