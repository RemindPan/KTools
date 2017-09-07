package com.jiangkang.tools;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by jiangkang on 2017/9/6.
 */

public final class King {

    private static Context sContext;

    public static void init(@NonNull final Context context){
        sContext = context;
    }

    public static Context getApplicationContext(){
        if (sContext == null) throw new NullPointerException("context can not be null");
        return sContext;
    }


}
