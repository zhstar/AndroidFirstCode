package com.codekun.androidfirstcode.ch2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

public class Ch2ThirdActivity extends TitleBarActivity {

    /**
     * 演示启动活动的最佳写法
     * @param context
     * @param title
     */
    public static void startActivity(Context context, String title){
        Intent i = new Intent(context, Ch2ThirdActivity.class);
        Bundle b = new Bundle();
        b.putString("title", title);
        i.putExtras(b);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        //设置标题栏
        if(bundle != null){
            String title = bundle.getString("title");
            if (title == null){
                title = "Default Title";
            }
            setTitle(title);
        }


    }
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch2_third;
    }


}
