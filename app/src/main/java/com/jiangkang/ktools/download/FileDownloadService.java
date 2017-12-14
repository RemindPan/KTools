package com.jiangkang.ktools.download;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by jiangkang on 2017/10/9.
 */

public interface FileDownloadService {

    @GET
    @Streaming
    Flowable<ResponseBody> downloadFile(@Url String url);


    @GET
    @Streaming
    Flowable<ResponseBody> downloadFile(@Url String url, @Header("Range") String range);



}