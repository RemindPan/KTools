package com.jiangkang.ktools;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HybridMap {

    public static final Map<String, Class<?>> clzByTagMaps = new HashMap<>();

    public static final Map<Class<?>, List<Method>> methodsByClzMaps = new HashMap<>();


    static {
        try {
            clzByTagMaps.put("app", Class.forName("ccc"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static Class<?> getHybridClass(String tag) {
        if (clzByTagMaps.containsKey(tag)) {
            return clzByTagMaps.get(tag);
        }
        return null;
    }



}
