package com.codekun.androidfirstcode.ch12;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.codekun.androidfirstcode.R;
import com.codekun.androidfirstcode.utils.CKLog;

public class Ch12CompassActivity extends AppCompatActivity {

    private float[] accelerometerValues = new float[3];
    private float[] magneticValues = new float[3];
    private float[] r = new float[9];
    private float[] values = new float[3];

    private ImageView compassBgImageView;
    private ImageView compassPointerImageView;

    private float lastRotateDegree = 0;

    //private TextView mTextView;
    private SensorManager mSensorManager;
    private SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                accelerometerValues = event.values.clone();
            }else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
                magneticValues = event.values.clone();
            }
            SensorManager.getRotationMatrix(r, null, accelerometerValues, magneticValues);
            SensorManager.getOrientation(r, values);
            //Log.d("AndroidFirstCode", "Value:" + Math.toDegrees(values[0]));
            //mTextView.setText("Value:" + Math.toDegrees(values[0]));
            float rotateDegree = -(float)Math.toDegrees(values[0]);
            if (Math.abs(rotateDegree - lastRotateDegree) > 1){
                RotateAnimation animation = new RotateAnimation(lastRotateDegree, rotateDegree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setFillAfter(true);
                compassBgImageView.startAnimation(animation);
                lastRotateDegree = rotateDegree;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch12_compass);

       // mTextView = (TextView)findViewById(R.id.ch12_compass_textView);

        compassBgImageView = (ImageView)findViewById(R.id.ch12_compass_bg_imageView);

        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor magneticSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        mSensorManager.registerListener(mSensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(mSensorEventListener, magneticSensor, SensorManager.SENSOR_DELAY_GAME);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSensorManager != null){
            mSensorManager.unregisterListener(mSensorEventListener);
        }
    }
}
