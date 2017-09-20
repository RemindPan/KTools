package com.jiangkang.tools.device;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.jiangkang.tools.King;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by jiangkang on 2017/9/8.
 */

public class DeviceUtils {

    private static final String TAG = DeviceUtils.class.getSimpleName();
    private static final String NETWORK_TYPE_WIFI = "wifi";
    private static final String NETWORK_TYPE_MOBILE = "mobile";
    private static final String NETWORK_TYPE_OTHER = "other";


    private static final String MAC_ADDRESS_DEFAULT = "02:00:00:00:00:00";

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


    public static String getMacAddress(){
        WifiInfo wifiInfo = sWifiManager.getConnectionInfo();
        String macAddress = wifiInfo.getMacAddress();
        if (!MAC_ADDRESS_DEFAULT.equals(macAddress)){
            return macAddress;
        }

        macAddress = getMacAddressByNetworkInterface();
        if (!MAC_ADDRESS_DEFAULT.equals(macAddress)){
            return macAddress;
        }
        return MAC_ADDRESS_DEFAULT;
    }


    /**
     * 获取设备MAC地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     *
     * @return MAC地址
     */
    private static String getMacAddressByNetworkInterface() {
        try {
            List<NetworkInterface> nis = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface ni : nis) {
                if (!ni.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = ni.getHardwareAddress();
                if (macBytes != null && macBytes.length > 0) {
                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02x:", b));
                    }
                    return res1.deleteCharAt(res1.length() - 1).toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }


    /**
     * 获取设备MAC地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     *
     * @return MAC地址
     */
    @SuppressLint("HardwareIds")
    private static String getMacAddressByWifiInfo() {
        try {
            @SuppressLint("WifiManagerLeak")
            WifiManager wifi = (WifiManager) King.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (wifi != null) {
                WifiInfo info = wifi.getConnectionInfo();
                if (info != null) return info.getMacAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }







}
