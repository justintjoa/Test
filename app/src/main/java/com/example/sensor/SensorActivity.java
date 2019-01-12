package com.example.sensor;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.util.Log;

import android.view.Menu;
import android.view.Display;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;

import android.widget.TextView;

import java.util.List;
import android.hardware.Sensor;
import android.hardware.SensorManager;


public class SensorActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;
        float mSensorX, mSensorY;


        switch (getResources().getConfiguration().orientation) {
            case Surface.ROTATION_0:
                mSensorX = event.values[0];
                mSensorY = event.values[1];
                System.out.println("Latitude: " + mSensorX + " Longitude: " + mSensorY);
                break;
            case Surface.ROTATION_90:
                mSensorX = -event.values[1];
                mSensorY = event.values[0];
                System.out.println("Latitude: " + mSensorX + " Longitude: " + mSensorY);
                break;
            case Surface.ROTATION_180:
                mSensorX = -event.values[0];
                mSensorY = -event.values[1];
                System.out.println("Latitude: " + mSensorX + " Longitude: " + mSensorY);
                break;
            case Surface.ROTATION_270:
                mSensorX = event.values[1];
                mSensorY = -event.values[0];
                System.out.println("Latitude: " + mSensorX + " Longitude: " + mSensorY);
        }
    }
}



