package com.codekun.weather.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.codekun.weather.models.City;
import com.codekun.weather.models.Country;
import com.codekun.weather.models.Province;

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
    public static String DB_NAME = "Weather_V_1";

    /**
     * 数据库版本
     */
    public static int DB_VERSION = 1;

    /**
     * 表名 Province
     */
    private static final String TABLE_PROVINCE = "Province";
    /**
     * 表名 City
     */
    private static final String TABLE_CITY = "City";
    /**
     * 表名 Country
     */
    private static final String TABLE_COUNTRY = "Country";


    private Context context;

    //数据库对象
    private SQLiteDatabase db;

    //数据库打开器
    private DatabaseOpenHelper helper;

    //
    private static DatabaseManager instance;
    /**
     * 单例：获取实例
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
            db.insert(TABLE_PROVINCE, null, values);

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
            values.put("provinceCode", city.getProvinceCode());
            db.insert(TABLE_CITY, null, values);
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
            values.put("cityCode", country.getCityCode());
            db.insert(TABLE_COUNTRY, null, values);
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
            cursor = db.query(TABLE_PROVINCE, null, null, null, null, null, null);
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
     * @param provinceCode
     * @return
     */
    public List<City> getCities(String provinceCode){
        List<City> cities = new ArrayList<City>();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_CITY, null, "provinceCode = ?", new String[]{provinceCode}, null, null, null);
            if (cursor.moveToFirst()){
                do {
                    City city = new City();
                    city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    city.setName(cursor.getString(cursor.getColumnIndex("name")));
                    city.setCode(cursor.getString(cursor.getColumnIndex("code")));
                    city.setProvinceCode(cursor.getString(cursor.getColumnIndex("provinceCode")));
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
     * @param cityCode
     * @return
     */
    public List<Country> getCountries(String cityCode){
        List<Country> countries = new ArrayList<Country>();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_COUNTRY, null, "cityCode = ?", new String[]{cityCode}, null, null, null);
            if (cursor.moveToFirst()){
                do {
                    Country country = new Country();
                    country.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    country.setName(cursor.getString(cursor.getColumnIndex("name")));
                    country.setCode(cursor.getString(cursor.getColumnIndex("code")));
                    country.setCityCode(cursor.getString(cursor.getColumnIndex("cityCode")));
                    countries.add(country);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return countries;
    }




}
