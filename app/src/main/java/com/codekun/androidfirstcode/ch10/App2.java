package com.codekun.androidfirstcode.ch10;

/**
 * Created by kun on 2015/12/2.
 */
public class App2 {

    private String id;
    private String name;
    private String version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String toString(){
        return "App:[id:"+ id +", name:"+ name +", version:"+ version +"]";
    }

}
