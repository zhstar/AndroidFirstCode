package com.codekun.androidfirstcode.ch6;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.core.TitleBarActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Ch6FileActivity extends TitleBarActivity implements View.OnClickListener{

    private EditText mEditText;
    private Button mSaveButton;
    private Button mReadButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEditText = (EditText)findViewById(R.id.ch6_file_editText);
        mSaveButton = (Button)findViewById(R.id.ch6_file_save_button);
        mReadButton = (Button)findViewById(R.id.ch6_file_read_button);

        mSaveButton.setOnClickListener(this);
        mReadButton.setOnClickListener(this);

        read();

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ch6_file;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ch6_file_save_button:
                save();
                break;
            case R.id.ch6_file_read_button:
                read();
                break;
        }
    }

    private void save()  {
        String text = mEditText.getText().toString();
        //为空
        if(TextUtils.isEmpty(text))return;
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(text);
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    private void read(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null){
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String text = content.toString();
        if(!TextUtils.isEmpty(text)){
            mEditText.setText(text);
            //光标移动最后
            mEditText.setSelection(content.toString().length());
        }

    }

}
