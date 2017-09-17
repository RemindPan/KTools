package com.jiangkang.tools.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.nio.charset.Charset;
import java.util.Hashtable;

/**
 * Created by jiangkang on 2017/9/17.
 * 二维码工具类
 */

public class QRCodeUtils {


    /**
     * @param content 二维码内容
     * @param width 宽度px
     * @param height 高度px
     * @param characterSet 字符编码， 支持格式:{@link CharacterSetECI }，传null时,zxing源码默认使用 "ISO-8859-1"
     * @param errorCorrection 容错级别，(支持级别:{@link ErrorCorrectionLevel })。传null时,zxing源码默认使用 "L"
     * @param margin 空白边距 (可修改,要求:整型且>=0), 传null时,zxing源码默认使用"4"
     * @param colorBlack 黑色色块的自定义颜色值
     * @param colorWhite 白色色块的自定义颜色值
     * @return 二维码Bitmap
     */
    public static Bitmap createQRCodeBitmap(@NonNull String content,
                                            @IntRange(from = 0) int width,
                                            @IntRange(from = 0) int height,
                                            @Nullable String characterSet,
                                            @Nullable String errorCorrection,
                                            @Nullable String margin,
                                            @ColorInt int colorBlack,
                                            @ColorInt int colorWhite
                                            ){
        Hashtable<EncodeHintType,String> hints = new Hashtable<>();

        if (!TextUtils.isEmpty(characterSet)){
            hints.put(EncodeHintType.CHARACTER_SET,characterSet);
        }

        if (!TextUtils.isEmpty(errorCorrection)){
            hints.put(EncodeHintType.ERROR_CORRECTION,errorCorrection);
        }

        if (!TextUtils.isEmpty(margin)){
            hints.put(EncodeHintType.MARGIN,margin);
        }

        try {
            BitMatrix bitMatrix = new QRCodeWriter()
                    .encode(content, BarcodeFormat.QR_CODE,width,height,hints);

            /** 3.创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值 */
            int[] pixels = new int[width * height];
            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                    if(bitMatrix.get(x, y)){
                        pixels[y * width + x] = colorBlack; // 黑色色块像素设置
                    } else {
                        pixels[y * width + x] = colorWhite; // 白色色块像素设置
                    }
                }
            }


            Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels,0,width,0,0,width,height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return null;
    }



    public static Bitmap createQRCodeBitmap(@NonNull String content,
                                            @IntRange(from = 0) int width,
                                            @IntRange(from = 0) int height){
     return createQRCodeBitmap(
             content,
             width,
             height,
             CharacterSetECI.UTF8.name(),
             ErrorCorrectionLevel.H.name(),
             "2",
             Color.BLACK,
             Color.WHITE);
    }


}
