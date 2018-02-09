package com.jiangkang.ktools.share

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import com.jiangkang.ktools.BuildConfig
import com.jiangkang.ktools.R
import com.jiangkang.tools.utils.FileUtils
import kotlinx.android.synthetic.main.activity_share.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.io.File


class ShareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        FileUtils.copyAssetsToFile("img/dog.jpg", "share0.jpg")
        FileUtils.copyAssetsToFile("img/demo.jpeg", "share1.jpeg")

        handleClick()
    }

    private fun handleClick() {

        btnShareText.onClick {
            shareText()
        }


        btnShareSingleImage.onClick {
            shareSingleImage()
        }

        btnShareMultiImages.onClick {
            shareMultiImages()
        }

    }

    private fun shareMultiImages() {
        doAsync {
            val file1 = File(Environment.getExternalStorageDirectory().absolutePath + File.separator + "ktools", "share0.jpg")
            val imageUri1 = FileProvider.getUriForFile(
                    this@ShareActivity,
                    BuildConfig.APPLICATION_ID,
                    file1
            )

            val file2 = File(Environment.getExternalStorageDirectory().absolutePath + File.separator + "ktools", "share1.jpeg")
            val imageUri2 = FileProvider.getUriForFile(
                    this@ShareActivity,
                    BuildConfig.APPLICATION_ID,
                    file2
            )
            var imageUris = ArrayList<Uri>()
            imageUris.add(imageUri1)
            imageUris.add(imageUri2)
            runOnUiThread {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND_MULTIPLE
                shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris)
                shareIntent.type = "image/*"
                startActivity(Intent.createChooser(shareIntent, "分享多张图片到指定App"))
            }
        }
    }

    private fun shareSingleImage() {
        doAsync {
            val file = File(Environment.getExternalStorageDirectory().absolutePath + File.separator + "ktools", "share0.jpg")
            val uriToImage = FileProvider.getUriForFile(
                    this@ShareActivity,
                    BuildConfig.APPLICATION_ID,
                    file
            )
            runOnUiThread {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage)
                shareIntent.type = "image/jpeg"
                startActivity(Intent.createChooser(shareIntent, "分享单张图片到指定App"))
            }
        }

    }


    private fun shareText() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, "分享内容到指定App"))
    }


}
