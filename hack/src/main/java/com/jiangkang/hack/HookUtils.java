package com.jiangkang.hack;

import android.app.Instrumentation;
import android.util.Log;
import android.view.View;

import com.jiangkang.hack.activity.LogInstrumentation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.security.auth.login.LoginException;

/**
 * Created by jiangkang on 2018/3/14.
 * description：
 */

public class HookUtils {


    private static final String TAG = "Hook";

    /*
        * 目前暂时只支持通过Context.startActivity()的Hook
        * */
    public static void attachBaseContext() throws Exception {

        //获取ActivityThread类
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");

        Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
        currentActivityThreadMethod.setAccessible(true);

        Object currentActivityThread = currentActivityThreadMethod.invoke(null);


        Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
        mInstrumentationField.setAccessible(true);
        Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);

        Instrumentation logInstrumentation = new LogInstrumentation(mInstrumentation);

        //替换
        mInstrumentationField.set(currentActivityThread, logInstrumentation);


    }


    public static void hookViewOnclickListener() {

        View.OnClickListener listener = (View.OnClickListener) Proxy.newProxyInstance(
                View.OnClickListener.class.getClassLoader(),
                new Class[]{View.OnClickListener.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        Log.d(TAG, "点击按钮之前");

                        Object result = method.invoke(proxy, args);

                        Log.d(TAG, "点击按钮之后");

                        return result;
                    }
                }
        );

    }


}
