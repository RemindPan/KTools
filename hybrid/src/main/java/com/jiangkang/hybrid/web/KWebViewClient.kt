package com.jiangkang.hybrid.web

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient


/**
 * Created by jiangkang on 2017/9/20.
 */

class KWebViewClient : WebViewClient {

    private val mContext: WebContract.IView

    constructor(context: WebContract.IView) : super() {
        mContext = context
    }

    //TODO: Kotlin 传入的参数类型要注意为null的情况，如果不修改重载函数参数类型，会出现运行时错误
    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        view.settings.blockNetworkImage = true
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)
        view.settings.blockNetworkImage = false
        injectJsFile(view)
        queryHistoryList(view)
    }

    private fun queryHistoryList(view: WebView) {

        val historyList = view.copyBackForwardList()
        if (historyList != null) {
            val size = historyList.size
            val currentIndex = historyList.currentIndex

            (0..(size-1))
                    .map { "$it-${historyList.getItemAtIndex(it)?.title}-${historyList.getItemAtIndex(it)?.url}" }
                    .forEach {
                        Log.d(TAG,it)
                    }
        }
    }

    private fun injectJsFile(view: WebView) {
        //        String js = FileUtils.readFromFile("web/inject.js");
        //        Log.d(TAG, "injectJsFile: \n js = " + js);
        view.loadUrl("javascript:" + "(function imgOnClick(){\n" +
                "    var imgs = document.getElementsByTagName(\"img\");\n" +
                "    for(var i = 0; i < imgs.length;i++){\n" +
                "            imgs[i].onclick = function(){\n" +
                "                jk.showBigImage(this.src);\n" +
                "            };\n" +
                "    }\n" +
                "})()")
    }


    override fun onScaleChanged(view: WebView, oldScale: Float, newScale: Float) {
        super.onScaleChanged(view, oldScale, newScale)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? {
        //此处对文件资源，js，css等请求资源进行拦截，替换
        Log.d(TAG, "shouldInterceptRequest: request = \n" +
                "\nurl = " + request.url.toString() +
                "\nmethod = " + request.method +
                "\nheaders = " + request.requestHeaders.toString())
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
        return super.shouldInterceptRequest(view, request)
    }

    companion object {
        private val TAG = "KWebViewClient"
    }


}
