package com.codekun.weather.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.codekun.weather.models.City;
import com.codekun.weather.models.Country;
import com.codekun.weather.models.Province;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kun on 2015/12/10.
 */
public class Utility {

    /**
     * 解释和处理服务器返回的省级数据
     * 写入数据库
     * @param manager
     * @param data 格式：01|北京,02|上海,03|天津,04|重庆,05|黑龙江,06|吉林,07|辽宁,08|内蒙古,09|河北,10|山西,11|陕西,12|山东,
    13|新疆,14|西藏,15|青海,16|甘肃,17|宁夏,18|河南,19|江苏,20|湖北,21|浙江,22|安徽,23|福建,24|江西,
    25|湖南,26|贵州,27|四川,28|广东,29|云南,30|广西,31|海南,32|香港,33|澳门,34|台湾
     * @return
     */
    public static boolean handleProvincesData(DatabaseManager manager, String data){
        //name:code
        Map<String, String> map = getMap(data);
        if (map != null){
            for (Map.Entry entry : map.entrySet() ){
                String name = entry.getKey().toString();
                String code = entry.getValue().toString();
                Province p = new Province();
                p.setName(name);
                p.setCode(code);
                manager.saveProvince(p);
            }
            return true;
        }
        return false;
    }

    /**
     * 解释和处理服务器返回的市级数据
     * @param manager
     * @param data 格式：2801|广州,2802|韶关,2803|惠州,2804|梅州,2805|汕头,2806|深圳,2807|珠海,2808|佛山,2809|肇庆,2810|湛江,2811|江门,
     *             2812|河源,2813|清远,2814|云浮,2815|潮州,2816|东莞,2817|中山,2818|阳江,2819|揭阳,2820|茂名,2821|汕尾,2822|东沙岛
     * @param provinceId 所在省级id
     * @return
     */
    public static boolean handleCitiesData(DatabaseManager manager, String data, String provinceId){
        //name:code
        Map<String, String> map = getMap(data);
        if (map != null){
            for (Map.Entry entry : map.entrySet() ){
                String name = entry.getKey().toString();
                String code = entry.getValue().toString();
                City c = new City();
                c.setName(name);
                c.setCode(code);
                c.setProvinceCode(provinceId);
                manager.saveCity(c);
            }
            return true;
        }
        return false;
    }

    /**
     * 解释和处理服务器返回的县级数据
     * @param manager
     * @param data 格式：280101|广州,280102|番禺,280103|从化,280104|增城,280105|花都,280106|天河
     * @param cityId 所在市级id
     * @return
     */
    public static boolean handleCountriesData(DatabaseManager manager, String data, String cityId){
        //{name:code}
        Map<String, String> map = getMap(data);
        if (map != null){
            for (Map.Entry entry : map.entrySet() ){
                String name = entry.getKey().toString();
                String code = entry.getValue().toString();
                Country c = new Country();
                c.setName(name);
                c.setCode(code);
                c.setCityCode(cityId);
                manager.saveCountry(c);
            }
            return true;
        }
        return false;
    }

    /**
     * 保存天气信息
     * @param context
     * @param jsonData
     */
    public static void saveWeatherInfoData(Context context, String jsonData){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("weatherinfo", jsonData);
        editor.commit();
    }

    /**
     * 读取本地保存的天气信息
     * @param context
     * @return
     */
    public static String readWeatherInfoData(Context context){
        String result = null;
        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(context);
        result = spf.getString("weatherinfo", null);
        return result;
    }

    /**
     * 将字符串数据转换成 Map<String, String>集合
     * @param data 格式：280101|广州,280102|番禺,280103|从化,280104|增城,280105|花都,280106|天河
     *             转成："广州" = "280101"
     * @return
     */
    private static Map<String, String> getMap(String data){
        Map<String, String> map = new HashMap<String, String>();
        if (!TextUtils.isEmpty(data)){
            String[] all = data.split(",");
            if (all != null && all.length > 0){
                for (String s : all){
                    String[] single = s.split("\\|");
                    String code = single[0];
                    String name = single[1];
                    map.put(name, code);
                }
                return map;
            }
        }
        return null;
    }

}
