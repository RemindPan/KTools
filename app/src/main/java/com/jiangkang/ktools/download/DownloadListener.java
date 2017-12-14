package com.jiangkang.ktools.download;

/**
 * Created by jiangkang on 2017/10/10.
 *
 * define the download action and state
 */

public interface DownloadListener {

    void onDownloading(int progress, long contentLength);

    void onSuccess();

    void onError(String message);

}
