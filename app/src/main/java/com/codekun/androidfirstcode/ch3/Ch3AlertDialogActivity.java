package com.codekun.androidfirstcode.ch3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;
import com.codekun.androidfirstcode.widgets.RoundImageView;

public class Ch3AlertDialogActivity extends TitleBarActivity {
    private Context mContext;
    private Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
        Button show_alertdialog = (Button)findViewById(R.id.ch3_show_alertdialog_button);
        show_alertdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("AlertDialog");
                builder.setMessage("Message");
                builder.setIcon(R.drawable.simle);
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        Button custom_alertdialog = (Button)findViewById(R.id.ch3_show_custom_alertdialog_button);
        custom_alertdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = new LinearLayout(mContext);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                CheckBox checkBox = new CheckBox(mContext);
                checkBox.setText("自动调节亮度");
                checkBox.setChecked(false);
                layout.addView(checkBox);
                ProgressBar progressBar = new ProgressBar(mContext, null, android.R.attr.progressBarStyleHorizontal);
                progressBar.setMax(100);
                progressBar.setProgress(40);
                layout.addView(progressBar);

                View imageViewParent = LayoutInflater.from(mContext).inflate(R.layout.layout_roundimageivew, layout);
                RoundImageView imageView = (RoundImageView) imageViewParent.findViewById(R.id.roundImageView);
                imageView.setType(RoundImageView.TYPE_ROUND);
                LayoutInflater.from(mContext).inflate(R.layout.layout_roundimageivew, layout);

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("AlertDialog");
                // builder.setMessage("Message");
                builder.setView(layout);
                builder.setIcon(R.drawable.simle);
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        RoundImageView iv = new RoundImageView(mContext);
        iv.setImageResource(R.drawable.koala);
        iv.setType(RoundImageView.TYPE_CIRCLE);
        iv.setBorderRadius(20);

        addContentView(iv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch3_alert_dialog;
    }

}
