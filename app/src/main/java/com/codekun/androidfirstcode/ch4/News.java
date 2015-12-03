package com.codekun.androidfirstcode.ch4;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kun on 2015/11/19.
 */
public class News implements Parcelable {

    /**
     * 新闻标题
     * @return
     */
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    /**
     * 新闻内容
     * @return
     */
    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String toString(){
        return "[News->title:"+mTitle + ", content:"+mContent + "]";
    }

    private String mTitle;
    private String mContent;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mContent);
    }

    public static Parcelable.Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            News news = new News();
            news.setTitle(source.readString());
            news.setContent(source.readString());
            return news;
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

}
