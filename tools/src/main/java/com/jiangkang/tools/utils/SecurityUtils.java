package com.jiangkang.tools.utils;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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


    public static String bmp2base64(Bitmap bitmap, Bitmap.CompressFormat compressFormat,int quality){
        if (bitmap == null){
            return "";
        }else {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(compressFormat,quality,outputStream);
            try {
                outputStream.flush();
                outputStream.close();
                byte[] bytes = outputStream.toByteArray();

                return Base64.encodeToString(bytes,Base64.DEFAULT);

            } catch (IOException e) {
                return "";
            }
        }
    }




}
