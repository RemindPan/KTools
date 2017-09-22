package com.jiangkang.tools.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.jiangkang.tools.King;

/**
 * Created by jiangkang on 2017/9/8.
 *
 * 与App相关的工具类
 */

public final class AppUtils {

    private static final String TAG = AppUtils.class.getSimpleName();

    public static String getAppVersionName() {
        try {
            PackageInfo packageInfo = getPackageInfo();
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "unknown";
    }


    private static PackageInfo getPackageInfo() throws PackageManager.NameNotFoundException {
        PackageManager pm = King.getApplicationContext().getPackageManager();
        return pm.getPackageInfo(King.getApplicationContext().getPackageName(),PackageManager.GET_CONFIGURATIONS);
    }







}
