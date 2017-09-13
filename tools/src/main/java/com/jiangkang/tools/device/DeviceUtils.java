package com.jiangkang.tools.device;

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


    /*
    *
    * 获取信号强度，暂时还没有靠谱的方案
    * */
    public static int getMobileSignalStrength(){
        int dbm = -1;
        TelephonyManager tm = (TelephonyManager)King.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        List<CellInfo> cellInfoList;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            cellInfoList = tm.getAllCellInfo();
            if (null != cellInfoList)
            {
                for (CellInfo cellInfo : cellInfoList)
                {
                    if (cellInfo instanceof CellInfoGsm)
                    {
                        CellSignalStrengthGsm cellSignalStrengthGsm = ((CellInfoGsm)cellInfo).getCellSignalStrength();
                        dbm = cellSignalStrengthGsm.getDbm();
                    }
                    else if (cellInfo instanceof CellInfoCdma)
                    {
                        CellSignalStrengthCdma cellSignalStrengthCdma =
                                ((CellInfoCdma)cellInfo).getCellSignalStrength();
                        dbm = cellSignalStrengthCdma.getDbm();
                    }
                    else if (cellInfo instanceof CellInfoWcdma)
                    {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
                        {
                            CellSignalStrengthWcdma cellSignalStrengthWcdma =
                                    ((CellInfoWcdma)cellInfo).getCellSignalStrength();
                            dbm = cellSignalStrengthWcdma.getDbm();
                        }
                    }
                    else if (cellInfo instanceof CellInfoLte)
                    {
                        CellSignalStrengthLte cellSignalStrengthLte = ((CellInfoLte)cellInfo).getCellSignalStrength();
                        dbm = cellSignalStrengthLte.getDbm();
                    }
                }
            }
        }
        return dbm;
    }




}
