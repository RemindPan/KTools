package com.jiangkang.tools.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.jiangkang.tools.King;

/**
 * Created by jiangkang on 2017/9/8.
 */

public final class AppUtils {

    public static String getAppVersion() {
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
