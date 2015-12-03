package com.codekun.androidfirstcode.ch10;

/**
 * Created by kun on 2015/12/2.
 */
public class App {
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        mVersion = version;
    }

    public String toString(){
        return "App:[id:"+mId+", name:"+mName+", version:"+mVersion+"]";
    }

    private int mId;
    private String mName;
    private String mVersion;
}
