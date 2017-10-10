package com.jiangkang.ktools.download;

/**
 * Created by jiangkang on 2017/10/10.
 */

public interface DownloadListener {


    void onDownloading(long bytesRead, long contentLength);

    void downloadSuccess();

}
