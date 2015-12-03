package com.codekun.androidfirstcode.ch10;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Ch10HttpActivity extends AppCompatActivity {

    private static final int SHOW_RESPONSE = 0;

    private Button connectionButton;
    private Button clientButton;
    private TextView textView;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == SHOW_RESPONSE){
                textView.setText((String)msg.obj);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch10_http);

        connectionButton = (Button)findViewById(R.id.ch10_http_connection_button);
        clientButton = (Button)findViewById(R.id.ch10_http_client_button);
        textView = (TextView)findViewById(R.id.ch10_http_textView);

        connectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHttpURLConnection();
            }
        });

        clientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHttpClient();
            }
        });

    }

    private void onHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection connection = null;
                    URL url = new URL("http://www.baidu.com");
                    try {
                        connection = (HttpURLConnection)url.openConnection();
                        connection.setConnectTimeout(8000);
                        connection.setRequestMethod("GET");
                        InputStream is = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                        StringBuilder sb = new StringBuilder();
                        String line = "";
                        while ((line = reader.readLine()) != null){
                            sb.append(line);
                        }
                        Message msg = new Message();
                        msg.what = SHOW_RESPONSE;
                        msg.obj = sb.toString();
                        handler.sendMessage(msg);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        if (connection != null){
                            connection.disconnect();
                        }
                    }

                } catch (MalformedURLException e) {
                    //链接格式错误
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void onHttpClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet("http://www.baidu.com");
                try {
                   HttpResponse response= client.execute(httpGet);
                    if (response.getStatusLine().getStatusCode() == 200){
                        Message msg = new Message();
                        msg.what = SHOW_RESPONSE;
                        msg.obj = EntityUtils.toString(response.getEntity());
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseXML(){

    }

}
