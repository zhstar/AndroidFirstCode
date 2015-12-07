package com.codekun.weather.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.codekun.weather.models.City;
import com.codekun.weather.models.Country;
import com.codekun.weather.models.Province;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库管理
 * Created by kun on 15/12/7.
 */
public class DatabaseManager {

    /**
     * 数据库名称
     */
    public static String DB_NAME = "Weather";

    /**
     * 数据库版本
     */
    public static int DB_VERSION = 1;

    private Context context;

    //数据库对象
    private SQLiteDatabase db;

    //数据库打开器
    private DatabaseOpenHelper helper;

    //
    private static DatabaseManager instance;

    /**
     * 获取实例
     * @param context
     * @return
     */
    public synchronized static DatabaseManager getInstance(Context context){
        if (instance == null){
            instance = new DatabaseManager(context);
        }
        return instance;
    }


    //单例模式
    private DatabaseManager(Context context){
        this.context = context;
        helper = new DatabaseOpenHelper(context, DB_NAME, null, DB_VERSION);
        db = helper.getWritableDatabase();
    }

    /**
     * 将省份信息保存到数据库
     * @param province
     */
    public void saveProvince(Province province){
        if (db != null){
            ContentValues values = new ContentValues();
            values.put("name", province.getName());
            values.put("code", province.getCode());
            db.insert("Province", null, values);

            Toast.makeText(context, "省份信息保存成功。", Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * 将市信息保存到数据库
     * @param city
     */
    public void saveCity(City city){
        if (db != null){
            ContentValues values = new ContentValues();
            values.put("name", city.getName());
            values.put("code", city.getCode());
            values.put("provinceId", city.getProvinceId());
            db.insert("City", null, values);
        }
    }

    /**
     * 将县级信息保存在数据库
     * @param country
     */
    public void saveCountry(Country country){
        if (db != null){
            ContentValues values = new ContentValues();
            values.put("name", country.getName());
            values.put("code", country.getCode());
            values.put("cityId", country.getCityId());
            db.insert("Country", null, values);
        }
    }

    /**
     * 从数据库读取所有省份信息
     * @return
     */
    public List<Province> getProvinces(){
        List<Province> provinces = new ArrayList<Province>();
        Cursor cursor = null;
        try{
            cursor = db.query("Province", null, null, null, null, null, null);
            if (cursor.moveToFirst()){
                do {
                    Province p = new Province();
                    p.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    p.setName(cursor.getString(cursor.getColumnIndex("name")));
                    p.setCode(cursor.getString(cursor.getColumnIndex("code")));
                    provinces.add(p);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return provinces;
    }

    /**
     * 从数据库读取指定省份序号的所有市级信息
     * @param provinceId
     * @return
     */
    public List<City> getCities(int provinceId){
        List<City> cities = new ArrayList<City>();
        Cursor cursor = null;
        try {
            cursor = db.query("City", null, "provinceId = ?", new String[]{String.valueOf(provinceId)}, null, null, null);
            if (cursor.moveToFirst()){
                do {
                    City city = new City();
                    city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    city.setName(cursor.getString(cursor.getColumnIndex("name")));
                    city.setCode(cursor.getString(cursor.getColumnIndex("code")));
                    city.setProvinceId(cursor.getInt(cursor.getColumnIndex("provinceId")));
                    cities.add(city);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return cities;
    }

    /**
     * 从数据库中读取指定市级序号的所有县级信息
     * @param cityId
     * @return
     */
    public List<Country> getCountries(int cityId){
        List<Country> countries = new ArrayList<Country>();
        Cursor cursor = null;
        try {
            cursor = db.query("Country", null, "cityId = ?", new String[]{String.valueOf(cityId)}, null, null, null);
            if (cursor.moveToFirst()){
                do {
                    Country country = new Country();
                    country.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    country.setName(cursor.getString(cursor.getColumnIndex("name")));
                    country.setCode(cursor.getString(cursor.getColumnIndex("code")));
                    country.setCityId(cursor.getInt(cursor.getColumnIndex("cityId")));
                    countries.add(country);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return countries;
    }



    /**
     * 测试函数
     */
    public static void test(Context context){
        DB_NAME = "weather_test4";
        DatabaseManager manager = DatabaseManager.getInstance(context);

        //写入省份信息
        Province p1 = new Province();//id = 1
        p1.setName("广东");
        p1.setCode("10");

        Province p2 = new Province(); // id = 2
        p2.setName("北京");
        p2.setCode("20");

        manager.saveProvince(p1);
        manager.saveProvince(p2);

        //写入市级信息
        City c1 = new City();
        c1.setName("广州");
        c1.setCode("1001");
        c1.setProvinceId(1);//
        City c2 = new City();
        c2.setName("深圳");
        c2.setCode("1002");
        c2.setProvinceId(1);

        manager.saveCity(c1);
        manager.saveCity(c2);

        //写入广州市县级信息
        Country cc1 = new Country();
        cc1.setName("天河");
        cc1.setCode("100101");
        cc1.setCityId(1);
        Country cc2 = new Country();
        cc2.setName("海珠");
        cc2.setCode("100102");
        cc2.setCityId(1);

        manager.saveCountry(cc1);
        manager.saveCountry(cc2);

        //写入深圳市县级信息
        Country ccc1 = new Country();
        ccc1.setName("南山");
        ccc1.setCode("100201");
        ccc1.setCityId(2);
        Country ccc2 = new Country();
        ccc2.setName("福田");
        ccc2.setCode("100202");
        ccc2.setCityId(2);

        manager.saveCountry(ccc1);
        manager.saveCountry(ccc2);

        List<Province> provinces = manager.getProvinces();
        for (int i = 0; i < provinces.size(); ++i){
            System.out.println(provinces.get(i).toString());
        }

        List<City> cities = manager.getCities(1);
        for (int i = 0; i < cities.size(); ++i){
            System.out.println(cities.get(i).toString());
            List<Country> countries = manager.getCountries(cities.get(i).getId());
            for (int j = 0; j < countries.size(); ++j){
                System.out.println(countries.get(j).toString());
            }
        }


    }

}
