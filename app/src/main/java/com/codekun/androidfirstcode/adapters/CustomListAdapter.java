package com.codekun.androidfirstcode.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;

/**
 * Created by kun on 2015/11/12.
 */
public class CustomListAdapter extends BaseAdapter {

    private Context mContext;
    private int mLayoutId;
    private String[] mItems;


    /**
     *
     * @param context
     * @param layoutId ListView 子项布局id
     * @param items
     */
    public CustomListAdapter(Context context, int layoutId, String[] items){
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.length;
    }

    @Override
    public Object getItem(int position) {
        return mItems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(mLayoutId, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView)convertView.findViewById(R.id.item_textView);
            convertView.setTag( holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.mTextView.setText(mItems[position]);

        return convertView;
    }

    private class ViewHolder{
        TextView mTextView;
    }

}
