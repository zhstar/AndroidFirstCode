package com.codekun.weather.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kun on 2015/12/11.
 */
public class WeatherInfo {
    private String city;
    private String cityId;
    private String temp1;
    private String temp2;
    private String weather;
    private String img1;
    private String img2;
    private String ptime;

    /**
     * 创建天气信息实例
     * @param jsonData 如：{"weatherinfo":{"city":"江阴","cityid":"101190202","temp1":"17℃","temp2":"10℃","weather":"小雨","img1":"d7.gif","img2":"n7.gif","ptime":"08:00"}}
     * @return
     */
    public static WeatherInfo createInstance(String jsonData){
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject wiJSON = jsonObject.getJSONObject("weatherinfo");
            WeatherInfo wi = new WeatherInfo();
            wi.setCity(wiJSON.getString("city"));
            wi.setCityId(wiJSON.getString("cityid"));
            wi.setTemp1(wiJSON.getString("temp1"));
            wi.setTemp2(wiJSON.getString("temp2"));
            wi.setWeather(wiJSON.getString("weather"));
            wi.setImg1(wiJSON.getString("img1"));
            wi.setImg2(wiJSON.getString("img2"));
            wi.setPtime(wiJSON.getString("ptime"));
            return wi;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 城市名
     * @return
     */
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 城市代码
     * @return
     */
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    /**
     * 最大气温
     * @return
     */
    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    /**
     * 最小气温
     * @return
     */
    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    /**
     * 发布时间
     * @return
     */
    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }
}
