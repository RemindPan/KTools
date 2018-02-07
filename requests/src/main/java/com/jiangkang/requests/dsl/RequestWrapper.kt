package com.jiangkang.requests.dsl

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Created by jiangkang on 2018/2/7.
 * description：Okhttp 请求封装 组成DSL
 */
class RequestWrapper {

    var url: String? = null

    var method: String = "GET"

    var body: RequestBody? = null

    var timeout: Long = 10

    internal var successCallback: (Response) -> Unit = {}

    internal var failedCallback: (Throwable) -> Unit = {}

    fun onSuccess(callback:(Response) -> Unit){
        successCallback = callback
    }

    fun onFailed(callback:(Throwable) -> Unit){
        failedCallback = callback
    }

    //此处还可以返回一个类型，组成链式
    fun fetch(exe:RequestWrapper.() -> Unit):Disposable{
        val wrapper = RequestWrapper()
        wrapper.exe()
        return makeWrapper(wrapper)
    }

    private fun makeWrapper(wrapper: RequestWrapper):Disposable {

        return Flowable.create<Response>(
                {
                    e -> e.onNext(sendRequest(wrapper))
                },
                BackpressureStrategy.BUFFER
        )
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            response -> wrapper.successCallback(response)
                        },
                        {
                            t -> wrapper.failedCallback(t)
                        }
                )
    }

    private fun sendRequest(wrapper: RequestWrapper): Response? {
        var request:Request? = null

        val requestBuilder = Request.Builder().url(wrapper.url)

        when(wrapper.method.toLowerCase()){
            "get" -> request = requestBuilder.get().build()
            "post" -> request = requestBuilder.post(wrapper.body).build()
            "delete" -> request = requestBuilder.delete(wrapper.body).build()
            "put" -> request = requestBuilder.put(wrapper.body).build()
        }

        val client = OkHttpClient.Builder()
                .connectTimeout(wrapper.timeout,TimeUnit.SECONDS)
                .writeTimeout(wrapper.timeout,TimeUnit.SECONDS)
                .readTimeout(wrapper.timeout,TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor())
                .build()
        return client.newCall(request).execute()
    }


}