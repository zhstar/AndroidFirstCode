package com.codekun.weather.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codekun.weather.ChooseAreaActivity;
import com.codekun.weather.R;
import com.codekun.weather.app.UpdateWeatherService;
import com.codekun.weather.database.DataLoader;
import com.codekun.weather.database.Utility;
import com.codekun.weather.models.WeatherInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherInfoFragment extends Fragment {


    private TextView publishText;
    private TextView currentDateText;
    private TextView weatherDespText;
    private TextView temp1Text;
    private TextView temp2Text;
    private TextView cityText;

    public WeatherInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = new Intent(getActivity(), UpdateWeatherService.class);
        getActivity().startService(i);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather_info, container, false);
        publishText = (TextView)v.findViewById(R.id.publish_text);
        currentDateText = (TextView)v.findViewById(R.id.current_date);
        weatherDespText = (TextView)v.findViewById(R.id.weather_desp);
        temp1Text = (TextView)v.findViewById(R.id.temp1);
        temp2Text = (TextView)v.findViewById(R.id.temp2);
        cityText = (TextView)v.findViewById(R.id.country_text);
        String jsonData = Utility.readWeatherInfoData(getActivity());
        if (jsonData != null){
            update(jsonData);
        }else{
            //选择城市
            Intent intent = new Intent(getActivity(), ChooseAreaActivity.class);
            // startActivity(intent);
            getActivity().startActivityForResult(intent, 1);
        }
        return v;
    }

    public void update(String jsonData){
        WeatherInfo info = WeatherInfo.createInstance(jsonData);
        update(info);
        Utility.saveWeatherInfoData(getActivity(), jsonData);
    }

    public void update(String countryName, String countryCode){
        DataLoader.queryWeatherInfo(getActivity(), countryCode, new DataLoader.ResultListener(){
            @Override
            public void onComplete(Object obj) {
                super.onComplete(obj);
                String jsonData = (String)obj;
                update(jsonData);
            }
        });
    }

    public void update(WeatherInfo info){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);
        publishText.setText("发布时间：" + info.getPtime());
        currentDateText.setText(sdf.format(new Date()));
        weatherDespText.setText(info.getWeather());
        temp1Text.setText(info.getTemp1());
        temp2Text.setText(info.getTemp2());
        cityText.setText(info.getCity());
    }

}
