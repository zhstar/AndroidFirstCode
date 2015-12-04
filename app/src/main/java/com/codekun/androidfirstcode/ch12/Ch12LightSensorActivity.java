package com.codekun.androidfirstcode.ch12;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;

public class Ch12LightSensorActivity extends AppCompatActivity {

    private TextView mTextView;

    private SensorManager mSensorManager;

    private SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // values数组中第一个下标的值就是当前的光照强度
            mTextView.setText("Light:" + event.values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //mTextView.setText("Light:" + accuracy);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch12_light_sensor);

        mTextView = (TextView)findViewById(R.id.ch12_light_sensor_textView);

        //传感器管理器
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //光照传感器
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //注册光照传感器监听
        mSensorManager.registerListener(mSensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(mSensorEventListener);
    }
}
