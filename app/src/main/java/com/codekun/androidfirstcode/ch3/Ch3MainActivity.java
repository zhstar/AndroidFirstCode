package com.codekun.androidfirstcode.ch3;

import android.app.Activity;
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

public class Ch3MainActivity extends TitleBarActivity {

/*    public static void startActivity(Context context, String title){
        Intent i = new Intent(context, Ch3MainActivity.class);
        Bundle b = new Bundle();
        b.putString("title", title);
        i.putExtras(b);
        context.startActivity(i);
    }*/

    private String[] mItems =new String[]{
            "TextView",
            "EditText",
            "ImageView",
            "ProgressBar",
            "AlertDialog",
            "ProgressDialog",
            "UIBestPractise"
    };
    private Class[] mActivities = new Class[]{
            Ch3TextViewActivity.class,
            Ch3EditTextActivity.class,
            Ch3ImageViewActivity.class,
            Ch3ProgressBarActivity.class,
            Ch3AlertDialogActivity.class,
            Ch3ProgressDialogActivity.class,
            Ch3PractiseActivity.class
    };

    private ListView mListView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        String title = getIntent().getExtras().getString("title");
        setTitle(title);


        mListView = (ListView)findViewById(R.id.ch3_listView);
        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.list_item, mItems);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= mActivities.length){
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
        return R.layout.activity_ch3_main;
    }

}
