package com.jiangkang.tools.device;

import android.content.Context;
import android.net.wifi.WifiManager;

import com.jiangkang.tools.King;

/**
 * Created by jiangkang on 2017/9/8.
 */

public class DeviceUtils {

    private static final String TAG = DeviceUtils.class.getSimpleName();
    
    private static WifiManager sWifiManager;
    
    static {
        sWifiManager = (WifiManager) King.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }






}
