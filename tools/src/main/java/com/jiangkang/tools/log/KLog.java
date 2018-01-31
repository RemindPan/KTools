package com.jiangkang.tools.log;

import android.util.Log;

import com.jiangkang.tools.King;
import com.jiangkang.tools.widget.KDialog;
import com.jiangkang.tools.widget.KNotification;

/**
 * Created by jiangkang on 2018/1/31.
 * description：
 */

public class KLog {

    public static boolean isDebug = true;

    public static  boolean enableLocalLog = false;

    public static void d(String tag, String message) {
        if (isDebug) {
            if (enableLocalLog){
                enqueueMessage(tag, message);
            }
            Log.d(tag, message);
        }
    }

    //存在文件即可，入队就插入数据，出队删除数据，仿照chuck
    private static void enqueueMessage(String tag, String message) {
        KNotification.createNotification(King.getApplicationContext(),-1,"Ktools",String.format("%s:%s",tag,message),null);
    }



}
