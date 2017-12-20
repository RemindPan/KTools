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


    public static Bitmap convert2Gray(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int[] pixs = new int[width * height];

        bitmap.getPixels(pixs, 0, width, 0, 0, width, height);

        int alpha = 0xFF << 24;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int color = pixs[width * i + j];

                int red = (color & 0x00FF0000) >> 16;

                int green = (color & 0x0000FF00) >> 8;

                int blue = (color & 0x000000FF);

                color = (red + green + blue) / 3;

                color = alpha | (color << 16) | (color << 8)| color;

                pixs[width * i + j] = color;

            }
        }

        Bitmap result = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
        result.setPixels(pixs,0,width,0,0,width,height);
        return result;
    }


}
