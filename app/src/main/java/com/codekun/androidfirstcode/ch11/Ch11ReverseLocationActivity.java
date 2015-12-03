package com.codekun.androidfirstcode.ch11;

import android.content.Context;
import android.content.Entity;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.utils.HttpUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 本实例在上一节实例基础上扩展
 */
public class Ch11ReverseLocationActivity extends AppCompatActivity {

    private static final int SHOW_LOCATION = 0;

    private TextView textView;
    private Button httpURLConnectionButton;
    private Button volleyButton;
    private Button volleyPostButton;

    private Context mContext;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == SHOW_LOCATION){
                textView.setText((String)msg.obj);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch11_reverse_location);

        mContext = this;

        textView = (TextView)findViewById(R.id.ch11_reverse_location_textView);
        httpURLConnectionButton = (Button)findViewById(R.id.ch11_reverse_location_HttpURLConnection_button);
        volleyButton = (Button)findViewById(R.id.ch11_reverse_location_volley_button);
        volleyPostButton = (Button)findViewById(R.id.ch11_reverse_location_volley_post_button);

        //注意网络被墙的问题
        final String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.96145&sensor=false";

        httpURLConnectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtil.sendPost(url, null, new HttpUtil.HttpCallbackListener() {
                    @Override
                    public void onFinish(String data) {
                        showData(data);
                        Toast.makeText(mContext, "HttpURLConnection获取数据成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        });

        volleyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(mContext);
                StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        showData(s);
                        Toast.makeText(mContext, "Volley GET获取数据成功", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
                queue.add(request);
            }
        });

        volleyPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(mContext);
                StringRequest request1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            String address = jsonArray.getJSONObject(0).getString("formatted_address");
                            showData(address);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //showData(s);
                        Toast.makeText(mContext, "Volley POST获取数据成功", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // Log.d("AndroidFirstCode", volleyError.getMessage());
                    }
                })/*{
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("latlng", "40.714224,-73.96145");
                map.put("sensor", "false");
                return map;
            }
        }*/;
                queue.add(request1);

            }
        });

    }

    private void showData(String data){
        Message msg = new Message();
        msg.what = SHOW_LOCATION;
        msg.obj = data;
        handler.sendMessage(msg);
    }

}
