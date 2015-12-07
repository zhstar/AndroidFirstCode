package com.codekun.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codekun.weather.database.DatabaseManager;
import com.codekun.weather.models.Province;

public class WeatherActivity extends AppCompatActivity {

    private DatabaseManager mDatabaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        /*mDatabaseManager = DatabaseManager.getInstance(this);

        Province p = new Province();
        p.setName("广东");
        p.setCode("10");
        mDatabaseManager.saveProvince(p);*/

        DatabaseManager.test(this);


    }
}
