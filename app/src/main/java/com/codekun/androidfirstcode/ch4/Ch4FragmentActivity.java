package com.codekun.androidfirstcode.ch4;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

public class Ch4FragmentActivity extends TitleBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch4_fragment;
    }
}
