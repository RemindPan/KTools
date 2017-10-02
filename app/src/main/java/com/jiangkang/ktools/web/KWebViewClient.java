package com.jiangkang.ktools.web;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jiangkang.tools.utils.LogUtils;


/**
 * Created by jiangkang on 2017/9/20.
 */

public class KWebViewClient extends WebViewClient{


    private static final String TAG = "KWebViewClient";

    private WebContract.IView mContext;


    public KWebViewClient(WebContract.IView context) {
        this.mContext = context;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        LogUtils.d(TAG,"url = %s",request.getUrl().toString());
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }


    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        LogUtils.d(TAG,"oldScale = %s,newScale = %s",String.valueOf(oldScale),String.valueOf(newScale));
        super.onScaleChanged(view, oldScale, newScale);
    }
}
