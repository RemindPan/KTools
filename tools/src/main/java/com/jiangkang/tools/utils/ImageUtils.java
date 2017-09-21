package com.jiangkang.tools.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by jiangkang on 2017/9/19.
 * 图片相关工具类
 */

public class ImageUtils {

    private static final String TAG = "ImageUtils";

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



    public static Bitmap scaleBitmap(Bitmap srcBitmap, int maxWidth, int maxHeight){

        int width = srcBitmap.getWidth();
        int height = srcBitmap.getHeight();

        Log.d(TAG, "scaleBitmap: \nwidth = " + width + "\nheight = " + height);

        int desiredWidth = Math.min(width,maxWidth);
        int desiredHeight = Math.min(height,maxHeight);

        float scaleWidth = ((float) desiredWidth / width);
        float scaleHeight = ((float) desiredHeight / height);

        float scaled = Math.min(scaleHeight,scaleWidth);

        Matrix matrix = new Matrix();
        matrix.postScale(scaled,scaled);
        return Bitmap.createBitmap(srcBitmap,0,0,width,height,matrix,true);
    }


}
