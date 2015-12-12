package com.codekun.weather;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.codekun.common.core.TitleBarActivity;

public class ChooseAreaActivity extends TitleBarActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_choose_area;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTitleBar().setRightBtnVisibility(View.GONE);
        setTitle("选择城市");
        ProvinceListFragment fragment = ProvinceListFragment.getInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.weather_choose_area_layout, fragment, ProvinceListFragment.TAG);
        //ft.addToBackStack(null);
        ft.commit();
    }



}
