package com.jiangkang.ktools.download;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by jiangkang on 2017/10/10.
 */

public class ProgressResponseBody extends ResponseBody{


    private static final String TAG = "ProgressResponseBody";

    private ResponseBody responseBody;

    private DownloadListener listener;

    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody responseBody, DownloadListener listener) {
        this.responseBody = responseBody;
        this.listener = listener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null){
            bufferedSource = Okio.buffer(readSource(responseBody.source()));
        }
        return bufferedSource;
    }


    private Source readSource(Source source){
        return new ForwardingSource(source) {

            long totalBytesRead = 0L;
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {

                long bytesRead = super.read(sink, byteCount);
                if (bytesRead == -1){
                    if (listener != null){
                        listener.onSuccess();
                    }
                }else {
                    totalBytesRead += bytesRead;


                    int progress = (int) (totalBytesRead * 100 / responseBody.contentLength());

//                    Log.d(TAG, "read: size = " + responseBody.contentLength() /(1024 * 1024));


//                    Log.d(TAG, "read: progress = " + progress);

                    if (listener != null){
                        listener.onDownloading(progress,responseBody.contentLength());
                    }
                }
                return bytesRead;
            }
        };
    }

}
