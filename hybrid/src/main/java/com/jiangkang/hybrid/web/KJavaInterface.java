package com.jiangkang.hybrid.web;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.jiangkang.hybrid.R;
import com.jiangkang.tools.utils.ImageUtils;

/**
 * Created by jiangkang on 2017/9/20.
 */

public class KJavaInterface {

    private static final String TAG = "KJavaInterface";

    private Context mContext;

    public KJavaInterface(Context mContext) {
        this.mContext = mContext;
    }

    @JavascriptInterface
    public String getBase64Img(){
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.demo);
        String content = ImageUtils.bitmap2Base64(bitmap,80, Bitmap.CompressFormat.JPEG);
        Log.d(TAG, "getBase64Img: \n" + content);
        return content;
    }


    @JavascriptInterface
    public void showBigImage(String url){
        Bundle data = new Bundle();
        if (!TextUtils.isEmpty(url)){
            data.putString("imgUrl",url);
//            BigImageActivity.launch(mContext,data);
        }
    }


}
