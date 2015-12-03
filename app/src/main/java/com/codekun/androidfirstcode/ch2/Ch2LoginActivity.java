/**
 * ��Activity��Ҫ��ʾ��һ������ʱ����ô��������
 */
package com.codekun.androidfirstcode.ch2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

public class Ch2LoginActivity extends TitleBarActivity {

    private EditText username_editText;
    private EditText password_editText;
    private Button login_button;

    private TextView forgot_password_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("AndroidFirstCode", "Ch2LoginActivity#onCreate()");

        username_editText = (EditText)findViewById(R.id.ch2_username_editText);
        password_editText = (EditText)findViewById(R.id.ch2_password_editText);
        login_button = (Button)findViewById(R.id.ch2_login_button);
        forgot_password_textView = (TextView)findViewById(R.id.ch2_forgot_password_textView);

        //�����Activity�����գ��ٴ���ʾ��Activityʱ��ִ�и÷���
        //�����ڴ˴�������
        if(savedInstanceState != null){
            String username = savedInstanceState.getString("username");
            String password = savedInstanceState.getString("password");
            if (username != null){
                username_editText.setText(username);
            }
            if (password != null){
                password_editText.setText(password);
            }
        }

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username_editText.getText().toString().equals("") || password_editText.getText().toString().equals("")){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Ch2LoginActivity.this);
                    builder.setTitle("错误：");
                    builder.setMessage("用户名或密码 不能为空!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }else{
                    Toast.makeText(getApplicationContext(), "验证 用户名和密码", Toast.LENGTH_SHORT).show();

                }

            }
        });

        forgot_password_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.codekun.androidfirstcode.ACTION_START");
                i.addCategory("com.codekun.androidfirstcode.CATEGORY_THIRD_START");
                Bundle b = new Bundle();
                b.putString("title", "取回密码");
                i.putExtras(b);
                startActivity(i);

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AndroidFirstCode", "Ch2LoginActivity#onDestroy()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //活动被回收前保存数据
        String username = username_editText.getText().toString();
        String password = password_editText.getText().toString();
        if(!username.equals("")){
            outState.putString("username", username);
        }
        if(!password.equals("")){
            outState.putString("password", password);
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch2_login;
    }

}
