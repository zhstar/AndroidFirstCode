package com.codekun.androidfirstcode.ch4;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Ch4NewsContentFragment extends Fragment {


    private View mView;

    private TextView mTitleTextView;
    private TextView mContentTextView;

    private News news;

    public Ch4NewsContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_ch4_news_content_fragment, container, false);
        mTitleTextView = (TextView)mView.findViewById(R.id.ch4_news_content_title_textView);
        mContentTextView = (TextView)mView.findViewById(R.id.ch4_news_content_content_textView);
        if(news != null){
            refresh(news);
        }
        return mView;
    }

    /**
     * 显示新闻
     * @param news
     */
    public void refresh(News news){
        if(mTitleTextView != null && mContentTextView != null){
            mTitleTextView.setText(news.getTitle());
            mContentTextView.setText(news.getContent());
        }
    }


    public void setNews(News news) {
        this.news = news;
    }
}
