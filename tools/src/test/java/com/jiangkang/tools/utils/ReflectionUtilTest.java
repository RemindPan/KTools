package com.jiangkang.tools.utils;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by jiangkang on 2018/1/29.
 * description：ReflectionUtil的测试类
 */
public class ReflectionUtilTest {

    @Test
    public void testGetPackageName() throws Exception {

        //class
        assertEquals("java.lang", ReflectionUtil.getPackageName(Object.class));
        assertEquals("java.io", ReflectionUtil.getPackageName(File.class));


        //className
        assertEquals("java.lang",ReflectionUtil.getPackageName(Object.class.getName()));
        assertEquals("java.lang",ReflectionUtil.getPackageName("java.lang.Object"));


        //no package
        assertEquals("",ReflectionUtil.getPackageName("No Package"));


    }


    @Test
    public void initialize() throws Exception {

    }

    @Test
    public void newProxy() throws Exception {

    }


}