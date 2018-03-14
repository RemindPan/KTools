package com.jiangkang.hack.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by jiangkang on 2018/3/14.
 * description：
 */

public class LogInstrumentation extends Instrumentation {

    private static final String TAG = "Hook";

    Instrumentation mBaseInstrumentation;

    public LogInstrumentation(Instrumentation instrumentation) {
        this.mBaseInstrumentation = instrumentation;
    }


    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {


        Log.d(TAG,
                String.format("\nwho = %s \n contextThread = %s, \n token = %s, \n target = %s,\n intent = %s,\nrequestCode = %s,\n options = %s",
                        who, contextThread, token, target, intent, requestCode, options));


        try {
            Method exeStartActivity = Instrumentation.class.getDeclaredMethod(
                    "execStartActivity",
                    Context.class,
                    IBinder.class,
                    IBinder.class,
                    Activity.class,
                    Intent.class,
                    int.class,
                    Bundle.class
            );
            exeStartActivity.setAccessible(true);

            return (ActivityResult) exeStartActivity.invoke(mBaseInstrumentation,
                    who,
                    contextThread,
                    token,
                    target,
                    intent,
                    requestCode,
                    options
                    );
        } catch (Exception e){
            throw new RuntimeException("you have to solve the bug");
        }
    }

}
