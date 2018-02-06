package com.jiangkang.requests.interceptors

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by jiangkang on 2018/2/6.
 * description：
 */
class LogInterceptor : Interceptor {

    companion object {
        const val TAG: String = "LogInterceptor"
    }

    private val mContext: Context

    constructor(context: Context) {
        mContext = context
    }


    override fun intercept(chain: Interceptor.Chain): Response {

        val gson = GsonBuilder()
                .setPrettyPrinting()
                .create()

        val request = chain.request()

        Log.d(TAG, "---------------------HTTP START-------------------------------")

        val url = request.url().toString()
        val method = request.method()
        val requestHeaders = request.headers()
        val requestBody = request.body()

        val requestInfo = " --> $method $url (${requestBody?.contentLength()})"
        Log.d(TAG, requestInfo)

        //headers
        for (index in 0 until requestHeaders.size()) {
            Log.d(TAG, "${requestHeaders.name(index)} : ${requestHeaders.value(index)} \n")
        }

        //此处需根据类型log


        val response = chain.proceed(request)
        val responseHeaders = response.headers()
        val responseBody = response.body()

        val responseInfo = " <-- ${response.request().url()}"
        Log.d(TAG, responseInfo)

        //headers
        for (index in 0 until responseHeaders.size()) {
            Log.d(TAG, "${responseHeaders.name(index)} : ${responseHeaders.value(index)} \n")
        }


        //此处需根据类型log


        Log.d(TAG, "---------------------HTTP END  -------------------------------")
        return response
    }


}
