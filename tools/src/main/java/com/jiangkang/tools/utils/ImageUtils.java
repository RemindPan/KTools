package com.jiangkang.tools.utils;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by jiangkang on 2017/9/19.
 * 图片相关工具类
 */

public class ImageUtils {

    public static byte[] bitmap2Bytes(Bitmap bitmap, int quality, Bitmap.CompressFormat format){
        if (bitmap == null){
            return null;
        }else {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(format,quality,outputStream);
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return outputStream.toByteArray();
        }
    }


    public static String bitmap2Base64(Bitmap bitmap, int quality, Bitmap.CompressFormat format){
        byte[] bytes = bitmap2Bytes(bitmap,quality,format);
        return Base64.encodeToString(bytes,Base64.DEFAULT)
                .replace("\n","")
                .replace("\r","")
                .replace("\t","");
    }



}
