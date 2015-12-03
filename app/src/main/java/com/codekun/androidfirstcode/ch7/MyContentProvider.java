package com.codekun.androidfirstcode.ch7;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.codekun.androidfirstcode.ch6.MySQLiteHelper;

/**
 * 内容提供器
 * 演示操作 Ch6 SQLite数据库创建的数据
 * 此提供器使用时必须在AndroidManifest.xml加入
 <provider
 android:authorities="com.codekun.androidfirstcode.provider"
 android:name="com.codekun.androidfirstcode.ch7.MyContentProvider">
 </provider>
 * Created by kun on 2015/11/27.
 */
public class MyContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.codekun.androidfirstcode.provider";

    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static final String TABLE1 = "Book";
    public static final String TABLE2 = "Category";

    public static final int CODE_TABLE1_DIR = 0;
    public static final int CODE_TABLE1_ITEM = 1;
    public static final int CODE_TABLE2_DIR = 2;
    public static final int CODE_TABLE2_ITEM = 3;

    private static UriMatcher mMatcher;

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(AUTHORITY, TABLE1, CODE_TABLE1_DIR);
        mMatcher.addURI(AUTHORITY, TABLE1 + "/#", CODE_TABLE1_ITEM);
        mMatcher.addURI(AUTHORITY, TABLE2, CODE_TABLE2_DIR);
        mMatcher.addURI(AUTHORITY, TABLE2 + "/#", CODE_TABLE2_ITEM);
    }

    private MySQLiteHelper mSQLiteHandler;

    @Override
    public boolean onCreate() {
        mSQLiteHandler = new MySQLiteHelper(getContext(), "BookStore1.db", null, 2);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //uri = "content://com.codekun.androidfirstcode.provider/table1"
        //uri = "content://com.codekun.androidfirstcode.provider/table1/1"
        Cursor cursor = null;
        if (mSQLiteHandler == null){
            Toast.makeText(getContext(), "获取数据库对象失败", Toast.LENGTH_SHORT).show();
        }
        SQLiteDatabase db = mSQLiteHandler.getWritableDatabase();
        switch (mMatcher.match(uri)){
            case CODE_TABLE1_DIR:
                //查询 table1 表中的所有数据
                cursor = db.query(TABLE1, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CODE_TABLE1_ITEM:
                //查询 table1 表中的一条数据
                //Book/1 -> {"Book", "1"}
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query(TABLE1, projection, "id = ?", new String[]{bookId}, null, null, sortOrder);
                break;
            case CODE_TABLE2_DIR:
                //查询 table2 表中的所有数据
                cursor = db.query(TABLE2, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CODE_TABLE2_ITEM:
                //查询 table2 表中的一条数据
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query(TABLE2, projection, "id = ?", new String[]{categoryId}, null, null, sortOrder);
                break;
            case UriMatcher.NO_MATCH:
                //没有匹配的数据
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        String type = null;
        String vnd_dir = "vnd.android.cursor.dir/";
        String vnd_item = "vnd.android.cursor.item/";
        //vnd.com.codekun.androidfirstcode.provider.table1
        String vnd_table1_authority = "vnd." + AUTHORITY + "." + TABLE1;
        String vnd_table2_authority = "vnd." + AUTHORITY + "." + TABLE2;
        switch (mMatcher.match(uri)){
            case CODE_TABLE1_DIR:
                type = vnd_dir + vnd_table1_authority;
                break;
            case CODE_TABLE1_ITEM:
                type = vnd_item + vnd_table1_authority;
                break;
            case CODE_TABLE2_DIR:
                type = vnd_dir + vnd_table2_authority;
                break;
            case CODE_TABLE2_ITEM:
                type = vnd_item + vnd_table2_authority;
                break;
        }
        return type;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri newUri = null;
        SQLiteDatabase db = mSQLiteHandler.getWritableDatabase();
        switch (mMatcher.match(uri)){
            case CODE_TABLE1_DIR:
            case CODE_TABLE1_ITEM:
                long newBookId = db.insert(TABLE1, null, values);
                newUri = Uri.withAppendedPath(AUTHORITY_URI, "/" + TABLE1 + "/" + newBookId);
                break;
            case CODE_TABLE2_DIR:
            case CODE_TABLE2_ITEM:
                long newCategoryId = db.insert(TABLE2, null, values);
                newUri = Uri.withAppendedPath(AUTHORITY_URI, "/" + TABLE2 + "/" + newCategoryId);
                break;
            default:
                break;
        }
        return newUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mSQLiteHandler.getWritableDatabase();
        int id = 0;
        switch (mMatcher.match(uri)){
            case CODE_TABLE1_DIR:
            case CODE_TABLE1_ITEM:
                id = db.delete(TABLE1, selection, selectionArgs);
                break;
            case CODE_TABLE2_DIR:
            case CODE_TABLE2_ITEM:
                id = db.delete(TABLE2, selection, selectionArgs);
                break;
        }
        return id;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mSQLiteHandler.getWritableDatabase();
        int id = 0;
        switch (mMatcher.match(uri)){
            case CODE_TABLE1_DIR:
            case CODE_TABLE1_ITEM:
                id = db.update(TABLE1, values, selection, selectionArgs);
                break;
            case CODE_TABLE2_DIR:
            case CODE_TABLE2_ITEM:
                id = db.update(TABLE2, values, selection, selectionArgs);
                break;
        }
        return id;
    }
}
