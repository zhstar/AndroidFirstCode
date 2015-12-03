package com.codekun.androidfirstcode.ch10;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class Ch10JSONActivity extends AppCompatActivity {

    private Button jsonObjectButton;
    private Button gsonButton;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch10_json);
        mContext = this;
        jsonObjectButton = (Button)findViewById(R.id.ch10_json_JSONObject_button);
        gsonButton = (Button)findViewById(R.id.ch10_json_GSON_button);

        jsonObjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String data = AssetReader.read(mContext, "get_data.json").toString();
                        JSONArray array = null;
                        try {
                            array = new JSONArray(data);
                            for (int i = 0; i<array.length(); ++i){
                                JSONObject obj = array.getJSONObject(i);
                                App app = new App();
                                app.setId(obj.getInt("id"));
                                app.setName(obj.getString("name"));
                                app.setVersion(obj.getString("version"));
                                Log.d("AndroidFirstCode", "JSONObject:" + app.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        gsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /*
                        添加GSON库
                        1、将jar文件拷到项目libs文件夹下
                        2、在Android Studio Project列表找到jar文件，鼠标右键，选择 Add as library
                        3、检查 app/build.gradle 是否已经加入 jar
                         */
                        String data = AssetReader.read(mContext, "get_data.json").toString();
                        Log.d("AndroidFirstCode", "Data:" + data);
                        Gson gson = new Gson();
                        //App2成员必须为String类型
                        List<App2> appList = gson.fromJson(data, new TypeToken<List<App2>>(){}.getType());
                        for (App2 app : appList){
                            Log.d("AndroidFirstCode", "GSON:" + app.toString());
                        }
                    }
                }).start();
            }
        });
    }
}
