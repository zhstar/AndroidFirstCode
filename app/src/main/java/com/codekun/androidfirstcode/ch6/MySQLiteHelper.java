package com.codekun.androidfirstcode.ch6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by kun on 2015/11/25.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    //创建Book表的SQL语句
    private static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer,"
            + "category_id integer,"
            + "name text)";

    //演示数据库升级
    //创建Category表的SQL语句
    private static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement,"
            + "category_name text,"
            + "category_code integer"
            +")";

    private static final String DROP_BOOK = "drop table if exists Book";

    private static final String DROP_CATEGORY = "drop table if exists Category";

    //在表 Book 添加一列 category_id
    private static final String ADD_BOOK_COLUMN = "alter table Book add column category_id integer";

    private Context mContext;

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    //数据库创建时调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext, "Create success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                db.execSQL(CREATE_CATEGORY);
                Toast.makeText(mContext, "Create category success!", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                db.execSQL(ADD_BOOK_COLUMN);
                break;
        }
    }
}
