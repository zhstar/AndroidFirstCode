package com.codekun.androidfirstcode.ch3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

public class Ch3EditTextActivity extends TitleBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button login_demo = (Button)findViewById(R.id.ch3_login_demo_button);
        login_demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.codekun.androidfirstcode.ACTION_VIEW_LOGIN");
                startActivity(i);
            }
        });

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch3_edit_text;
    }

}
