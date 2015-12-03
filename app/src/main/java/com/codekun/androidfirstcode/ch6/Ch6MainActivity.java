package com.codekun.androidfirstcode.ch6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.adapters.CustomListAdapter;
import com.codekun.androidfirstcode.core.TitleBarActivity;

public class Ch6MainActivity extends TitleBarActivity {

    private String[] mItems =new String[]{
            "文件存储",
            "SharedPreferences存储",
            "SQLite数据库存储",
            "SQLite数据库的最佳实践"
    };
    private Class[] mActivities = new Class[]{
            Ch6FileActivity.class,
            Ch6SharedPreferencesfActivity.class,
            Ch6SQLiteActivity.class,
            Ch6SQLiteActivity.class
    };
    private ListView mListView;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mContext = this;
        mListView = (ListView)findViewById(R.id.ch6_listView);
        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.list_item, mItems);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= mActivities.length) {
                    Toast.makeText(mContext, mItems[position] + "\n未完成", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(mContext, mActivities[position]);
                Bundle b = new Bundle();
                b.putString("title", mItems[position]);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch6_main;
    }

}
