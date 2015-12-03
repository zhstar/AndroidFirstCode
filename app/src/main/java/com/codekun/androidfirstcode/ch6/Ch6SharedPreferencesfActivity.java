/*

要想使用SharedPreferences来存储数据，首先需要获取到SharedPreferences对象。
Android中主要提供了三种方法用于得到SharedPreferences对象。
1. Context类中的getSharedPreferences()方法
此方法接收两个参数，第一个参数用于指定SharedPreferences文件的名称，
如果指定的文件不存在则会创建一个，SharedPreferences文件都是存放在/data/data/<package name>/shared_prefs/目录下的。
第二个参数用于指定操作模式，主要有两种模式可以选择，MODE_PRIVATE和MODE_MULTI_PROCESS。
MODE_PRIVATE仍然是默认的操作模式，和直接传入0效果是相同的，表示只有当前的应用程序才可以对这个SharedPreferences文件进行读写。
MODE_MULTI_PROCESS则一般是用于会有多个进程中对同一个SharedPreferences文件进行读写的情况。
类似地，MODE_WORLD_READABLE和MODE_WORLD_WRITEABLE这两种模式已在Android 4.2版本中被废弃。
2. Activity类中的getPreferences()方法
这个方法和Context中的getSharedPreferences()方法很相似，不过它只接收一个操作模式参数，
因为使用这个方法时会自动将当前活动的类名作为SharedPreferences的文件名。
3. PreferenceManager类中的getDefaultSharedPreferences()方法
这是一个静态方法，它接收一个Context参数，并自动使用当前应用程序的包名作为前缀来命名SharedPreferences文件。
得到了SharedPreferences对象之后，就可以开始向SharedPreferences文件中存储数据了，主要可以分为三步实现。
1. 调用SharedPreferences对象的edit()方法来获取一个SharedPreferences.Editor对象。
2. 向SharedPreferences.Editor对象中添加数据，比如添加一个布尔型数据就使用putBoolean方法，
添加一个字符串则使用putString()方法，以此类推。
3. 调用commit()方法将添加的数据提交，从而完成数据存储操作。

摘自《第一行代码》240页
 */
package com.codekun.androidfirstcode.ch6;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

public class Ch6SharedPreferencesfActivity extends TitleBarActivity implements View.OnClickListener {

    private EditText mEditText;
    private Button mSaveButton;
    private Button mReadButton;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch6_shared_preferencesf;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mEditText = (EditText)findViewById(R.id.ch6_sp_editText);
        mSaveButton = (Button)findViewById(R.id.ch6_sp_save_button);
        mReadButton = (Button)findViewById(R.id.ch6_sp_read_button);

        mSaveButton.setOnClickListener(this);
        mReadButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ch6_sp_save_button:
                save();
                break;
            case R.id.ch6_sp_read_button:
                read();
                break;
        }
    }

    private void save(){
        //使用第一种方法获取SharedPreferences对象
        SharedPreferences sp = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String text = mEditText.getText().toString();
        if (TextUtils.isEmpty(text))return;
        editor.putString("key", text);
        editor.commit();
    }

    private void read(){
        //使用第一种方法获取SharedPreferences对象
        SharedPreferences sp = getSharedPreferences("data", Context.MODE_PRIVATE);
        String text = sp.getString("key", "");
        mEditText.setText(text);
        mEditText.setSelection(text.length());
    }

}
