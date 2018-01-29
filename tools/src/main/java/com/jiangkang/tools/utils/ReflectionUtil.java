package com.jiangkang.tools.utils;

import android.support.annotation.NonNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by jiangkang on 2018/1/29.
 * description：反射工具类
 */

public class ReflectionUtil {

    private ReflectionUtil() {
    }

    /**
     * @param clazz 类，如Object.class
     * @return 包名
     */
    public static String getPackageName(Class<?> clazz) {
        return getPackageName(clazz.getName());
    }

    /**
     * @param classFullName 类名
     * @return 包名
     */
    public static String getPackageName(String classFullName) {
        int lastDot = classFullName.lastIndexOf('.');
        return (lastDot < 0) ? "" : classFullName.substring(0, lastDot);
    }


    /**
     * 确保传入的类被初始化，适用于有静态初始化的类
     *
     * @param classes 要初始化的类
     */
    public static void initialize(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            try {
                Class.forName(clazz.getName(), true, clazz.getClassLoader());
            } catch (ClassNotFoundException e) {
                throw new AssertionError(e);
            }
        }
    }


    public static <T> T newProxy(@NonNull Class<T> interfaceType, InvocationHandler handler) {
        Object object = Proxy.newProxyInstance(
                interfaceType.getClassLoader(), new Class[]{interfaceType}, handler
        );
        //转换对象
        return interfaceType.cast(object);
    }


}
