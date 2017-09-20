package com.jiangkang.tools.utils;

import android.graphics.Bitmap;

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


}
