package com.jiangkang.ktools.web;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jiangkang.tools.utils.DownloadUtils;
import com.jiangkang.tools.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by jiangkang on 2017/9/20.
 */

public class KWebViewClient extends WebViewClient {


    private static final String TAG = "KWebViewClient";

    private WebContract.IView mContext;


    public KWebViewClient(WebContract.IView context) {
        this.mContext = context;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        view.getSettings().setBlockNetworkImage(true);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        view.getSettings().setBlockNetworkImage(false);
        injectJsFile(view);
    }

    private void injectJsFile(WebView view) {
//        String js = FileUtils.readFromFile("web/inject.js");
//        Log.d(TAG, "injectJsFile: \n js = " + js);
        view.loadUrl("javascript:" + "(function imgOnClick(){\n" +
                "    var imgs = document.getElementsByTagName(\"img\");\n" +
                "    for(var i = 0; i < imgs.length;i++){\n" +
                "            imgs[i].onclick = function(){\n" +
                "                jk.showBigImage(this.src);\n" +
                "            };\n" +
                "    }\n" +
                "})()");
    }


    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        super.onScaleChanged(view, oldScale, newScale);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        //此处对文件资源，js，css等请求资源进行拦截，替换
        Log.d(TAG, "shouldInterceptRequest: request = \n" +
                "\nurl = " + request.getUrl().toString() +
                "\nmethod = " + request.getMethod() +
                "\nheaders = " + request.getRequestHeaders().toString());
//        String url = request.getUrl().toString();
//        if ((url.startsWith("https://") || url.startsWith("http://")) && (url.endsWith(".png") || url.endsWith(".jpg"))) {
//            Log.d(TAG, "拦截资源 :" + url);
//            try {
//                WebResourceResponse response = new WebResourceResponse(MimeTypeMap.getFileExtensionFromUrl(".jpg"), "utf-8", FileUtils.getInputStreamFromAssets("img/dog.jpg"));
//                return response;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return super.shouldInterceptRequest(view, request);
    }



}
