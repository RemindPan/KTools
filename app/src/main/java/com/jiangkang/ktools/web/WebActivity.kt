package com.jiangkang.ktools.web

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import butterknife.ButterKnife
import butterknife.OnClick
import com.jiangkang.ktools.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity(), WebContract.IView {

    //网址
    private var launchUrl: String? = null


    private var mContext = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        ButterKnife.bind(this)
        initVar()
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initVar() {
        launchUrl = intent.getStringExtra("launchUrl")

        Log.d(TAG, "initVar: launchUrl = " + launchUrl!!)

        webContainer?.apply {
            webChromeClient = KWebChromeClient(mContext)
            webViewClient = KWebViewClient(mContext)
            addJavascriptInterface(KJavaInterface(mContext), "jk")
        }

        webContainer?.settings?.apply {
            mixedContentMode = MIXED_CONTENT_ALWAYS_ALLOW
            javaScriptEnabled = true
            allowFileAccessFromFileURLs = true
            setGeolocationEnabled(true)
            allowFileAccess = true
            allowFileAccessFromFileURLs = true
        }


        WebView.setWebContentsDebuggingEnabled(true)

        webContainer?.loadUrl(launchUrl)

    }


    @OnClick(R.id.iv_title_left)
    fun onIvTitleLeftClicked() {
        if (webContainer!!.canGoBack()) {
            webContainer!!.goBack()
        } else {
            finish()
        }
    }

    @OnClick(R.id.tv_title_right)
    fun onTvTitleRightClicked(): TextView {
        return tv_title_right
    }

    override fun onBackPressed() {
        if (webContainer!!.canGoBack()) {
            webContainer!!.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            webContainer!!.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun getTvTitle(): TextView? {
        return tv_title_middle
    }

    override fun getIvBack(): ImageView? {
        return iv_title_left
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {

        private val TAG = "WebActivity"

        /**
         *
         * @param bundle
         * launchUrl : 网址
         * imgClickable : 点击图片是否显示大图
         *
         * @return
         */
        fun launch(context: Context, bundle: Bundle?) {
            val intent = Intent(context, WebActivity::class.java)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            context.startActivity(intent)
        }
    }
}
