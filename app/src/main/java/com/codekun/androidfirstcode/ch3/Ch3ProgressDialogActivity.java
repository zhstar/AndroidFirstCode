package com.codekun.androidfirstcode.ch3;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;


public class Ch3ProgressDialogActivity extends TitleBarActivity {

    private Context mContext;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        Button btn = (Button)findViewById(R.id.ch3_show_progressdialog);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(mContext);
                dialog.setTitle("ProgressDialog");
                dialog.setMessage("Loading...");
                dialog.setCancelable(true);//设置为false是不能通过返回键取消的
                dialog.show();
            }
        });


    }

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if(dialog != null){
                dialog.dismiss();
            }
        }
        return super.onKeyDown(keyCode, event);
    }*/

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch3_progress_dialog;
    }
}
