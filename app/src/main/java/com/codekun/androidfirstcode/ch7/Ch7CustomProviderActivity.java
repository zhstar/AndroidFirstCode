package com.codekun.androidfirstcode.ch7;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

public class Ch7CustomProviderActivity extends TitleBarActivity implements View.OnClickListener {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch7_custom_provider;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.ch7_quary_button).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Uri uri = null;
        switch (v.getId()){
            case R.id.ch7_quary_button:
                uri = Uri.withAppendedPath(MyContentProvider.AUTHORITY_URI, "/" + MyContentProvider.TABLE1);//只演示操作表 Book
                Cursor cursor = null;
                try{
                    cursor = getContentResolver().query(uri, null, null, null, null);
                    if (cursor.moveToFirst()){
                        do {
                            String name = cursor.getString(cursor.getColumnIndex("name"));
                            String author = cursor.getString(cursor.getColumnIndex("author"));
                            Double price = cursor.getDouble(cursor.getColumnIndex("price"));
                            int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                            Log.d("AndroidFirstCode", "name:" + name + ", author:" + author + ", price:" + price + ", pages:" + pages);
                        }while (cursor.moveToNext());
                    }
                }catch (Exception e){
                    Log.d("AndroidFirstCode", e.getMessage());
                }finally {
                    if (cursor != null){
                        cursor.close();
                    }
                }
                break;
        }
    }

}
