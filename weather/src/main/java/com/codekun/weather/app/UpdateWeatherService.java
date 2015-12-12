package com.codekun.weather.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.codekun.weather.database.DataLoader;
import com.codekun.weather.database.Utility;
import com.codekun.weather.models.WeatherInfo;

public class UpdateWeatherService extends Service {
    public UpdateWeatherService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateWeather();
            }
        }).start();
        //定时启动广播
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        long m = SystemClock.elapsedRealtime() + 1000 * 1000 * 60 * 8;//8小时
        Intent i = new Intent(this, UpdateWeatherReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, m, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateWeather(){
        final Context context = this;
        String jsonData = Utility.readWeatherInfoData(context);
        if (jsonData != null){
            String countryCode = WeatherInfo.createInstance(jsonData).getCityId();
            DataLoader.queryWeatherInfo(context, countryCode, new DataLoader.ResultListener(){
                @Override
                public void onComplete(Object obj) {
                    super.onComplete(obj);
                    Utility.saveWeatherInfoData(context, (String)obj);
                }
            });
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
