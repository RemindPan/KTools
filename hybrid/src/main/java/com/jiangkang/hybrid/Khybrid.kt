package com.jiangkang.hybrid

import android.content.Context
import android.os.Bundle
import com.jiangkang.hybrid.web.WebActivity
import com.jiangkang.hybrid.web.WebArgs

/**
 * Created by jiangkang on 2018/2/2.
 * description：Khybrid
 */

class Khybrid{

    private var mIsLoadImgLazy:Boolean = false

    private var mIsInterceptResources:Boolean = false

    fun loadUrl(context: Context, url: String){
        val data = Bundle()
        data.putString("launchUrl", url)
        data.putBoolean(WebArgs.IS_LOAD_IMG_LAZY,mIsLoadImgLazy)
        data.putBoolean(WebArgs.IS_INTERCEPT_RESOURCES,mIsInterceptResources)
        WebActivity.launch(context, data)
    }

    fun isLoadImgLazy(isLoadImgLazy: Boolean):Khybrid{
        mIsLoadImgLazy = isLoadImgLazy
        return this
    }

    fun isInterceptResouces(isInterceptResources:Boolean):Khybrid{
        mIsInterceptResources = isInterceptResources
        return this
    }


}