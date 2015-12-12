package com.codekun.common.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kun on 2015/12/2.
 */
public class HttpUtil {

    /**
     * 请求方式
     */
    public static final String REQUEST_METHOD_GET = "GET";
    public static final String REQUEST_METHOD_POST = "POST";

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
                BufferedReader reader =null;
                try {
                    StringBuilder data = new StringBuilder();
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
                    if (reader != null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();
    }

    public static void sendPost(final String address, final String params, final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                PrintWriter writer = null;
                BufferedReader reader =null;
                try {
                    StringBuilder data = new StringBuilder();
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod(REQUEST_METHOD_POST);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setInstanceFollowRedirects(false);
                    connection.connect();
                    writer = new PrintWriter(connection.getOutputStream());
                    writer.print(params);
                    writer.flush();
                    /*connection.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
                    connection.setRequestProperty("Content-Language", "en-US");
                    connection.connect();
                    OutputStream out = connection.getOutputStream();
                    out.write(params.getBytes());
                    out.flush();
                    out.close();*/
                    //connection.addRequestProperty("Accept-Language", "zh-CN");
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null){
                        data.append(line);
                    }
                    Log.d("AndroidFirstCode", "POST:" + data.toString());
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
                    if (writer != null){
                        writer.close();
                    }
                    if (reader != null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();
    }


}
