package com.codekun.weather.database;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codekun.weather.models.City;
import com.codekun.weather.models.Country;
import com.codekun.weather.models.Province;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by kun on 2015/12/11.
 */
public class DataLoader {

    //省级数据
    public static final int LEVEL_PROVINCE = 0;
    //市级数据
    public static final int LEVEL_CITY = 1;
    //县级数据
    public static final int LEVEL_COUNTRY = 2;
    //天气信息
    public static final int LEVEL_WEATHER_INFO = 3;

    @IntDef({
            LEVEL_PROVINCE,
            LEVEL_CITY,
            LEVEL_COUNTRY,
            LEVEL_WEATHER_INFO
    })
    @Retention(RetentionPolicy.SOURCE)
    private @interface level{}

    /**
     * 从服务器加载数据
     * @param context
     * @param serverUrl 服务器链接
     * @param listener  加载结果监听
     * @param level 加载的数据级别
     * @param parentLevelCode  父级code码
     */
    public static void queryFromServer(final Context context, String serverUrl, final ResultListener listener, @level final int level, @Nullable final String parentLevelCode){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (level == LEVEL_PROVINCE){
                    if (Utility.handleProvincesData(DatabaseManager.getInstance(context), s)){
                        queryProvinces(context, null, listener);
                    }else{
                        if (listener != null){
                            listener.onError("错误：获取不到省级数据");
                        }
                    }
                }else if(level == LEVEL_CITY){
                    if (Utility.handleCitiesData(DatabaseManager.getInstance(context), s, parentLevelCode)){
                        queryCities(context, null, listener, parentLevelCode);
                    }else{
                        if (listener != null){
                            listener.onError("错误：获取不到市级数据");
                        }
                    }
                }else if (level == LEVEL_COUNTRY){
                    if (Utility.handleCountriesData(DatabaseManager.getInstance(context), s, parentLevelCode)){
                        queryCountries(context, null, listener, parentLevelCode);
                    }else{
                        if (listener != null){
                            listener.onError("错误：获取不到县级数据");
                        }
                    }
                }else{
                    if (listener != null){
                        listener.onComplete(s);
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (listener != null){
                    listener.onError(volleyError.getMessage());
                }
            }
        });
        queue.add(request);
    }

    /**
     * 检索中国所有省级数据
     * 程序先从数据库中读取，如果数据库不存在数据，则从指定的服务器链接中加载，加载完成之后写入数据库
     * @param context
     * @param serverUrl
     * @param listener
     */
    public synchronized static void queryProvinces(final Context context, String serverUrl, final ResultListener listener){
        final DatabaseManager manager = DatabaseManager.getInstance(context);
        List<Province> provinces = manager.getProvinces();
        if (provinces.size() > 0){
            if (listener != null){
                listener.onCompleteProvince(provinces);
            }
        }else{
            queryFromServer(context, serverUrl, listener, LEVEL_PROVINCE, null);
        }
    }

    public synchronized static void queryCities(Context context, String serverUrl, ResultListener listener, String provinceCode){
        final DatabaseManager manager = DatabaseManager.getInstance(context);
        List<City> cities = manager.getCities(provinceCode);
        if (cities.size() > 0){
            if (listener != null){
                listener.onCompleteCity(cities);
            }
        }else{
            queryFromServer(context, serverUrl, listener, LEVEL_CITY, provinceCode);
        }
    }

    public synchronized static void queryCountries(Context context, String serverUrl, ResultListener listener, String cityCode){
        final DatabaseManager manager = DatabaseManager.getInstance(context);
        List<Country> countries = manager.getCountries(cityCode);
        if (countries.size() > 0){
            if (listener != null){
                listener.onCompleteCountry(countries);
            }
        }else{
            queryFromServer(context, serverUrl, listener, LEVEL_COUNTRY, cityCode);
        }
    }

    /**
     * 获取指定县级的天气信息
     * @param context
     * @param countryCode
     * @param listener
     */
    public static void queryWeatherInfo(final Context context, String countryCode, final ResultListener listener){
        //先获取天气代码
        queryFromServer(context, getServerUrl(countryCode), new ResultListener(){
            @Override
            public void onComplete(Object obj) {
                String data = (String)obj;
                String[] codes = data.split("\\|");
                if (codes != null && codes.length > 1){
                    String weatherCode = codes[1];//天气代码
                    String weatherInfoUrl = getWeatherInfoServerUrl(weatherCode);//天气信息链接
                    //获取天气信息
                    queryFromServer(context, weatherInfoUrl, listener, LEVEL_WEATHER_INFO, null);
                }
            }
        }, LEVEL_WEATHER_INFO, null);
    }

    /**
     * 省级数据链接
     * @return
     */
    public static String getServerUrl(){
        return getServerUrl(null);
    }

    /**
     * 获取服务器链接
     * 省级数据链接 http://www.weather.com.cn/data/list3/city.xml
     * 市级数据链接 http://www.weather.com.cn/data/list3/city[19:省代码].xml
     * 县级数据链接 http://www.weather.com.cn/data/list3/city[1904:市代码].xml
     * 县级天气代码数据链接 http://www.weather.com.cn/data/list3/city[190404:县代码].xml
     * @return
     */
    public static String getServerUrl(String code){
        return "http://www.weather.com.cn/data/list3/city" + (code == null? "" : code) + ".xml";
    }

    /**
     * 根据天气代码获取天气信息的服务器链接
     * @param weatherCode
     * @return
     */
    public static String getWeatherInfoServerUrl(String weatherCode){
        return "http://www.weather.com.cn/data/cityinfo/" + weatherCode + ".html";
    }

    /**
     *
     */
    public static class ResultListener {

        public void onComplete(Object obj){

        }

        /**
         * 加载省级数据完成时回调
         * @param resultList
         */
        public void onCompleteProvince(List<Province> resultList){

        }

        /**
         * 加载市级数据完成时回调
         * @param resultList
         */
        public void onCompleteCity(List<City> resultList){

        }

        /**
         * 加载县级数据完成时回调
         * @param resultList
         */
        public void onCompleteCountry(List<Country> resultList){

        }

        /**
         * 加载错误时回调
         * @param e
         */
        public void onError(Exception e){

        }
        /**
         * 加载错误时回调
         * @param errorMsg
         */
        public void onError(String errorMsg){

        }
    }

}
