package com.jiangkang.tools.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by jiangkang on 2017/9/11.
 */

public class SecurityUtils {


    public static String encodeByBase64(String string){
        String result = "";
        try {
            result = Base64.encodeToString(string.getBytes("utf-8"),Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {

        }
        return result;
    }



}
