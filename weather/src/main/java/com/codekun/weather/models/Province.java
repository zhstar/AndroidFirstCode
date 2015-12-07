package com.codekun.weather.models;

/**
 * 省级类
 * Created by kun on 15/12/7.
 */
public class Province {

    private int id;
    private String name;
    private String code;

    /**
     * 序号
     * @return
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 省的名称
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 省的代码号
     * @return
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     */
    public String toString(){
        return "Province->[id="+id+",name="+name+",code="+code+"]";
    }



}
