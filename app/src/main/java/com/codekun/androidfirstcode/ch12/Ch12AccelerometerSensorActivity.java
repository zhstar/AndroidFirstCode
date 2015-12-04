package com.codekun.androidfirstcode.ch12;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.codekun.androidfirstcode.R;

public class Ch12AccelerometerSensorActivity extends AppCompatActivity {

    private TextView mTextView;
    private SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = Math.abs(event.values[0]);
            float y = Math.abs(event.values[1]);
            float z = Math.abs(event.values[2]);
            if (x > 15 || y > 15 || z > 15){
                Toast.makeText(Ch12AccelerometerSensorActivity.this, "摇一摇", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch12_accelerometer_sensor);

        mTextView = (TextView)findViewById(R.id.ch12_accelerometer_sensor_textView);

        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(mSensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSensorManager != null)mSensorManager.unregisterListener(mSensorEventListener);
    }
}
