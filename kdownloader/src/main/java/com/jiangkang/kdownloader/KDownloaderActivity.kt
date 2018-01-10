package com.jiangkang.kdownloader

import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import com.jiangkang.tools.utils.ToastUtils
import com.jiangkang.tools.widget.KDialog
import kotlinx.android.synthetic.main.activity_kdownloader.*
import java.io.File
import kotlin.concurrent.thread

class KDownloaderActivity : AppCompatActivity() {

    val mContext = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kdownloader)
        handleOnClick()
    }

    private val APK_URL: String = "http://bestpay.ctdns.net/bestpay_common_signed.apk"

    private fun handleOnClick() {

        btn_check_support_partial_content.setOnClickListener {
            checkSupportPartialContent()
        }

        btn_download_file.setOnClickListener {
            downloadFile()
        }

    }

    private fun checkSupportPartialContent() {
        Thread {
            val result = DownloaderUtils.checkIsSupportPartialContent(APK_URL)
            runOnUiThread {
                ToastUtils.showShortToast(result.toString())
            }
        }.start()
    }


    private fun downloadFile() {
        thread {
            val downloadedFile = DownloaderUtils.download(APK_URL,
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + File.separator + "zhifubao.apk")
            runOnUiThread {
                if (downloadedFile != null) {
                    ToastUtils.showShortToast(downloadedFile.absolutePath)
                } else {
                    ToastUtils.showShortToast("下载失败")
                }
            }
        }.start()
    }




}
