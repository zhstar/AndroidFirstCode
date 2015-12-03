package com.codekun.androidfirstcode.ch4;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Ch4RightFragment extends Fragment implements View.OnClickListener{


    public Ch4RightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_ch4_right_fragment, container, false);

        Button btn = (Button)v.findViewById(R.id.ch4_right_fragment_btn);
        btn.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
        //碎片与碎片之间通信
        FragmentManager fm = getActivity().getFragmentManager();
        Ch4LeftFragment leftFragment = (Ch4LeftFragment)fm.findFragmentById(R.id.ch4_left_fragment);
        leftFragment.setViewVisibility(View.GONE);
    }
}
