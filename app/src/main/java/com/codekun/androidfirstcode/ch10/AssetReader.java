package com.codekun.androidfirstcode.ch10;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by kun on 2015/12/2.
 */
public class AssetReader {

    public static StringBuilder read(Context context, String fileUrl) {
        StringBuilder result = new StringBuilder();
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = context.getApplicationContext().getAssets().open(fileUrl);
            br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while((line = br.readLine())!= null){
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
