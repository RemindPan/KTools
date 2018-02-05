package com.jiangkang.ktools.web

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jiangkang.ktools.R
import com.jiangkang.tools.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_hybrid.*

class HybridActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hybrid)

        handleClick()

    }

    private fun handleClick() {

        btnResourceInterceptor.setOnClickListener {
            ToastUtils.showShortToast("待开发")
        }

        btnImgLazyLoading.setOnClickListener {
            ToastUtils.showShortToast("待开发")
        }


        btnCertificateVerify.setOnClickListener {
            ToastUtils.showShortToast("待开发")
        }

        btnGeoRequest.setOnClickListener {
            ToastUtils.showShortToast("待开发")
        }

        btnPageCache.setOnClickListener {
            ToastUtils.showShortToast("待开发")
        }

        btnJsInject.setOnClickListener {
            ToastUtils.showShortToast("待开发")
        }


    }
}
