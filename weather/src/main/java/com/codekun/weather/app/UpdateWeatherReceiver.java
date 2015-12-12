package com.codekun.weather.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class UpdateWeatherReceiver extends BroadcastReceiver {
    public UpdateWeatherReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, UpdateWeatherService.class);
        context.startService(i);

    }
}
