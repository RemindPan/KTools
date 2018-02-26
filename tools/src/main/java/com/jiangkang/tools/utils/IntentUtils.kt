package com.jiangkang.tools.utils

import android.content.Intent
import android.provider.MediaStore

/**
 * Created by jiangkang on 2018/2/17.
 * description：
 */

object IntentUtils {


    /*
    * 拍照Intent
    * */
    val cameraIntent: Intent
        get() {
            return Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        }

    /*
    * 拍视频Intent
    * */
    val takeVideoIntent: Intent
        get() {
            return Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        }





}