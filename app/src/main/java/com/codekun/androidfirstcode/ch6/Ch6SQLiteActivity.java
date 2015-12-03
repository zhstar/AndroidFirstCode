package com.codekun.androidfirstcode.ch6;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

public class Ch6SQLiteActivity extends TitleBarActivity implements View.OnClickListener {

    private MySQLiteHelper mMySQLiteHelper;
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch6_sqlite;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMySQLiteHelper = new MySQLiteHelper(this, "BookStore1.db", null, 2);

        findViewById(R.id.ch6_sqlite_create_table_button).setOnClickListener(this);
        findViewById(R.id.ch6_sqlite_add_button).setOnClickListener(this);
        findViewById(R.id.ch6_sqlite_update_button).setOnClickListener(this);
        findViewById(R.id.ch6_sqlite_delete_button).setOnClickListener(this);
        findViewById(R.id.ch6_sqlite_query_button).setOnClickListener(this);

        findViewById(R.id.ch6_sqlite_transaction_button).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        //调用getWritableDatabase()方法，当数据库不存在时，
        // //就会创建数据库并调用 SQLiteOpenHelper#onCreate()这个方法
        SQLiteDatabase db = mMySQLiteHelper.getWritableDatabase();
        ContentValues values = null;
        switch (v.getId()){
            case R.id.ch6_sqlite_create_table_button:
                //调用 getWriteableDatabase()方法即可创建数据库
                break;
            case R.id.ch6_sqlite_add_button:
                //添加数据
                values = new ContentValues();
                values.put("author", "CodeKun");
                values.put("price", 99.9);
                values.put("pages", 999);
                values.put("name", "Android");
                db.insert("Book", null, values);
                values.clear();
                //再添加一条数据
                values.put("author", "郭lin");
                values.put("price", 99.9);
                values.put("pages", 888);
                values.put("name", "第一行代码");
                db.insert("Book", null, values);
                values.clear();
                values.put("author", "Kun");
                values.put("price", 95.9);
                values.put("pages", 888);
                values.put("name", "Android App");
                db.insert("Book", null, values);
                break;
            case R.id.ch6_sqlite_update_button:
                values = new ContentValues();
                values.put("price", 55.3);
                db.update("Book", values, "name = ?", new String[]{"Android"});
                break;
            case R.id.ch6_sqlite_delete_button:
                //删除 name = "Android App" 的数据
                db.delete("Book", "name = ?", new String[]{"Android App"});
                break;
            case R.id.ch6_sqlite_query_button:

                Cursor cursor = db.query("Book", null, null, null, null, null, null);

                if (cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        String log = "name:" + name + ", author:" + author + ", price:" + price + ", pages:" + pages;
                        Log.d("AndroidFirstCode", log);
                    }while (cursor.moveToNext());
                }
                cursor.close();

                break;
            case R.id.ch6_sqlite_transaction_button:
                db.beginTransaction();
                try{
                    db.delete("Book", null, null);
                    if (true){
                        //在这里手机抛出一个异常，让事务失败
                        throw new NullPointerException();
                    }
                    values = new ContentValues();
                    values.put("author", "Kun");
                    values.put("price", 15.9);
                    values.put("pages", 48);
                    values.put("name", "Android_Program");
                    db.insert("Book", null, values);
                    db.setTransactionSuccessful();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    db.endTransaction();
                }
                break;
        }
    }


}
