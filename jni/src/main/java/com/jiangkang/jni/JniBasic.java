package com.jiangkang.jni;

public class JniBasic {

    static {
        System.loadLibrary("basic");
    }

    private native void callNative();



}
