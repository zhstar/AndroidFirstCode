package com.codekun.androidfirstcode.ch4;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Ch4NewsListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView newsListView;
    private List<News> mItems= new ArrayList<News>();
    private NewsAdapter adapter;

    //是否双页，布局两个fragment
    private Boolean isTwoPage;

    public Ch4NewsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_ch4_news_list_fragment, container, false);
        newsListView = (ListView)v.findViewById(R.id.ch4_news_listView);

        mItems = getNews();

        adapter = new NewsAdapter(getActivity(), R.layout.list_item, mItems);

        newsListView.setAdapter(adapter);

        newsListView.setOnItemClickListener(this);


        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.ch4_news_content_fragment) != null){
            isTwoPage = true;
        }else{
            isTwoPage = false;
        }
    }

    /**
     * 直接拷贝 书上的
     * @return
     */
    private List<News> getNews() {
        News news1 = new News();
        news1.setTitle("Succeed in College as a Learning Disabled Student");
        news1.setContent("College freshmen will soon learn to live with a" +
                "roommate, adjust to a new social scene and survive less-than-stellar" +
                "dining hall food. Students with learning disabilities will face these" +
                "transitions while also grappling with a few more hurdles.");
        News news2 = new News();
        news2.setTitle("Google Android exec poached by China's Xiaomi");
        news2.setContent("China's Xiaomi has poached a key Google executive"+
                "involved in the tech giant's Android phones, in a move seen as a coup"+
        "for the rapidly growing Chinese smartphone maker.");
        mItems.add(news1);
        mItems.add(news2);
        return mItems;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        News news = mItems.get(position);

        Log.d("AndroidFirstCode", news.toString());

        if (isTwoPage){
            Ch4NewsContentFragment fragment = (Ch4NewsContentFragment)getActivity().getFragmentManager().findFragmentById(R.id.ch4_news_content_fragment);
            if (fragment != null){
                fragment.refresh(news);
            }
        }else{
            Ch4NewsContentActivity.startActivity(getActivity(), news);
        }

    }
}
