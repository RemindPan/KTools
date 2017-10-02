package com.jiangkang.tools.utils;

import android.util.Log;

/**
 * Created by jiangkang on 2017/9/28.
 */

public class LogUtils {


    private static final String LOG_DEFAULT = "LogUtils";

//    public static void d(String format, String... msg){
//        d(LOG_DEFAULT,format,msg);
//    }


    public static void d(String tag, String format, String... msg){
        Log.d(tag,String.format(format,msg));
    }

}
