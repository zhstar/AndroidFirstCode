package com.codekun.androidfirstcode.ch7;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

import java.util.ArrayList;
import java.util.List;

public class Ch7ReadContactsActivity extends TitleBarActivity {

    private List<String> contacts = new ArrayList<String>();

    private ArrayAdapter<String> adapter;

    private ListView contactsListView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch7_read_contacts;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contactsListView = (ListView)findViewById(R.id.ch7_contacts_listView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts);
        contactsListView.setAdapter(adapter);


        readContacts();
    }

    private void readContacts(){
        //以下操作需要权限：<uses-permission android:name="android.permission.READ_CONTACTS" />
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, null, null,null);
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String log = "name:" + name + ", tel:" + number;
                contacts.add(log);
            }
            adapter.notifyDataSetChanged();
        }catch (Exception e){

        }finally {
            if (cursor != null){
                cursor.close();
            }
        }

    }

}
