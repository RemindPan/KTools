package com.jiangkang.tools.device;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.jiangkang.tools.King;

/**
 * Created by jiangkang on 2017/9/8.
 */

public class DeviceUtils {

    private static final String TAG = DeviceUtils.class.getSimpleName();
    private static final String NETWORK_TYPE_WIFI = "wifi";
    private static final String NETWORK_TYPE_MOBILE = "mobile";
    private static final String NETWORK_TYPE_OTHER = "other";

    private static WifiManager sWifiManager;

    private static ConnectivityManager sConnectivityManager;

    public static final String NETWORK_TYPE_OFFLINE = "offline";
    
    static {
        sWifiManager = (WifiManager) King.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        sConnectivityManager = (ConnectivityManager) King.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static String getNetWorkType(){
        NetworkInfo networkInfo = sConnectivityManager.getActiveNetworkInfo();
        NetworkInfo mobileInfo = sConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiInfo = sConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo ==null || !networkInfo.isAvailable()){
            return NETWORK_TYPE_OFFLINE;
        }else if (wifiInfo != null && wifiInfo.isAvailable()){
            return NETWORK_TYPE_WIFI;
        }else if (mobileInfo != null && mobileInfo.isAvailable()){
            return NETWORK_TYPE_MOBILE;
        }else {
            return NETWORK_TYPE_OTHER;
        }
    }


    /*
    * 获取Wifi信号等级，分为0-4五个等级
    * */
    public static int getSignalLevel(){
        WifiInfo wifiInfo = sWifiManager.getConnectionInfo();
        return WifiManager.calculateSignalLevel(wifiInfo.getRssi(),5);
    }



}
