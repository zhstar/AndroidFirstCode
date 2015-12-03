package com.codekun.androidfirstcode.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kun on 2015/12/2.
 */
public class HttpUtil {

    /**
     * 提供回调操作
     */
    public interface HttpCallbackListener{
        /**
         * 请求成功时回调
         * @param data 服务器响应的数据
         */
        void onFinish(String data);

        /**
         * 请求出现异常时回调
         * @param e
         */
        void onError(Exception e);
    }

    /**
     * 向服务器发送 get 请求
     * @param address
     * @param listener
     */
    public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    StringBuilder data = new StringBuilder();
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null){
                        data.append(line);
                    }
                    if (listener != null){
                        listener.onFinish(data.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (listener != null){
                        listener.onError(e);
                    }
                }finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

}
