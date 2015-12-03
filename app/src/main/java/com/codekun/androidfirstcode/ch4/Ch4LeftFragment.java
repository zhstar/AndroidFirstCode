package com.codekun.androidfirstcode.ch4;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;


public class Ch4LeftFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_ch4_left_fragment, container, false);
        Button add_fragment = (Button)v.findViewById(R.id.ch4_add_fragment_button);
        add_fragment.setOnClickListener(this);

        return v;
    }

    public void setViewVisibility(int visibility){
        getView().setVisibility(visibility);
    }


    @Override
    public void onClick(View v) {
        //动态添加fragment
        Ch4RightFragment fragment = new Ch4RightFragment();
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.ch4_fragment_right_fragment_layout, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}
