package com.codekun.weather.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库创建类
 * Created by kun on 15/12/7.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper{

    /**
     * 创建Province表语句
     */
    private static final String CREATE_PROVINCE = "create table Province (" +
            "id integer primary key autoincrement," +
            "name text," +
            "code text" +
            ")";

    /**
     * 创建City表语句
     */
    private static final String CREATE_CITY = "create table City (" +
            "id integer primary key autoincrement," +
            "name text," +
            "code text," +
            "provinceCode text)";

    /**
     * 创建Country表语句
     */
    private static final String CREATE_COUNTRY = "create table Country (" +
            "id integer primary key autoincrement," +
            "name text," +
            "code text," +
            "cityCode text)";



    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
