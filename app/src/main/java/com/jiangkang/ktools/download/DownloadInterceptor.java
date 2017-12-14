package com.jiangkang.ktools.download;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jiangkang on 2017/10/10.
 */

public class DownloadInterceptor implements Interceptor{

    private DownloadListener listener;

    public DownloadInterceptor(DownloadListener listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response originalResponse = chain.proceed(request);
        return originalResponse.newBuilder()
                .body(new ProgressResponseBody(originalResponse.body(),listener))
                .build();
    }
}
