package com.codekun.androidfirstcode.ch11;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.codekun.androidfirstcode.R;

import java.util.List;

public class Ch11LocationActivity extends AppCompatActivity {

    protected TextView textView;

    //位置提供器，如：GPS，网络定位
    protected String locationProvider;

    protected LocationManager locationManager;

    protected LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch11_location);

        textView = (TextView)findViewById(R.id.ch11_location_textView);

        //获取位置服务管理器
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取已开启的位置提供器，如：GPS，网络定位...
        List<String> providers = locationManager.getProviders(true);
        //查找GPS或网络定位是否开启
        if (providers.contains(LocationManager.GPS_PROVIDER)){
            //GPS开启
            locationProvider = LocationManager.GPS_PROVIDER;
        }else if (providers.contains(LocationManager.NETWORK_PROVIDER)){
            //网络定位开启
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }else{
            Toast.makeText(this, "没有可用的定位服务", Toast.LENGTH_SHORT).show();
            return;
        }

        //根据位置提供器获取当前设备的位置对象
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location != null){
            //显示当前位置
            showLocation(location);
        }else{
            Toast.makeText(this, "获取不到当前位置", Toast.LENGTH_SHORT).show();
        }

        //监听位置更新
        locationManager.requestLocationUpdates(locationProvider, 5000, 1, locationListener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null){
            locationManager.removeUpdates(locationListener);
        }
    }

    protected void showLocation(Location location){
        String currentPosistion = "经度：" + location.getLongitude() + ", 纬度:" + location.getLatitude() ;
        Log.d("AndroidFirstCode", currentPosistion);
        textView.setText(currentPosistion);
    }

}
