package com.jiangkang.tools.utils;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.jiangkang.tools.King;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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



    public static String getCurrentActivity(){
        return King.getTopActivityWeakRef().get().getComponentName().getClassName();
    }


    public static List<String> getActivityListInStack(){
        List<Activity> activities = King.getsActivityList();
        List<String> list = new ArrayList<>();
        Iterator iterator = activities.iterator();
        while (iterator.hasNext()){
            Activity activity = (Activity) iterator.next();
            list.add(activity.getComponentName().getClassName());
        }
        return list;
    }






}
