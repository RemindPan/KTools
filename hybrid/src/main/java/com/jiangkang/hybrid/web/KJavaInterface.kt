package com.jiangkang.hybrid.web

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.webkit.JavascriptInterface

import com.jiangkang.hybrid.R
import com.jiangkang.tools.utils.ImageUtils

/**
 * Created by jiangkang on 2017/9/20.
 */

class KJavaInterface(private val mContext: Context) {

    val base64Img: String
        @JavascriptInterface
        get() {
            val bitmap = BitmapFactory.decodeResource(mContext.resources, R.drawable.demo)
            val content = ImageUtils.bitmap2Base64(bitmap, 80, Bitmap.CompressFormat.JPEG)
            Log.d(TAG, "getBase64Img: \n" + content)
            return content
        }


    @JavascriptInterface
    fun showBigImage(url: String) {
        val data = Bundle()
        if (!TextUtils.isEmpty(url)) {
            data.putString("imgUrl", url)
            //            BigImageActivity.launch(mContext,data);
        }
    }

    companion object {

        private val TAG = "KJavaInterface"
    }


}
