package com.codekun.androidfirstcode.ch4;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

public class Ch4NewsContentActivity extends TitleBarActivity {

    public static void startActivity(Context context, News news){
        Intent i = new Intent(context, Ch4NewsContentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", news.getTitle());
        bundle.putParcelable("news", news);
        i.putExtras(bundle);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        News news = null;
        Intent intent = getIntent();
        if(intent != null){
            Bundle bundle = intent.getExtras();
            if (bundle!= null){
                String title = bundle.getString("title");
                news = (News)bundle.getParcelable("news");
                getTitleBar().setTitle(title);
            }
        }

        //动态添加 fragment
        Ch4NewsContentFragment fragment = new Ch4NewsContentFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.ch4_news_content_container, fragment);
        ft.commit();
        if (news != null){
            fragment.setNews(news);
        }

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch4_news_content;
    }
}
