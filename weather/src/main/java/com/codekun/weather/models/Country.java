package com.codekun.weather.models;

/**
 * 县级类
 * Created by kun on 15/12/7.
 */
public class Country {

    private int id;
    private String name;
    private String code;
    private String cityCode;

    /**
     * 县序号
     * @return
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 县的名称
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 县的代码号
     * @return
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 县所属的市序号
     * @return
     */
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }


    /**
     *
     * @return
     */
    public String toString(){
        return "Country->[id="+id+",name="+name+",code="+code+"cityCode="+ cityCode +"]";
    }

}
