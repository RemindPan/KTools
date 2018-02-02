package com.jiangkang.hybrid

import android.content.Context
import android.os.Bundle
import com.jiangkang.hybrid.web.WebActivity

/**
 * Created by jiangkang on 2018/2/2.
 * description：Khybrid
 */

object Khybrid {

    fun loadUrl(context: Context, url: String) {
        val data = Bundle()
        data.putString("launchUrl", url)
        WebActivity.launch(context, data)
    }

}