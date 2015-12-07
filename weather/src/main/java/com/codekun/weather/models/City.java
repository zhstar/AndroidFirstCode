package com.codekun.weather.models;

/**
 * 市级类
 * Created by kun on 15/12/7.
 */
public class City {

    private int id;
    private String name;
    private String code;
    private int provinceId;

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
     * 市的名称
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 市的代码号
     * @return
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 该市所属的省份序号
     * @return
     */
    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    /**
     *
     * @return
     */
    public String toString(){
        return "City->[id="+id+",name="+name+",code="+code+",provinceId="+provinceId+"]";
    }

}
