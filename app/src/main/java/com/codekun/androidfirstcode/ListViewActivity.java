package com.codekun.androidfirstcode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.codekun.androidfirstcode.adapters.CustomListAdapter;
import com.codekun.androidfirstcode.core.TitleBarActivity;

/**
 * Created by kun on 2015/11/27.
 */
public abstract class ListViewActivity extends TitleBarActivity {


    protected String[] mItems = null;
    protected Class[] mActivities = null;

    protected ListView mListView;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        mItems = getItems();
        mActivities = getActivities();

        mListView = (ListView)findViewById(getListViewId());
        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.list_item, mItems);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= mActivities.length) {
                    Toast.makeText(mContext, mItems[position] + "\n未完成", Toast.LENGTH_SHORT).show();
                    return;
                }
                Class c = mActivities[position];
                if (c == null){
                    Toast.makeText(mContext, mItems[position] + "\n未完成", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(mContext, c);
                Bundle b = new Bundle();
                b.putString("title", mItems[position]);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    protected abstract int getListViewId();

    protected abstract String[] getItems();

    protected abstract Class[] getActivities();

}
