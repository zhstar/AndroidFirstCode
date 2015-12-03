package com.codekun.androidfirstcode.ch4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;

import java.util.List;

/**
 * 新闻列表适配器
 * Created by kun on 2015/11/19.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    private Context mContext;
    private int mItemLayoutResourceId;
    private List<News> mItems;
    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        mContext = context;
        mItemLayoutResourceId = resource;
        mItems = objects;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public News getItem(int position) {
        return mItems.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(mItemLayoutResourceId, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView)convertView.findViewById(R.id.item_textView);
            holder.mImageView = (ImageView)convertView.findViewById(R.id.item_next_flag_imageView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        if (holder.mImageView != null){
            holder.mImageView.setVisibility(View.GONE);
        }

        holder.mTextView.setText(mItems.get(position).getTitle());

        return convertView;
    }

    class ViewHolder{
        TextView mTextView;
        ImageView mImageView;
    }


}
