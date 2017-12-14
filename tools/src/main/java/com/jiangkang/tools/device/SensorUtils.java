package com.jiangkang.tools.device;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.jiangkang.tools.King;

/**
 * Created by jiangkang on 2017/12/4.
 * description：
 */

public class SensorUtils {

    /*
    * 判断设备是否支持计步器
    * */
    public static boolean isSupportStepSensor(){
        SensorManager sensorManager = (SensorManager) King.getApplicationContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor counterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        Sensor detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        return counterSensor != null || detectorSensor != null;
    }



    


}
