package com.codekun.androidfirstcode.ch3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.widgets.RoundImageView;

import java.util.List;

/**
 * Created by kun on 2015/11/18.
 */
public class MsgAdapter extends ArrayAdapter<Msg> {
    private Context mContext;
    private int mItemLayoutResourceId;
    private List<Msg> mMsgs;
    public MsgAdapter(Context context, int itemLayoutResourceId, List<Msg> msgs) {
        super(context, itemLayoutResourceId, msgs);
        mContext = context;
        mItemLayoutResourceId = itemLayoutResourceId;
        mMsgs = msgs;
    }

    @Override
    public int getCount() {
        return mMsgs.size();
    }

    @Override
    public Msg getItem(int position) {
        return mMsgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(mItemLayoutResourceId, null);
            holder.leftIcon = (ImageView)convertView.findViewById(R.id.ch3_msg_left_icon);
            holder.rightIcon = (ImageView)convertView.findViewById(R.id.ch3_msg_right_icon);
            holder.backgroundView = (ViewGroup)convertView.findViewById(R.id.ch3_msg_backgroundView);
            holder.textView = (TextView)convertView.findViewById(R.id.ch3_msg_textView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        Msg m = mMsgs.get(position);
        //RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)holder.backgroundView.getLayoutParams();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (m.getType() == Msg.TYPE_RECEIVED){
            holder.backgroundView.setBackgroundResource(R.drawable.ch3_msg_left_bg);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            holder.leftIcon.setVisibility(View.VISIBLE);
            holder.rightIcon.setVisibility(View.INVISIBLE);
        }else if (m.getType() == Msg.TYPE_SENT) {
            holder.backgroundView.setBackgroundResource(R.drawable.ch3_msg_right_bg);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.leftIcon.setVisibility(View.INVISIBLE);
            holder.rightIcon.setVisibility(View.VISIBLE);
        }
        holder.backgroundView.setLayoutParams(params);
        holder.textView.setText(m.getText());

        return convertView;
    }

    class ViewHolder{
        ImageView leftIcon;
        ImageView rightIcon;
        TextView textView;
        ViewGroup backgroundView;
    }

}
